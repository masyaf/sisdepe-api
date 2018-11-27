package br.com.sisdepe.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sisdepe.api.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{


}
