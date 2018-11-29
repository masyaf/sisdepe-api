package br.com.sisdepe.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sisdepe.api.model.Project;
import br.com.sisdepe.api.model.UserType;

public interface ProjectRepository extends JpaRepository<Project, Long>{


	List<Project> findByCourseUsersCodeAndCourseUsersType(Long code, UserType type);
	List<Project> findByRequestingCode(Long code);
	
}
