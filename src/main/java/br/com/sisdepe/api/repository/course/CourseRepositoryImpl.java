package br.com.sisdepe.api.repository.course;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import br.com.sisdepe.api.model.Project;
import br.com.sisdepe.api.model.User;

public class CourseRepositoryImpl implements CourseRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Project> findProjectsByTeacher(Long code) {

		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);

		Root<User> userRoot = criteriaQuery.from(User.class);
		Join<Project, User> joinProjectsUsers = userRoot.join("projects");
		
		return manager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Project> findProjectsByCoordinator(Long code) {
		return null;
	}

}
