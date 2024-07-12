package com.cnbv.consultas.dtoRequest;

import java.util.List;

public class FinalizarConsultaDto {

	private int idConsultaReceptor;
	private String comentarios;
	private String finalizadaPor;
	private int secuenciaFirma;
	private String cadenaOriginal;
	private List<ArchivoConsultaDto> archivos;
	public int getIdConsultaReceptor() {
		return idConsultaReceptor;
	}
	public void setIdConsultaReceptor(int idConsultaReceptor) {
		this.idConsultaReceptor = idConsultaReceptor;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public String getFinalizadaPor() {
		return finalizadaPor;
	}
	public void setFinalizadaPor(String finalizadaPor) {
		this.finalizadaPor = finalizadaPor;
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
	public List<ArchivoConsultaDto> getArchivos() {
		return archivos;
	}
	public void setArchivos(List<ArchivoConsultaDto> archivos) {
		this.archivos = archivos;
	}
	
	


	
	
	
}
