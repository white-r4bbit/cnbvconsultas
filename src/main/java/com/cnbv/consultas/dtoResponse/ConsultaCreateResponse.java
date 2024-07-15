package com.cnbv.consultas.dtoResponse;

import java.util.List;

public class ConsultaCreateResponse {
    private int id;

    private List<Integer> receptores;

    public ConsultaCreateResponse(int id, List<Integer> receptores) {
        this.id = id;
        this.receptores = receptores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getReceptores() {
        return receptores;
    }

    public void setReceptores(List<Integer> receptores) {
        this.receptores = receptores;
    }
}
