package br.com.sisdepe.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sisdepe.api.event.ResourceCreatedEvent;
import br.com.sisdepe.api.model.Course;
import br.com.sisdepe.api.model.Grade;
import br.com.sisdepe.api.repository.CourseRepository;
import br.com.sisdepe.api.service.CourseService;
import br.com.sisdepe.api.service.GradeService;

@RestController//define como um controller de padrão rest
@RequestMapping("/courses")// mapeia a partir da rota /courses/...
public class CourseResource {

	@Autowired//injeção de dependencia do serviço de cursos
	private CourseService courseService;
	
	@Autowired
	private GradeService gradeService;

	@Autowired//injeção de dependencia do repositório de cursos
	private CourseRepository courseRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping//rota /courses/
	public ResponseEntity<?> all() {
		List<Course> courses = courseRepository.findAll();//recebe todos os cursos cadastrados
		return !courses.isEmpty() ? ResponseEntity.ok(courses) : ResponseEntity.noContent().build();//verifica se o array não está nulo e retorna como uma resposta
	}

	@GetMapping("/{code}/users")//rota /courses/users
	public ResponseEntity<?> findCourseByUserCode(@PathVariable Long code) {//parameto do codigo de usuario
		List<Course> courses = courseRepository.findByUsersCode(code); //recebe todos os cursos cadastrados pelos codigos de usuarios
		return !courses.isEmpty() ? ResponseEntity.ok(courses) : ResponseEntity.noContent().build(); //verifica se o array não está nulo e retorna como uma resposta
	}

	@PostMapping//post para /courses
	public ResponseEntity<Course> create(@Valid @RequestBody Course course, HttpServletResponse response) {
		Course courseSaved = courseService.save(course); //recebe todos os cursos cadastrados pelos codigos de usuarios
		publisher.publishEvent(new ResourceCreatedEvent(this, response, course.getCode()));
		return ResponseEntity.status(HttpStatus.CREATED).body(courseSaved);//retorna um curso criado como corpo da requisição

	}

	@GetMapping("/{code}")//   /courses/id
	public ResponseEntity<Course> findByCode(@PathVariable Long code) {//id do curso como parametro
		Course course = courseService.findByCode(code);// encontra um unico curso pelo id do memso
		return course != null ? ResponseEntity.ok(course) : ResponseEntity.notFound().build();// retorna o corpo da requisicao com os cursos
	}

	@PostMapping("/{code}/grades")//  courses/id/grades  via post
	@PutMapping("/{code}/grades")
	public ResponseEntity<Course> addGrades(@PathVariable Long code, @Valid @RequestBody List<Grade> grades,
			HttpServletResponse response) {
		Course courseSaved = courseService.findByCode(code);// retorna todas as turmas de um curso
		courseService.addGradeToCourse(courseSaved, grades);//adiciona turma a um curso
		publisher.publishEvent(new ResourceCreatedEvent(this, response, code));
		return ResponseEntity.status(HttpStatus.CREATED).body(courseSaved);
	}
	@PutMapping("/grades/{code}")
	public ResponseEntity<Grade> updateGrades(@PathVariable Long code , @Valid @RequestBody Grade grade,
			HttpServletResponse response) {
		
		Grade gradeSaved = gradeService.update(code, grade);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, grade.getCode()));
		return ResponseEntity.status(HttpStatus.CREATED).body(gradeSaved);
	}

}
