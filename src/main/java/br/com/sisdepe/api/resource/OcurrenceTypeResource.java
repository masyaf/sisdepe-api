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
@RequestMapping("/ocurrencetypes")//mapeia a partir de /ocurrencetypes
public class OcurrenceTypeResource {

	@Autowired
	private OcurrenceTypeRepository ocurrenceTypeRepository;//injeção de depedencias
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public ResponseEntity<?> findAll() {//retorna todos os tipos de ocorrencias cadastrados
		List<OcurrenceType> ocurrenceTypes = ocurrenceTypeRepository.findAll();//lista todas as ocorrencias do banco
		return !ocurrenceTypes.isEmpty() ? ResponseEntity.ok(ocurrenceTypes) : ResponseEntity.noContent().build();
	}
	@PostMapping//post para a /ocurrencetypes
	public ResponseEntity<OcurrenceType> create(@Valid @RequestBody OcurrenceType ocurrenceType,
			HttpServletResponse response) {
		OcurrenceType ocurrenceTypeSaved = ocurrenceTypeRepository.save(ocurrenceType);//cadastra no banco um objeto ocurrenceType
 
		publisher.publishEvent(new ResourceCreatedEvent(this, response, ocurrenceTypeSaved.getCode()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ocurrenceTypeSaved);

	}
	@GetMapping("/{code}")// retorna uma ocorrencia pelo id via get
	public ResponseEntity<OcurrenceType> findByCode(@PathVariable Long code) {//parametro id
		OcurrenceType ocurrenceType = ocurrenceTypeRepository.findOne(code);//encontra um unico tipo de ocorrencia pelo id.
		 return ocurrenceType != null ? ResponseEntity.ok(ocurrenceType) : ResponseEntity.notFound().build();
	}
}
