package br.com.sisdepe.api.repository.course;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.sisdepe.api.model.Project;

public class CourseRepositoryImpl implements CourseRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Project> findProjectsByTeacher(Long code) {

		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
		Root<Project> root = criteriaQuery.from(Project.class);

//		criteriaQuery.select(criteriaBuilder.construct(Project.class, root.get(Lancamento_.tipo),
//				root.get(Lancamento_.pessoa), criteriaBuilder.sum(root.get(Lancamento_.valor))));

		return null;
	}

	@Override
	public List<Project> findProjectsByCoordinator(Long code) {
		return null;
	}

}
