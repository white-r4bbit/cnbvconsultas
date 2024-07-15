package com.cnbv.consultas.entities;

import java.time.ZonedDateTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// FALTA AGREGAR OBJETOS DE LA RELACION
@Entity
@Table(schema = "Consulta" ,name = "ArchivoConsulta")
public class ArchivoConsulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;
	@Column(name = "Ruta",length = 1000, nullable = false)
	private String ruta;
	@Column(name = "FechaCreacion",nullable = false)
	private ZonedDateTime fechaCreacion;
	@ManyToOne
	@JoinColumn(name = "IdConsulta",nullable = true)
	private Consulta consultaId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "IdTipoDocumento",nullable = false)
	private TipoDocumentoEnum tipoDocumentoEnum;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "IdTipoElemento",nullable = false)
	private TipoElementoEnum tipoElementoEnum;
	@ManyToOne
	@JoinColumn(name = "IdReceptor",nullable = true)
	private ConsultaReceptor consultaReceptor;
	@Column(name = "Nombre",length = 1000,nullable = false)
	private String nombre;
	@Column(name = "Eliminado",nullable = true)
	
	private Boolean eliminado;
	
	public Boolean getEliminado() {
		return eliminado;
	}
	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public ZonedDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(ZonedDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Consulta getConsultaId() {
		return consultaId;
	}
	public void setConsultaId(Consulta consultaId) {
		this.consultaId = consultaId;
	}
	public TipoDocumentoEnum getTipoDocumentoEnum() {
		return tipoDocumentoEnum;
	}
	public void setTipoDocumentoEnum(TipoDocumentoEnum tipoDocumentoEnum) {
		this.tipoDocumentoEnum = tipoDocumentoEnum;
	}
	public TipoElementoEnum getTipoElementoEnum() {
		return tipoElementoEnum;
	}
	public void setTipoElementoEnum(TipoElementoEnum tipoElementoEnum) {
		this.tipoElementoEnum = tipoElementoEnum;
	}
	public ConsultaReceptor getConsultaReceptor() {
		return consultaReceptor;
	}
	public void setConsultaReceptor(ConsultaReceptor consultaReceptor) {
		this.consultaReceptor = consultaReceptor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
