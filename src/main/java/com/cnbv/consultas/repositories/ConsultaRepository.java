package com.cnbv.consultas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cnbv.consultas.entities.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

	List<Consulta> findByFolioAsunto(String folioAsunto);
	
	
}
