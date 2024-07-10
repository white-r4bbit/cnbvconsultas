//package com.cnbv.consultas.entities;
//
//import java.time.LocalDateTime;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(schema = "Consulta" ,name = "BitacoraConsulta")
//public class BitacoraConsulta {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "Id")
//	private int id;
//	@Column(name = "FechaEvento",nullable = false)
//	private LocalDateTime fechaEvento;
//	@Column(name = "Descripcion",length = 45,nullable = false)
//	private String descripcion;
//	@ManyToOne
//	@JoinColumn(name = "Consulta_Id",nullable = false)
//	private Consulta consulta;
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public LocalDateTime getFechaEvento() {
//		return fechaEvento;
//	}
//	public void setFechaEvento(LocalDateTime fechaEvento) {
//		this.fechaEvento = fechaEvento;
//	}
//	public String getDescripcion() {
//		return descripcion;
//	}
//	public void setDescripcion(String descripcion) {
//		this.descripcion = descripcion;
//	}
//	public Consulta getConsulta() {
//		return consulta;
//	}
//	public void setConsulta(Consulta consulta) {
//		this.consulta = consulta;
//	}
//	
//	
//	
//
//}
