package br.com.sisdepe.api.repository.course;

import java.util.List;

import br.com.sisdepe.api.model.Project;

public interface CourseRepositoryQuery {

	List<Project> findProjectsByTeacher(Long code);
	List<Project> findProjectsByCoordinator(Long code);
}
