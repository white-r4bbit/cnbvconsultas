package com.cnbv.consultas.dtoRequest;

import java.time.ZonedDateTime;

public class ConsultaReceptorDto {

	private int id;
	private String clave;
	private String nombre;
	private boolean esInterna;
	private ZonedDateTime fechaRespuesta;
	private boolean obligatoria;
	private boolean enProceso;
	private String idEnvio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isEsInterna() {
		return esInterna;
	}

	public void setEsInterna(boolean esInterna) {
		this.esInterna = esInterna;
	}

	public ZonedDateTime getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(ZonedDateTime fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	public boolean isObligatoria() {
		return obligatoria;
	}

	public void setObligatoria(boolean obligatoria) {
		this.obligatoria = obligatoria;
	}

	public boolean isEnProceso() {
		return enProceso;
	}

	public void setEnProceso(boolean enProceso) {
		this.enProceso = enProceso;
	}

	public String getIdEnvio() {
		return idEnvio;
	}

	public void setIdEnvio(String idEnvio) {
		this.idEnvio = idEnvio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
