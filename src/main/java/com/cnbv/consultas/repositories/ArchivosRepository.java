package com.cnbv.consultas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cnbv.consultas.entities.ArchivoConsulta;

public interface ArchivosRepository extends JpaRepository<ArchivoConsulta, Integer> {

	@Query(value="SELECT c FROM ArchivoConsulta c WHERE c.consultaId =:id")
	List<ArchivoConsulta> findByIdConsulta(@Param("id") Integer id);
	
	@Query(value="SELECT c FROM ArchivoConsulta c WHERE c.consultaReceptor =:id")
	List<ArchivoConsulta> findByIdReceptor(@Param("id") Integer id);
	
	
}
