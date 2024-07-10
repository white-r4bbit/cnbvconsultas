package com.cnbv.consultas.services;

import java.util.List;

import com.cnbv.consultas.dtoRequest.ArchivoAdicionalDto;
import com.cnbv.consultas.dtoRequest.ArchivoConsultaDto;
import com.cnbv.consultas.dtoRequest.ConsultaDto;
import com.cnbv.consultas.dtoRequest.FinalizarConsultaDto;
import com.cnbv.consultas.dtoRequest.FinalizarConsultaExternaDto;
import com.cnbv.consultas.dtoResponse.ArchivoConsultaDtoResponse;
import com.cnbv.consultas.dtoResponse.ConsultaDetalleResponse;
import com.cnbv.consultas.dtoResponse.ConsultaDtoResponse;
import com.cnbv.consultas.dtoResponse.ConsultaExternaDetalle;
import com.cnbv.consultas.entities.Consulta;

public interface ConsultaService {

	List<Consulta> findAll();
	int solicitarConsulta(ConsultaDto consulta);
	List<ConsultaDtoResponse> obtenerConsultas(String folioAsunto); 
	ConsultaDetalleResponse obtenerDetalleConsulta( int idConsultaReceptor);
	Boolean finalizarConsultar(FinalizarConsultaDto finalizarConsultaDto);
	List<String> ObtenerCatalogoTipoElemento();
	List<String> ObtenerCatalogoTipoDocumento();
	Boolean finalizarConsultaExterna(FinalizarConsultaExternaDto finalizarConsultaExternaDto);
	ConsultaExternaDetalle consultarConsultaExterna(String idEnvio);
	Boolean agregarArchivos(ArchivoAdicionalDto archivo);
		
	
}

