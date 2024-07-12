package com.cnbv.consultas.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstatusAsunto {
	@JsonProperty("FolioAsunto") 
	private String folioAsunto;
	@JsonProperty("IdEstatusAsunto") 
	private int idEstatusAsunto;
	@JsonProperty("Accion") 
	private String  accion;
	public String getFolioAsunto() {
		return folioAsunto;
	}
	public void setFolioAsunto(String folioAsunto) {
		this.folioAsunto = folioAsunto;
	}
	public int getIdEstatusAsunto() {
		return idEstatusAsunto;
	}
	public void setIdEstatusAsunto(int idEstatusAsunto) {
		this.idEstatusAsunto = idEstatusAsunto;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	

	
	
	
}
