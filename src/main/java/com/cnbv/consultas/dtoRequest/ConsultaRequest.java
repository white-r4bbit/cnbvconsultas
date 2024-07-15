package com.cnbv.consultas.dtoRequest;

import java.util.List;
import com.cnbv.consultas.dtoResponse.ConsultaActualizarRequestDto;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultaRequest {

	private int id;
	private String comentarios;
	private Integer secuenciaFirma;
	private String cadenaOriginal;
	private ConsultaActualizarRequestDto receptor;
	private List<ArchivoConsultaRequestDto> archivos;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public Integer getSecuenciaFirma() {
		return secuenciaFirma;
	}
	public void setSecuenciaFirma(Integer secuenciaFirma) {
		this.secuenciaFirma = secuenciaFirma;
	}
	public String getCadenaOriginal() {
		return cadenaOriginal;
	}
	public void setCadenaOriginal(String cadenaOriginal) {
		this.cadenaOriginal = cadenaOriginal;
	}
	public List<ArchivoConsultaRequestDto> getArchivos() {
		return archivos;
	}
	public void setArchivos(List<ArchivoConsultaRequestDto> archivos) {
		this.archivos = archivos;
	}
	public ConsultaActualizarRequestDto getReceptor() {
		return receptor;
	}
	public void setReceptor(ConsultaActualizarRequestDto receptor) {
		this.receptor = receptor;
	}
}