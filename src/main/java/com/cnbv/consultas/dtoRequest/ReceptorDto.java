package com.cnbv.consultas.dtoRequest;

import com.cnbv.consultas.models.EntidadExterna;
import com.cnbv.consultas.models.EstatusSolicitudDto;

public class ReceptorDto {

	private String clave;
	private String nombre;
	private boolean esObligatoria;
	private boolean esInterna;
	private EntidadExterna entidadExterna;
	private EstatusSolicitudDto estatus;

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isEsObligatoria() {
		return esObligatoria;
	}

	public void setEsObligatoria(boolean esObligatoria) {
		this.esObligatoria = esObligatoria;
	}

	public boolean isEsInterna() {
		return esInterna;
	}

	public void setEsInterna(boolean esInterna) {
		this.esInterna = esInterna;
	}

	public EntidadExterna getEntidadExterna() {
		return entidadExterna;
	}

	public void setEntidadExterna(EntidadExterna entidadExterna) {
		this.entidadExterna = entidadExterna;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EstatusSolicitudDto getEstatusSolicitudResponse() {
		return estatus;
	}

	public void setEstatusSolicitudResponse(EstatusSolicitudDto estatus) {
		this.estatus = estatus;
	}
}
