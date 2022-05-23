package br.com.etechoracio.boa_viagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import br.com.etechoracio.boa_viagem.entity.Gasto;

public interface GastoRepository extends JpaRepository<Gasto, Long> {

	List<Gasto> findByViagemId(Long id);
	
}
