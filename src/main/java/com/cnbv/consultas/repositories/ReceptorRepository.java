package com.cnbv.consultas.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cnbv.consultas.entities.Consulta;
import com.cnbv.consultas.entities.ConsultaReceptor;

public interface ReceptorRepository extends JpaRepository<ConsultaReceptor, Integer> {

	List<ConsultaReceptor> findByConsulta(Consulta consulta);
	Optional<ConsultaReceptor> findByIdEnvio(String idEnvio);

	@Query("SELECT COUNT (e.idEnvio) from ConsultaReceptor e")
	int listIdEnvio();

	List<ConsultaReceptor> findByEstatusSolicitud(String estatusSolicitud);

	@Query("SELECT r FROM ConsultaReceptor r WHERE r.estatusSolicitud = :estatusSolicitud AND r.firmante = :firmante")
	List<ConsultaReceptor> findByEstatusSolicitudAndFirmante(String estatusSolicitud, String firmante);
}
