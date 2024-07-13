package com.cnbv.consultas.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cnbv.consultas.dtoRequest.ArchivoAdicionalDto;
import com.cnbv.consultas.dtoRequest.ConsultaDto;
import com.cnbv.consultas.dtoRequest.FinalizarConsultaDto;
import com.cnbv.consultas.dtoRequest.FinalizarConsultaExternaDto;
import com.cnbv.consultas.dtoResponse.ArchivoConsultaDtoResponse;
import com.cnbv.consultas.dtoResponse.ConsultaDetalleResponse;
import com.cnbv.consultas.dtoResponse.ConsultaDtoResponse;
import com.cnbv.consultas.dtoResponse.ConsultaExternaDetalle;
import com.cnbv.consultas.services.AppInsightsService;
import com.cnbv.consultas.services.impl.ConsultaImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("api/consultas")
@Validated
public class ConsultaController {

	@Autowired
	private ConsultaImpl solicitudConsulta;
	@Autowired
	private AppInsightsService appInsightsService;

	@Operation(tags = "Consultas", summary = "Permite generar un proceso de solicitud de consulta ", description = "Permite generar un proceso de solicitud de consulta de manera interna (áreas de la CNBV) y externa (Autoridades) sobre un folio asunto.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201"), @ApiResponse(responseCode = "409"),
			@ApiResponse(responseCode = "500") })

	@PostMapping(value = "/solicitarConsultas")
	public ResponseEntity<?> solicitarConsulta(@RequestBody ConsultaDto consultaDtoRequest) {

		appInsightsService.trackEvent("Se ejecutó método solicitarconsulta");

		int consultaId = solicitudConsulta.solicitarConsulta(consultaDtoRequest);

		if (consultaId != 0) {
			return new ResponseEntity<>(consultaId, HttpStatus.CREATED);

		} else {

			return new ResponseEntity<>("Existe un proceso de consulta previo que no ha sido finalizado",
					HttpStatus.CONFLICT);
		}

	}

	@Operation(tags = "Consultas", summary = "Permite agregar archivos", description = "Permite agregar varios archivos a una consulta existente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "409"),
			@ApiResponse(responseCode = "500") })
	@PatchMapping(value = "/agregarArchivos")
	public ResponseEntity<String> agregarArchivos(@RequestBody ArchivoAdicionalDto archivo) {

		appInsightsService.trackEvent("Se ejecutó método solicitarconsulta");

		Boolean archivosCreated = solicitudConsulta.agregarArchivos(archivo);

		if (archivosCreated) {
			return new ResponseEntity<String>("Los archivos se cargaron correctamente.", HttpStatus.OK);

		} else {

			return new ResponseEntity<>("Los archivos no se lograron leer", HttpStatus.CONFLICT);
		}

	}

	@Operation(tags = "Consultas", summary = "Permite traer la lista de consultas relacionadas a un folio asunto.", description = "Permite traer la lista de consultas solicitadas a diferentes receptores ya sean internos o externos a la CNBV.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultaDtoResponse.class))),
			@ApiResponse(responseCode = "404"), @ApiResponse(responseCode = "500") })
	@GetMapping("/ObtenerConsultas")
	public ResponseEntity<?> ObtenerConsultas(String folioAsunto) {

		appInsightsService.trackEvent("Se ejecutó método obtenerconsulta");
		List<ConsultaDtoResponse> consulta = solicitudConsulta.obtenerConsultas(folioAsunto);

		if (consulta != null && consulta.size() > 0) {
			return new ResponseEntity<>(consulta, HttpStatus.OK);

		} else {

			return new ResponseEntity<>("No se encontró información con el folio proporcionado", HttpStatus.NOT_FOUND);
		}

	}

	@Operation(tags = "Consultas", summary = "Permite traer el detalle de las consultas y de los receptores con base en el folio asunto.", description = "Permite traer los comentarios y archivos de la consulta y del receptor con base en el id del mismo.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultaDetalleResponse.class))),
			@ApiResponse(responseCode = "404"), @ApiResponse(responseCode = "500") })
	@GetMapping("/obtenerDetalleConsulta")

	public ResponseEntity<?> ObtenerDetalleConsulta(int idConsultaReceptor) {

		appInsightsService.trackEvent("Se ejecutó método obtenerdetalleconsulta");

		ConsultaDetalleResponse consultaDetalle = solicitudConsulta.obtenerDetalleConsulta(idConsultaReceptor);

		if (consultaDetalle != null) {
			return new ResponseEntity<>(consultaDetalle, HttpStatus.OK);

		} else {

			return new ResponseEntity<>("No se encontró información con el identificador proporcionado",
					HttpStatus.NOT_FOUND);

		}

	}

	@Operation(tags = "Consultas", summary = "Permite guardar la información de respuesta que da el receptor y cierra la consulta.", description = "Permite guardar la información que haya respondido el receptor de la consulta, los archivos que agregó a su respuesta, sus comentarios y se cierra la consulta.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "409"),
			@ApiResponse(responseCode = "404"), @ApiResponse(responseCode = "500") })

	@PatchMapping(value = "/finalizarConsultaExterna")
	public ResponseEntity<?> finalizarConsultaExterna(
			@RequestBody FinalizarConsultaExternaDto finalizarConsultaExternaDto) {

		appInsightsService.trackEvent("Se ejecutó método finalizarConsultaExterna");

		Boolean consultaFinalizada = solicitudConsulta.finalizarConsultaExterna(finalizarConsultaExternaDto);

		if (consultaFinalizada != null && consultaFinalizada) {
			return new ResponseEntity<>("La consulta fue finalizada correctamente", HttpStatus.OK);

		} else if (consultaFinalizada == null) {

			return new ResponseEntity<>(
					"Para finalizar una consulta interna se debe utilizar el método finalizarConsulta",
					HttpStatus.NOT_FOUND);
		} else {

			return new ResponseEntity<>("No se encontró información con el identificador proporcionado",
					HttpStatus.NOT_FOUND);
		}

	}

	@Operation(tags = "Consultas", summary = "Permite guardar la información de respuesta que da el receptor y cierra la consulta.", description = "Permite guardar la información que haya respondido el receptor de la consulta, los archivos que agregó a su respuesta, sus comentarios y se cierra la consulta.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "409"),
			@ApiResponse(responseCode = "404"), @ApiResponse(responseCode = "500") })

	@PatchMapping(value = "/finalizarConsulta")
	public ResponseEntity<?> finalizarConsulta(@RequestBody FinalizarConsultaDto finalizarConsultaDto) {

		appInsightsService.trackEvent("Se ejecutó método finalizarConsulta");

		Boolean consultaFinalizada = solicitudConsulta.finalizarConsultar(finalizarConsultaDto);

		if (consultaFinalizada != null && consultaFinalizada) {
			return new ResponseEntity<>("La consulta fue finalizada correctamente", HttpStatus.OK);

		} else if (consultaFinalizada == null) {
			return new ResponseEntity<String>(
					"Para finalizar una consulta externa se debe utilizar el método finalizarConsultaExterna",
					HttpStatus.NOT_FOUND);

		} else {

			return new ResponseEntity<>("No se encontró información con el identificador proporcionado",
					HttpStatus.NOT_FOUND);
		}

	}

	@Operation(tags = "Consultas", summary = "Permite obtener documentos.", description = "Permite obtener los documentos de una consulta externa.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "409"),
			@ApiResponse(responseCode = "500") })
	@GetMapping(value = "/consultarConsultaExterna")
	public ResponseEntity<ConsultaExternaDetalle> consultarConsultaExterna(
			@RequestParam String idEnvio) {

		ConsultaExternaDetalle consultaExternaDetalle = solicitudConsulta.consultarConsultaExterna(idEnvio);

		return new ResponseEntity<>(consultaExternaDetalle, HttpStatus.OK);

	}

	@Operation(tags = "Consultas", summary = "Permtie traer la lista de los tipos de solicitud.")
	@GetMapping(value = "/obtenerCatalogoElemento")
	public ResponseEntity<List<String>> ObtenerCatalogoTipoElemento() {

		List<String> tipoElementos = solicitudConsulta.ObtenerCatalogoTipoElemento();

		if (tipoElementos != null && tipoElementos.size() > 0) {
			return new ResponseEntity<>(tipoElementos, HttpStatus.OK);

		} else {

			return ResponseEntity.notFound().build();
		}

	}

	@Operation(tags = "Consultas", summary = "Permite traer la lsita de los tipos de documentos.")
	@GetMapping(value = "/obtenerCatalogoDocumento")
	public ResponseEntity<List<String>> obtenerCatalogoTipoDocumento() {

		List<String> tipoDocumentos = solicitudConsulta.ObtenerCatalogoTipoDocumento();

		if (tipoDocumentos != null && tipoDocumentos.size() > 0) {
			return new ResponseEntity<>(tipoDocumentos, HttpStatus.OK);

		} else {

			return ResponseEntity.notFound().build();
		}

	}


	
	@Operation(tags = "Consultas", summary = "Permite traer los asuntos pendientes de firma.")
	@GetMapping(value = "/obtenerAsuntosPendienteFirma")
	public ResponseEntity<Set<String>> obtenerAsuntosPendienteFirma() {

		Set<String> pendientesFirma = solicitudConsulta.obtenerAsuntosPendienteFirma();

			return new ResponseEntity<>(pendientesFirma, HttpStatus.OK);


		

	}


}
