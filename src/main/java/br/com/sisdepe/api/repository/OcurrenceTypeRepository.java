package br.com.sisdepe.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sisdepe.api.model.OcurrenceType;

public interface OcurrenceTypeRepository extends JpaRepository<OcurrenceType, Long> {
	//herda de JpaRepository que parametriza o tipo Tipo Ocorrencia e a chave do tipo Long
		//implicitamente os métodos de salvar,alterar, excluir,listar todos e listar por id estão inclusos
}
