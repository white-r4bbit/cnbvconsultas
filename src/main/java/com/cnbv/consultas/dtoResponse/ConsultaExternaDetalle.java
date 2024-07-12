package com.cnbv.consultas.dtoResponse;

import java.util.List;

public class ConsultaExternaDetalle {

	private String detalle;
	private List<ArchivoConsultaDtoResponse> documentos;
	
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public List<ArchivoConsultaDtoResponse> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<ArchivoConsultaDtoResponse> documentos) {
		this.documentos = documentos;
	}
	
	
}
