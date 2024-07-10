package com.cnbv.consultas.dtoResponse;

import java.time.ZonedDateTime;
import java.util.List;

import com.cnbv.consultas.dtoRequest.ConsultaReceptorDto;

public class ConsultaDtoResponse {

	
	private int identificador;
	private String folioAsunto;
	private ZonedDateTime fechaSolicitud;
	private boolean enProceso;
	private int version;
	private List<ConsultaReceptorDto> receptores;
	
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public String getFolioAsunto() {
		return folioAsunto;
	}
	public void setFolioAsunto(String folioAsunto) {
		this.folioAsunto = folioAsunto;
	}
	
	public ZonedDateTime getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(ZonedDateTime fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	public boolean isEnProceso() {
		return enProceso;
	}
	public void setEnProceso(boolean enProceso) {
		this.enProceso = enProceso;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public List<ConsultaReceptorDto> getReceptores() {
		return receptores;
	}
	public void setReceptores(List<ConsultaReceptorDto> receptores) {
		this.receptores = receptores;
	}

	
	
	
}
