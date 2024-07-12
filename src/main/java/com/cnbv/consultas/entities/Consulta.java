package com.cnbv.consultas.entities;

import java.time.ZonedDateTime;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "Consulta", name = "Consulta")

public class Consulta {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "FolioAsunto", length = 100, nullable = false)
	private String folioAsunto;
	@Column(name = "FechaSolicitud", nullable = false)
	private ZonedDateTime fechaSolicitud;
	@Column(name = "Detalle", nullable = false)
	private String detalle;
	@Column(name = "Activa", nullable = false)
	private boolean activa;
	@Column(name = "Version", nullable = false)
	private int version;
	@Column(name = "SecuenciaFirma", nullable = false)
	private int secuenciaFirma;
	@Column(name = "CadenaOriginal",nullable = false)
	private String cadenaOriginal;
	@OneToMany(mappedBy = "consultaId", cascade = CascadeType.ALL)
	private List<ArchivoConsulta> archivoConsulta;
	@OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
	private List<ConsultaReceptor> consultaReceptores;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFolioAsunto() {
		return folioAsunto;
	}

	public void setFolioAsunto(String folioAsunto) {
		this.folioAsunto = folioAsunto;
	}

	public ZonedDateTime getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(ZonedDateTime fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<ArchivoConsulta> getArchivoConsulta() {
		return archivoConsulta;
	}

	public void setArchivoConsulta(List<ArchivoConsulta> archivoConsulta) {
		this.archivoConsulta = archivoConsulta;
	}

	public List<ConsultaReceptor> getConsultaReceptores() {
		return consultaReceptores;
	}

	public void setConsultaReceptores(List<ConsultaReceptor> consultaReceptores) {
		this.consultaReceptores = consultaReceptores;
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

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

}
