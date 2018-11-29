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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sisdepe.api.event.ResourceCreatedEvent;
import br.com.sisdepe.api.model.Course;
import br.com.sisdepe.api.model.Grade;
import br.com.sisdepe.api.repository.CourseRepository;
import br.com.sisdepe.api.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseResource {

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public ResponseEntity<?> all() {
		List<Course> courses = courseRepository.findAll();
		return !courses.isEmpty() ? ResponseEntity.ok(courses) : ResponseEntity.noContent().build();
	}

	@GetMapping("/{code}/users")
	public ResponseEntity<?> findCourseByUserCode(@PathVariable Long code) {
		List<Course> courses = courseRepository.findByUsersCode(code);
		return !courses.isEmpty() ? ResponseEntity.ok(courses) : ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Course> create(@Valid @RequestBody Course course, HttpServletResponse response) {
		Course courseSaved = courseService.save(course);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, course.getCode()));
		return ResponseEntity.status(HttpStatus.CREATED).body(courseSaved);

	}

	@GetMapping("/{code}")
	public ResponseEntity<Course> findByCode(@PathVariable Long code) {
		Course course = courseService.findByCode(code);
		return course != null ? ResponseEntity.ok(course) : ResponseEntity.notFound().build();
	}

	@PostMapping("/{code}/grades")
	public ResponseEntity<Course> addGrades(@PathVariable Long code, @Valid @RequestBody List<Grade> grades,
			HttpServletResponse response) {
		Course courseSaved = courseService.findByCode(code);
		courseService.addGradeToCourse(courseSaved, grades);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, code));
		return ResponseEntity.status(HttpStatus.CREATED).body(courseSaved);
	}

}
