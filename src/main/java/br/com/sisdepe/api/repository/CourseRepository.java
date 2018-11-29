package br.com.sisdepe.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sisdepe.api.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {//herda de JpaRepository que parametriza o tipo Curso e a chave do tipo Long
	//implicitamente os métodos de salvar,alterar, excluir,listar todos e listar por id estão inclusos

	
	 List<Course>  findByUsersCode(Long code);//metodo especifico para encontrar cursosp pelo codigo de usuario
}
