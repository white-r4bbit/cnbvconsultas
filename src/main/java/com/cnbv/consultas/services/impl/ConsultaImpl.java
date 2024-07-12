package com.cnbv.consultas.services.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.azure.security.keyvault.secrets.SecretClient;
import com.cnbv.consultas.customGlobalExceptions.IlegalActionException;
import com.cnbv.consultas.dtoRequest.ArchivoAdicionalDto;
import com.cnbv.consultas.dtoRequest.ConsultaDto;
import com.cnbv.consultas.dtoRequest.ConsultaReceptorDetalleDto;
import com.cnbv.consultas.dtoRequest.ConsultaReceptorDto;
import com.cnbv.consultas.dtoRequest.FinalizarConsultaDto;
import com.cnbv.consultas.dtoRequest.FinalizarConsultaExternaDto;
import com.cnbv.consultas.dtoRequest.ReceptorDto;
import com.cnbv.consultas.dtoResponse.ArchivoConsultaDtoResponse;
import com.cnbv.consultas.dtoResponse.ConsultaDetalleResponse;
import com.cnbv.consultas.dtoResponse.ConsultaDtoResponse;
import com.cnbv.consultas.dtoResponse.ConsultaExternaDetalle;
import com.cnbv.consultas.entities.ArchivoConsulta;
import com.cnbv.consultas.entities.Consulta;
import com.cnbv.consultas.entities.ConsultaReceptor;
import com.cnbv.consultas.entities.TipoDocumentoEnum;
import com.cnbv.consultas.entities.TipoElementoEnum;
import com.cnbv.consultas.models.EnvioBside;
import com.cnbv.consultas.models.EstatusAsunto;
import com.cnbv.consultas.repositories.ConsultaRepository;
import com.cnbv.consultas.repositories.ReceptorRepository;
import com.cnbv.consultas.repositories.TipoDocumentoRepository;
import com.cnbv.consultas.repositories.TipoElementoRepository;
import com.cnbv.consultas.services.ConsultaService;
import reactor.core.publisher.Mono;

@Service
public class ConsultaImpl implements ConsultaService {

	@Autowired
	private ConsultaRepository consulta;
	@Autowired
	private TipoDocumentoRepository documentoRepository;
	@Autowired
	private TipoElementoRepository elementoRepository;
	@Autowired
	private ReceptorRepository receptorRepository;
	@Autowired
	private SecretClient secretClient;

	private final WebClient.Builder webClientBuilder;

	public ConsultaImpl(WebClient.Builder webClientBuilder) {
		this.webClientBuilder = webClientBuilder;

	}

	public Mono<EnvioBside> getDisenioEnvios(String baseUrl, String pathVariable) {
		WebClient webClient = this.webClientBuilder.baseUrl(baseUrl).build();

		String subscriptionKey = secretClient.getSecret("CNBV--PortalGestion--Servicios--Ocp-Apim-Subscription-Key")
				.getValue();
		return webClient.get().uri("/gestion-diseno-envios/v1/DisenoEnvios/{valor}", pathVariable)
				.header("Ocp-Apim-Subscription-Key", subscriptionKey).retrieve().bodyToMono(EnvioBside.class);
	}

	public Mono<String> getIdEnvio(int idEntidad, int tipoEntidad, int idAsunto, String folioAsunto, int areaResponsable) {

		String url = secretClient.getSecret("CNBV--Apim").getValue();
		WebClient webClient = this.webClientBuilder.baseUrl(url).build();
		String subscriptionKey = secretClient.getSecret("CNBV--PortalGestion--Servicios--Ocp-Apim-Subscription-Key")
				.getValue();
		return getDisenioEnvios(url, "336A705E-A1A1-4E6A-815E-40087AAACDF4").flatMap(response -> {
			response.setIdAsunto(idAsunto);
			response.setFolioCrearAsunto(folioAsunto);
			response.setIdAreaResponsable(areaResponsable);
			return webClient.post().uri("/gestor-de-envios/v1/GestorEnvios/{valor1}/{valor2}", idEntidad, tipoEntidad)
					.header("Ocp-Apim-Subscription-Key", subscriptionKey).bodyValue(response).retrieve()
					.bodyToMono(Map.class);
		}).map(responseMap -> {

			if (responseMap.containsKey("respuesta")) {
				Map<String, Object> respuesta = (Map<String, Object>) responseMap.get("respuesta");
				String folioEnvio = (String) respuesta.get("folioEnvio");
				return folioEnvio;
			} else {
				throw new RuntimeException("La respuesta no contiene folioEnvioAsunto");
			}
		});
	}

