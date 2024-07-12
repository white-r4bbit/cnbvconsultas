package com.cnbv.consultas.entities;

public class ActualizarAsuntoRequest {
    private String FolioAsunto;

    private int IdEstatusAsungo;

    private String Accion;

    public String getFolioAsunto() {
		return FolioAsunto;
	}
	public void setFolioAsunto(String folioAsunto) {
		this.FolioAsunto = folioAsunto;
	}


    public int getIdEstatusAsunto() {
		return IdEstatusAsungo;
	}
	public void setIdEstatusAsunto(int idEstatusAsungo) {
		this.IdEstatusAsungo = idEstatusAsungo;
	}
    
    public String getAccion() {
		return Accion;
	}
	public void setAccion(String Accion) {
		this.Accion = Accion;
	}
}
