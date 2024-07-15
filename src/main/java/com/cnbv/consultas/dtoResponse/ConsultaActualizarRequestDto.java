package com.cnbv.consultas.dtoResponse;

import java.util.List;
import com.cnbv.consultas.dtoRequest.ArchivoConsultaRequestDto;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultaActualizarRequestDto {
	private int id;
	private String firmante;
	private String estatusSolicitud;
	private String comentarioFirmante;
	private List<ArchivoConsultaRequestDto> archivos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirmante() {
		return firmante;
	}

	public void setFirmante(String firmante) {
		this.firmante = firmante;
	}

	public String getEstatusSolicitud() {
		return estatusSolicitud;
	}

	public void setEstatusSolicitud(String estatusSolicitud) {
		this.estatusSolicitud = estatusSolicitud;
	}

	public String getComentarioFirmante() {
		return comentarioFirmante;
	}

	public void setComentarioFirmante(String comentarioFirmante) {
		this.comentarioFirmante = comentarioFirmante;
	}

	public List<ArchivoConsultaRequestDto> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<ArchivoConsultaRequestDto> archivos) {
		this.archivos = archivos;
	}
}
