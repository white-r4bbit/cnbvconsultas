package com.cnbv.consultas.entities;

import java.time.ZonedDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "Consulta" ,name = "ConsultaReceptor")

public class ConsultaReceptor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id",nullable = false)
	private int id;
	@Column(name = "Clave", nullable = true,length = 50)
	private String clave;
	@Column(name = "Nombre",nullable = true)
	private String nombre;
	@Column(name = "Comentarios",nullable = true)
	private String comentartios;
	@Column(name = "SecuenciaFirma",nullable = true)
	private int secuenciaFirma;
	@Column(name = "CadenaOriginal",nullable = true)
	private String cadenaOriginal;
	@Column(name = "FechaRespuesta", nullable = true)
	private ZonedDateTime fechaRespuesta;
	@Column(name = "Obligatoria",nullable = false)
	private boolean obligatoria;
	@Column(name = "Activa", nullable = false)
	private boolean activa;
	@Column(name = "Interno", nullable = false)
	private boolean interno;
	@Column(name = "FinalizadaPor", nullable = true,length = 100)
	private String nombreFinalizador;
	@ManyToOne
	@JoinColumn(name = "idConsulta", nullable = false)
	private Consulta consulta;
	@Column(name = "IdEnvio",nullable = true,length = 50)
	private String idEnvio;
	@OneToMany(mappedBy ="consultaReceptor", cascade = CascadeType.ALL)
	private List<ArchivoConsulta> archivosReceptor;
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
	public String getComentartios() {
		return comentartios;
	}
	public void setComentartios(String comentartios) {
		this.comentartios = comentartios;
	}
	
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
	public boolean isActiva() {
		return activa;
	}
	public void setActiva(boolean activa) {
		this.activa = activa;
	}
	public boolean isInterno() {
		return interno;
	}
	public void setInterno(boolean interno) {
		this.interno = interno;
	}
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public List<ArchivoConsulta> getArchivosReceptor() {
		return archivosReceptor;
	}
	public void setArchivosReceptor(List<ArchivoConsulta> archivosReceptor) {
		this.archivosReceptor = archivosReceptor;
	}
	public String getNombreFinalizador() {
		return nombreFinalizador;
	}
	public void setNombreFinalizador(String nombreFinalizador) {
		this.nombreFinalizador = nombreFinalizador;
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
