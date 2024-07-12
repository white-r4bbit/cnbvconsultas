package com.cnbv.consultas.dtoResponse;

import java.util.List;

import com.cnbv.consultas.dtoRequest.ConsultaReceptorDetalleDto;


public class ConsultaDetalleResponse {
	private int id;
	private String comentarios;
	private List<ArchivoConsultaDtoResponse> archivos;
	private ConsultaReceptorDetalleDto receptor;
	

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
	public List<ArchivoConsultaDtoResponse> getArchivos() {
		return archivos;
	}
	public void setArchivos(List<ArchivoConsultaDtoResponse> archivos) {
		this.archivos = archivos;
	}
	public ConsultaReceptorDetalleDto getReceptor() {
		return receptor;
	}
	public void setReceptor(ConsultaReceptorDetalleDto receptor) {
		this.receptor = receptor;
	}
	
	
	
	
}
