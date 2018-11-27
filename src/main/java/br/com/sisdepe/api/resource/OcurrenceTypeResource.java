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
import br.com.sisdepe.api.model.OcurrenceType;
import br.com.sisdepe.api.repository.OcurrenceTypeRepository;

@RestController
@RequestMapping("/ocurrencetypes")
public class OcurrenceTypeResource {

	@Autowired
	private OcurrenceTypeRepository ocurrenceTypeRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<OcurrenceType> ocurrenceTypes = ocurrenceTypeRepository.findAll();
		return !ocurrenceTypes.isEmpty() ? ResponseEntity.ok(ocurrenceTypes) : ResponseEntity.noContent().build();
	}
	@PostMapping
	public ResponseEntity<OcurrenceType> create(@Valid @RequestBody OcurrenceType ocurrenceType,
			HttpServletResponse response) {
		OcurrenceType ocurrenceTypeSaved = ocurrenceTypeRepository.save(ocurrenceType);
 
		publisher.publishEvent(new ResourceCreatedEvent(this, response, ocurrenceTypeSaved.getCode()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ocurrenceTypeSaved);

	}
	@GetMapping("/{code}")
	public ResponseEntity<OcurrenceType> findByCode(@PathVariable Long code) {
		OcurrenceType ocurrenceType = ocurrenceTypeRepository.findOne(code);
		 return ocurrenceType != null ? ResponseEntity.ok(ocurrenceType) : ResponseEntity.notFound().build();
	}
}
