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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sisdepe.api.event.ResourceCreatedEvent;
import br.com.sisdepe.api.model.Justification;
import br.com.sisdepe.api.model.Project;
import br.com.sisdepe.api.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectResource {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<Project> create(@Valid @RequestBody Project project, HttpServletResponse response) {
		Project projectSaved = projectService.save(project);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, projectSaved.getCode()));
		return ResponseEntity.status(HttpStatus.CREATED).body(projectSaved);
	}

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Project> projects = null;
		try {
			projects = projectService.findAll();	
		}catch (Exception e) {
		    e.printStackTrace();
		}
		
		return !projects.isEmpty() ? ResponseEntity.ok(projects) : ResponseEntity.noContent().build();
	}

	@PutMapping("/{code}/justifications")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addJustification(@PathVariable Long code, @RequestBody List<Justification> justifications,
			HttpServletResponse response) {
		Project projectSaved = projectService.findByCode(code);
		projectService.addJustification(projectSaved, justifications);
	}

}
