package com.cnbv.consultas.dtoRequest;

import java.util.List;

import com.cnbv.consultas.models.Asunto;

public class ConsultaDto {

	private String folioAsunto;
	private int secuenciaFirma;
	private String cadenaOriginal;
	private List<ReceptorDto> receptores;
	private String comentarios;
	private Asunto asunto;
	private int areaResponsable;
	public int getSecuenciaFirma() {
		return secuenciaFirma;
	}

	public void setSecuenciaFirma(int secuenciaFirma) {
		this.secuenciaFirma = secuenciaFirma;
	}

	public String getCadenaOriginal() {
		return cadenaOriginal;
	}

	public void setCadenaOriginal(String cadenaOriginal) {
		this.cadenaOriginal = cadenaOriginal;
	}

	public List<ReceptorDto> getReceptores() {
		return receptores;
	}

	public void setReceptores(List<ReceptorDto> receptores) {
		this.receptores = receptores;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Asunto getAsunto() {
		return asunto;
	}

	public void setAsunto(Asunto asunto) {
		this.asunto = asunto;
	}

	public String getFolioAsunto() {
		return folioAsunto;
	}

	public void setFolioAsunto(String folioAsunto) {
		this.folioAsunto = folioAsunto;
	}

	public int getAreaResponsable() {
		return areaResponsable;
	}

	public void setAreaResponsable(int areaResponsable) {
		this.areaResponsable = areaResponsable;
	}
}
