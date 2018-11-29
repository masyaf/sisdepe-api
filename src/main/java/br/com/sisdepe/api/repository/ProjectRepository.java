package br.com.sisdepe.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sisdepe.api.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
	//herda de JpaRepository que parametriza o tipo Ocorrencia e a chave do tipo Long
		//implicitamente os métodos de salvar,alterar, excluir,listar todos e listar por id estão inclusos
 
	List<Project> findByCourseUsersCode(Long code);//lista de projetos pelo curso e codigo de usuario
	List<Project> findByRequestingCode(Long code);// lista de projetos pelo id do requisitante
	
}