	public void updateStatus(String folioAsunto) {
		String ip = secretClient.getSecret("CNBV--MS--Asuntos--IP").getValue();
		WebClient webClient = this.webClientBuilder.baseUrl(ip).build();

		EstatusAsunto estatusAsunto = new EstatusAsunto();
		estatusAsunto.setFolioAsunto(folioAsunto);
		estatusAsunto.setIdEstatusAsunto(4);
		estatusAsunto.setAccion("SOLICITUD_FINALIZADA");

		try {
	        webClient.post()
	                 .uri("/actualizarasunto/estatus")
	                 .bodyValue(estatusAsunto)
	                 .retrieve()
	                 .toBodilessEntity()
	                 .block();
	    } catch (WebClientResponseException e) {
	        throw new IlegalActionException("Error al enviar la solicitud: " + e.getStatusCode());
	    } catch (Exception e) {
	        throw new IlegalActionException("Error durante la petición al Gestor Asuntos");
	    }
	}

	@Override
	public List<Consulta> findAll() {

		return consulta.findAll();
	}

	@Transactional
	@Override
	public int solicitarConsulta(ConsultaDto consultaDto) {

		List<Consulta> consultasToCheck = consulta.findByFolioAsunto(consultaDto.getFolioAsunto());
		List<Boolean> consultasActivas = consultasToCheck.stream().map(check -> check.isActiva())
				.filter(activas -> activas.equals(true)).collect(Collectors.toList());

		if (consultasActivas.size() == 0) {
			Consulta consultaToInsert = new Consulta();
			consultaToInsert.setFolioAsunto(consultaDto.getFolioAsunto());
			consultaToInsert.setFechaSolicitud(ZonedDateTime.now(ZoneId.of("America/Mexico_City")));
			consultaToInsert.setDetalle(consultaDto.getComentarios());
			consultaToInsert.setActiva(true);
			consultaToInsert.setSecuenciaFirma(consultaDto.getSecuenciaFirma());
			consultaToInsert.setCadenaOriginal(consultaDto.getCadenaOriginal());
			List<Consulta> consultasList = consulta.findByFolioAsunto(consultaDto.getFolioAsunto());

			consultaToInsert.setVersion(consultasList.size() + 1);

			Consulta consultaSaved = consulta.save(consultaToInsert);

			for (ReceptorDto receptorDtoRequest : consultaDto.getReceptores()) {

				ConsultaReceptor receptorToInsert = new ConsultaReceptor();
				receptorToInsert.setClave(receptorDtoRequest.getClave());
				receptorToInsert.setNombre(receptorDtoRequest.getNombre());
				receptorToInsert.setObligatoria(receptorDtoRequest.isEsObligatoria());
				receptorToInsert.setActiva(true);
				receptorToInsert.setInterno(receptorDtoRequest.isEsInterna());
				receptorToInsert.setConsulta(consultaSaved);
				receptorToInsert.setFirmante(receptorDtoRequest.getEstatusSolicitudResponse().getFirmante());
				receptorToInsert.setEstatusSolicitud(receptorDtoRequest.getEstatusSolicitudResponse().getEstatusSolicitud());

				if (receptorDtoRequest.isEsInterna() == false) {

					String idEnvio = getIdEnvio(receptorDtoRequest.getEntidadExterna().getId(),
							receptorDtoRequest.getEntidadExterna().getTipo(), consultaDto.getAsunto().getId(),
							consultaDto.getAsunto().getFolio(),consultaDto.getAreaResponsable()).block();

					receptorToInsert.setIdEnvio(idEnvio);

				}
				receptorRepository.save(receptorToInsert);

			}

			return consultaSaved.getId();

		} else {
			return 0;
		}
	}

