package br.com.sisdepe.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sisdepe.api.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long>{

}
