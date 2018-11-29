package br.com.sisdepe.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sisdepe.api.model.Justification;
import br.com.sisdepe.api.model.Project;
import br.com.sisdepe.api.model.UserType;
import br.com.sisdepe.api.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project findByCode(Long code) {
		return projectRepository.findOne(code);
	}

	public Project save(Project project) {
		return projectRepository.save(project);
	}

	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	public Project addJustification(Project project, List<Justification> justifications) {
		project.getJustifications().addAll(justifications);
		return projectRepository.save(project);
		
	}

	public List<Project> findByCourseUsersCodeAndCourseUsersType(Long code, UserType type) {
		
		return projectRepository.findByCourseUsersCodeAndCourseUsersType(code, type);
	}
	public List<Project> findByRequestingCode(Long code){
		return projectRepository.findByRequestingCode(code);
	}

}
