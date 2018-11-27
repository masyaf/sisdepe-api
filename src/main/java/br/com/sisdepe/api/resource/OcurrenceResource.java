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
import br.com.sisdepe.api.model.Ocurrence;
import br.com.sisdepe.api.repository.OcurrenceRepository;

@RestController
@RequestMapping("/ocurrences")
public class OcurrenceResource {

	@Autowired
	private OcurrenceRepository ocurrenceRepository;
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Ocurrence> ocurrence = ocurrenceRepository.findAll();
		return !ocurrence.isEmpty() ? ResponseEntity.ok(ocurrence) : ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Ocurrence> create(@Valid @RequestBody Ocurrence ocurrence,
			HttpServletResponse response) {
		Ocurrence ocurenceSaved = ocurrenceRepository.save(ocurrence);

		publisher.publishEvent(new ResourceCreatedEvent(this, response, ocurenceSaved.getCode()));

		return ResponseEntity.status(HttpStatus.CREATED).body(ocurenceSaved);

	}
	@GetMapping("/{code}")
	public ResponseEntity<Ocurrence> findByCode(@PathVariable Long code) {
		Ocurrence ocurrence = ocurrenceRepository.findOne(code);
		 return ocurrence != null ? ResponseEntity.ok(ocurrence) : ResponseEntity.notFound().build();
	}
}
