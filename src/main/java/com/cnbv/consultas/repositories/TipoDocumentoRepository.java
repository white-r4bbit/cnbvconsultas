package com.cnbv.consultas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cnbv.consultas.entities.TipoDocumentoEnum;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEnum, Integer> {

	TipoDocumentoEnum findByNombre(String nombre);

	
}
