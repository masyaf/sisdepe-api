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
import br.com.sisdepe.api.model.User;
import br.com.sisdepe.api.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	
	@Autowired
	private UserService userService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public ResponseEntity<?> all() {
		List<User> users = userService.findAll();
		return !users.isEmpty() ? ResponseEntity.ok(users) : ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<User> create(@Valid @RequestBody User user, HttpServletResponse response) {
		// save user
		User userSaved = userService.save(user);

		publisher.publishEvent(new ResourceCreatedEvent(this, response, userSaved.getCode()));

		return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
	}

	@GetMapping("/{code}")
	public ResponseEntity<User> findByCode(@PathVariable Long code) {
		User user = userService.findOne(code);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();

	}
	@GetMapping("/teachers")
	public ResponseEntity<?> findAllTeachers() {
		List<User> teachers = userService.findAllTeachers();
		return !teachers.isEmpty() ? ResponseEntity.ok(teachers) : ResponseEntity.noContent().build();
	}
	@GetMapping("/coordinators")
	public ResponseEntity<?> findAllCoordinators() {
		List<User> coordinators = userService.findAllCoordinators();
		return !coordinators.isEmpty() ? ResponseEntity.ok(coordinators) : ResponseEntity.noContent().build();
	}
}
