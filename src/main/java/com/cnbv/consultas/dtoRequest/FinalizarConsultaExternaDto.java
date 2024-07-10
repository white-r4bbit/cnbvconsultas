package com.cnbv.consultas.dtoRequest;

import com.cnbv.consultas.models.FirmaElectronica;

public class FinalizarConsultaExternaDto {

	
	private String idEnvio;
	private FirmaElectronica firmaElectronica;
	
	
	public String getIdEnvio() {
		return idEnvio;
	}
	public void setIdEnvio(String idEnvio) {
		this.idEnvio = idEnvio;
	}
	public FirmaElectronica getFirmaElectronica() {
		return firmaElectronica;
	}
	public void setFirmaElectronica(FirmaElectronica firmaElectronica) {
		this.firmaElectronica = firmaElectronica;
	}
	
	
	
}
