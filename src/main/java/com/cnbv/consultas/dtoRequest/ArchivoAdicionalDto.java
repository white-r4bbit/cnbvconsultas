package com.cnbv.consultas.dtoRequest;

import java.util.List;

public class ArchivoAdicionalDto {

	private int id;
	List<ArchivoConsultaDto> archivos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ArchivoConsultaDto> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<ArchivoConsultaDto> archivos) {
		this.archivos = archivos;
	}

}
