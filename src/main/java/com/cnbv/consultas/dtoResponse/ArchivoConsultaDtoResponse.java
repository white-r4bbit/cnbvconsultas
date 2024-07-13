package com.cnbv.consultas.dtoResponse;

import java.time.ZonedDateTime;


public class ArchivoConsultaDtoResponse {


	private Integer id;
	private String ruta;
	private String nombre;
	private ZonedDateTime fechaCreacion;
	private String tipoElemento;
	private String tipoDocumento;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public ZonedDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(ZonedDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getTipoElemento() {
		return tipoElemento;
	}
	public void setTipoElemento(String tipoElemento) {
		this.tipoElemento = tipoElemento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	
}

	