	@Transactional
	@Override
	public Boolean agregarArchivos(ArchivoAdicionalDto archivo) {

		Consulta consultaEntity = consulta.findById(archivo.getId())
				.orElseThrow(() -> new IlegalActionException("El ID de la consulta no existe"));
		try {
			List<ArchivoConsulta> archivosAdicionales = archivo.getArchivos().stream().map(file -> {

				ArchivoConsulta archivoConsulta = new ArchivoConsulta();

				archivoConsulta.setRuta(file.getRuta());
				archivoConsulta.setNombre(file.getNombre());
				archivoConsulta.setFechaCreacion(file.getFechaCreacion());
				TipoDocumentoEnum doc = documentoRepository.findByNombre(file.getTipoDocumento());
				archivoConsulta.setTipoDocumentoEnum(doc);
				TipoElementoEnum elem = elementoRepository.findByNombre(file.getTipoElemento());
				archivoConsulta.setTipoElementoEnum(elem);
				archivoConsulta.setConsultaId(consultaEntity);
				return archivoConsulta;

			}).collect(Collectors.toList());

			consultaEntity.setArchivoConsulta(archivosAdicionales);
		} catch (Exception e) {

			return false;
		}
		consulta.save(consultaEntity);

		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public List<ConsultaDtoResponse> obtenerConsultas(String folioAsunto) {
		List<Consulta> consultas = null;
		consultas = consulta.findByFolioAsunto(folioAsunto);

		if (consultas != null && consultas.size() > 0) {
			List<ConsultaDtoResponse> consultasResponse = consultas.stream().map(consulta -> {
				ConsultaDtoResponse consultaDtoResponse = new ConsultaDtoResponse();
				consultaDtoResponse.setIdentificador(consulta.getId());
				consultaDtoResponse.setFolioAsunto(consulta.getFolioAsunto());
				consultaDtoResponse.setFechaSolicitud(consulta.getFechaSolicitud());
				consultaDtoResponse.setEnProceso(consulta.isActiva());
				consultaDtoResponse.setVersion(consulta.getVersion());
				consultaDtoResponse.setReceptores(consulta.getConsultaReceptores().stream().map(receptorEntity -> {
					ConsultaReceptorDto consultaReceptorDto = new ConsultaReceptorDto();

					consultaReceptorDto.setId(receptorEntity.getId());
					consultaReceptorDto.setNombre(receptorEntity.getNombre());
					consultaReceptorDto.setClave(receptorEntity.getClave());
					consultaReceptorDto.setEsInterna(receptorEntity.isInterno());
					consultaReceptorDto.setFechaRespuesta(receptorEntity.getFechaRespuesta());
					consultaReceptorDto.setObligatoria(receptorEntity.isObligatoria());
					consultaReceptorDto.setEnProceso(receptorEntity.isActiva());
					consultaReceptorDto.setIdEnvio(receptorEntity.getIdEnvio());
					consultaReceptorDto.setFirmante(receptorEntity.getFirmante());
					consultaReceptorDto.setEstatusSolicitud(receptorEntity.getEstatusSolicitud());
					consultaReceptorDto.setComentarioFirmante(receptorEntity.getComentarioFirmante());
					return consultaReceptorDto;

				}).sorted(Comparator.comparing(ConsultaReceptorDto::getFechaRespuesta).reversed()).collect(Collectors.toList()));
				return consultaDtoResponse;

			}).sorted(Comparator.comparing(ConsultaDtoResponse::getFechaSolicitud).reversed()).collect(Collectors.toList());

			return consultasResponse;

		} else {
			return null;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public ConsultaDetalleResponse obtenerDetalleConsulta(int idConsultaReceptor) {

		Optional<ConsultaReceptor> receptor = receptorRepository.findById(idConsultaReceptor);

		if (receptor.isPresent()) {

			ConsultaDetalleResponse detalleConsultaResponse = new ConsultaDetalleResponse();

			ConsultaReceptorDetalleDto receptorDetail = new ConsultaReceptorDetalleDto();
			receptorDetail.setId(receptor.get().getId());
			receptorDetail.setClave(receptor.get().getClave());
			receptorDetail.setNombre(receptor.get().getNombre());
			receptorDetail.setFinalizadaPor(receptor.get().getNombreFinalizador());
			receptorDetail.setComentarios(receptor.get().getComentartios());
			receptorDetail.setFechaRespuesta(receptor.get().getFechaRespuesta());
			receptorDetail.setIdEnvio(receptor.get().getIdEnvio());
			
			receptorDetail.setFirmante(receptor.get().getFirmante());
			receptorDetail.setEstatusSolicitud(receptor.get().getEstatusSolicitud());
			receptorDetail.setComentarioFirmante(receptor.get().getComentarioFirmante());
			
			receptorDetail.setArchivos(receptor.get().getArchivosReceptor().stream().map(archivosDto -> {
				ArchivoConsultaDtoResponse archivo = new ArchivoConsultaDtoResponse();

				archivo.setId(archivosDto.getId());
				archivo.setRuta(archivosDto.getRuta());
				archivo.setNombre(archivosDto.getNombre());
				archivo.setFechaCreacion(archivosDto.getFechaCreacion());
				archivo.setTipoElemento(archivosDto.getTipoElementoEnum().getNombre());
				archivo.setTipoDocumento(archivosDto.getTipoDocumentoEnum().getNombre());

				return archivo;

			}).sorted(Comparator.comparing(ArchivoConsultaDtoResponse::getFechaCreacion).reversed()).collect(Collectors.toList()));

			detalleConsultaResponse.setId(receptor.get().getConsulta().getId());
			detalleConsultaResponse.setComentarios(receptor.get().getConsulta().getDetalle());
			detalleConsultaResponse.setReceptor(receptorDetail);
			detalleConsultaResponse
					.setArchivos(receptor.get().getConsulta().getArchivoConsulta().stream().map(archivosDto -> {
						ArchivoConsultaDtoResponse archivoConsulta = new ArchivoConsultaDtoResponse();

						archivoConsulta.setId(archivosDto.getId());
						archivoConsulta.setRuta(archivosDto.getRuta());
						archivoConsulta.setNombre(archivosDto.getNombre());
						archivoConsulta.setFechaCreacion(archivosDto.getFechaCreacion());
						archivoConsulta.setTipoElemento(archivosDto.getTipoElementoEnum().getNombre());
						archivoConsulta.setTipoDocumento(archivosDto.getTipoDocumentoEnum().getNombre());

						return archivoConsulta;

					}).sorted(Comparator.comparing(ArchivoConsultaDtoResponse::getFechaCreacion).reversed()).collect(Collectors.toList()));

			return detalleConsultaResponse;

		} else {
			return null;
		}

	}

	@Transactional
	@Override
	public Boolean finalizarConsultar(FinalizarConsultaDto finalizarConsultaDto) {

		Optional<ConsultaReceptor> receptorBd = receptorRepository
				.findById(finalizarConsultaDto.getIdConsultaReceptor());

		if (receptorBd.isPresent()) {
			if (receptorBd.get().isInterno()) {
				if (receptorBd.get().isActiva()) {
					receptorBd.get().setComentartios(finalizarConsultaDto.getComentarios());
					receptorBd.get().setSecuenciaFirma((finalizarConsultaDto.getSecuenciaFirma()));
					receptorBd.get().setCadenaOriginal(finalizarConsultaDto.getCadenaOriginal());
					receptorBd.get().setFechaRespuesta(ZonedDateTime.now(ZoneId.of("America/Mexico_City")));
					receptorBd.get().setArchivosReceptor(finalizarConsultaDto.getArchivos().stream().map(archivos -> {
						ArchivoConsulta filesEntity = new ArchivoConsulta();

						filesEntity.setRuta(archivos.getRuta());
						filesEntity.setFechaCreacion(archivos.getFechaCreacion());
						filesEntity.setConsultaId(null);
						TipoDocumentoEnum doc = documentoRepository.findByNombre(archivos.getTipoDocumento());
						filesEntity.setTipoDocumentoEnum(doc);
						TipoElementoEnum elem = elementoRepository.findByNombre(archivos.getTipoElemento());
						filesEntity.setTipoElementoEnum(elem);
						filesEntity.setConsultaReceptor(receptorBd.get());
						filesEntity.setNombre(archivos.getNombre());

						return filesEntity;

					}).collect(Collectors.toList()));
					receptorBd.get().setActiva(false);
					receptorBd.get().setNombreFinalizador(finalizarConsultaDto.getFinalizadaPor());

					receptorRepository.save(receptorBd.get());

					List<ConsultaReceptor> receptores = receptorRepository
							.findByConsulta(receptorBd.get().getConsulta());
					int contador = 0;
					for (ConsultaReceptor receptorInBd : receptores) {

						if (receptorInBd.isActiva() == false) {
							contador++;
						}

					}

					if (contador == receptores.size()) {
						Optional<Consulta> consultaCompleta = consulta.findById(receptorBd.get().getConsulta().getId());

						consultaCompleta.get().setActiva(false);
						consulta.save(consultaCompleta.get());
						updateStatus(receptorBd.get().getConsulta().getFolioAsunto());

					}

					return true;
				} else {
					throw new IlegalActionException("La consulta ya se encuentra finalizada");

				}
			} else {
				return null;

			}
		} else {
			return false;
		}
	}

	@Transactional
	@Override
	public Boolean finalizarConsultaExterna(FinalizarConsultaExternaDto finalizarConsultaExternaDto) {

		Optional<ConsultaReceptor> receptorBd = receptorRepository
				.findByIdEnvio(finalizarConsultaExternaDto.getIdEnvio());

		if (receptorBd.isPresent()) {
			if (receptorBd.get().isInterno() == false) {
				if (receptorBd.get().isActiva()) {
					if(finalizarConsultaExternaDto.getFirmaElectronica()!=null){


					
					receptorBd.get()
							.setSecuenciaFirma(finalizarConsultaExternaDto.getFirmaElectronica().getSecuencia());
					receptorBd.get()
							.setCadenaOriginal(finalizarConsultaExternaDto.getFirmaElectronica().getCadenaOriginal());
					}
					receptorBd.get().setFechaRespuesta(ZonedDateTime.now(ZoneId.of("America/Mexico_City")));
					receptorBd.get().setActiva(false);

					receptorRepository.save(receptorBd.get());

					List<ConsultaReceptor> receptores = receptorRepository
							.findByConsulta(receptorBd.get().getConsulta());
					int contador = 0;
					for (ConsultaReceptor receptorInBd : receptores) {

						if (receptorInBd.isActiva() == false) {
							contador++;
						}

					}

					if (contador == receptores.size()) {
						Optional<Consulta> consultaCompleta = consulta.findById(receptorBd.get().getConsulta().getId());

						consultaCompleta.get().setActiva(false);
						consulta.save(consultaCompleta.get());

						updateStatus(receptorBd.get().getConsulta().getFolioAsunto());
					}
					return true;
				} else {
					throw new IlegalActionException("La consulta ya se encuentra finalizada");
				}
			} else {
				return null;
			}
		} else {
			return false;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<String> ObtenerCatalogoTipoElemento() {

		List<TipoElementoEnum> tipoDocumentos = elementoRepository.findAll();

		List<String> nombresTipoElementos = tipoDocumentos.stream().map(elemento -> {
			return elemento.getNombre();
		}).collect(Collectors.toList());

		return nombresTipoElementos;

	}

	@Transactional(readOnly = true)
	@Override
	public List<String> ObtenerCatalogoTipoDocumento() {

		List<TipoDocumentoEnum> tipoDocumentos = documentoRepository.findAll();

		List<String> nombresTipoDocumentos = tipoDocumentos.stream().map(documento -> {
			return documento.getNombre();
		}).collect(Collectors.toList());

		return nombresTipoDocumentos;

	}

	@Override
	public ConsultaExternaDetalle consultarConsultaExterna(String idEnvio) {

		ConsultaReceptor receptor = receptorRepository.findByIdEnvio(idEnvio)
				.orElseThrow(() -> new IlegalActionException("El usuario no existe"));
		String detalleConsulta =  receptor.getConsulta().getDetalle();
		List<ArchivoConsultaDtoResponse> archivosReceptor = receptor.getConsulta().getArchivoConsulta().stream()
				.map(archivoDto -> {

					ArchivoConsultaDtoResponse archivo = new ArchivoConsultaDtoResponse();

					archivo.setRuta(archivoDto.getRuta());
					archivo.setNombre(archivoDto.getNombre());
					archivo.setFechaCreacion(archivoDto.getFechaCreacion());
					archivo.setTipoElemento(archivoDto.getTipoElementoEnum().getNombre());
					archivo.setTipoDocumento(archivoDto.getTipoDocumentoEnum().getNombre());

					return archivo;

				}).sorted(Comparator.comparing(ArchivoConsultaDtoResponse::getFechaCreacion).reversed()).collect(Collectors.toList());

		ConsultaExternaDetalle consultaExternaDetalle = new ConsultaExternaDetalle();
		
		consultaExternaDetalle.setDetalle(detalleConsulta);
		consultaExternaDetalle.setDocumentos(archivosReceptor);
		
		return consultaExternaDetalle;

	}

}
