package com.cnbv.consultas.customGlobalExceptions;

import java.util.List;

public class ErrorResponse {

	private String mensaje;
	private List<String> detalles;

	public ErrorResponse() {
	}

	public ErrorResponse(String mensaje, List<String> detalles) {
		this.mensaje = mensaje;
		this.detalles = detalles;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<String> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<String> detalles) {
		this.detalles = detalles;
	}

}
