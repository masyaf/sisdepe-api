package br.com.sisdepe.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sisdepe.api.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{


	List<Project> findByCourseUsersCode(Long code);
	List<Project> findByRequestingCode(Long code);
	
}
