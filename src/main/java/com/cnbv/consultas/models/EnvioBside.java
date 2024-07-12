package com.cnbv.consultas.models;

import java.util.List;

public class EnvioBside {

	private List<Documento> documentos;
	private String idCategoria;
	private String categoria;
	private String idSubcategoria;
	private String subcategoria;
	private Boolean permitirCargaArchivosAdicionales;
	private String areaResponsable;
	private int idAreaResponsable;
	private String id;
	private String nombre;
	private String descripcion;
	private String folioCrearAsunto;
	private int idAsunto;
	
	public List<Documento> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
	public String getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getIdSubcategoria() {
		return idSubcategoria;
	}
	public void setIdSubcategoria(String idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}
	public String getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}
	public Boolean getPermitirCargaArchivosAdicionales() {
		return permitirCargaArchivosAdicionales;
	}
	public void setPermitirCargaArchivosAdicionales(Boolean permitirCargaArchivosAdicionales) {
		this.permitirCargaArchivosAdicionales = permitirCargaArchivosAdicionales;
	}
	public String getAreaResponsable() {
		return areaResponsable;
	}
	public void setAreaResponsable(String areaResponsable) {
		this.areaResponsable = areaResponsable;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getIdAreaResponsable() {
		return idAreaResponsable;
	}
	public void setIdAreaResponsable(int idAreaResponsable) {
		this.idAreaResponsable = idAreaResponsable;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFolioCrearAsunto() {
		return folioCrearAsunto;
	}
	public void setFolioCrearAsunto(String folioCrearAsunto) {
		this.folioCrearAsunto = folioCrearAsunto;
	}
	public int getIdAsunto() {
		return idAsunto;
	}
	public void setIdAsunto(int idAsunto) {
		this.idAsunto = idAsunto;
	}

	
	
}
