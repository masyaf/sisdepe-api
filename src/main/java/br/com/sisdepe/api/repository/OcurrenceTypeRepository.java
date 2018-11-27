package br.com.sisdepe.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sisdepe.api.model.OcurrenceType;

public interface OcurrenceTypeRepository extends JpaRepository<OcurrenceType, Long> {

}
