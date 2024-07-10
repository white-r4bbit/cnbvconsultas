package com.cnbv.consultas.dtoRequest;

import java.time.ZonedDateTime;
import java.util.List;

import com.cnbv.consultas.dtoResponse.ArchivoConsultaDtoResponse;

public class ConsultaReceptorDetalleDto {
	
	private int id;
	private String clave;
	private String nombre;
	private String finalizadaPor;
	private String comentarios;
	private ZonedDateTime fechaRespuesta;
	private String idEnvio;
	private List<ArchivoConsultaDtoResponse> archivos;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFinalizadaPor() {
		return finalizadaPor;
	}
	public void setFinalizadaPor(String finalizadaPor) {
		this.finalizadaPor = finalizadaPor;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	public ZonedDateTime getFechaRespuesta() {
		return fechaRespuesta;
	}
	public void setFechaRespuesta(ZonedDateTime fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}
	public List<ArchivoConsultaDtoResponse> getArchivos() {
		return archivos;
	}
	public void setArchivos(List<ArchivoConsultaDtoResponse> archivos) {
		this.archivos = archivos;
	}
	public String getIdEnvio() {
		return idEnvio;
	}
	public void setIdEnvio(String idEnvio) {
		this.idEnvio = idEnvio;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
