package com.cnbv.consultas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cnbv.consultas.entities.TipoElementoEnum;

public interface TipoElementoRepository extends JpaRepository<TipoElementoEnum, Integer> {

	TipoElementoEnum findByNombre(String nombre);
}
