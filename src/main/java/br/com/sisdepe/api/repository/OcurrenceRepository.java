package br.com.sisdepe.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sisdepe.api.model.Ocurrence;

public interface OcurrenceRepository extends JpaRepository<Ocurrence, Long>{

}
