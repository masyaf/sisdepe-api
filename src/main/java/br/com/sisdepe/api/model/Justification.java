package br.com.sisdepe.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity//entidade a ser gerenciada pelo JPA
@Table(name = "justification")//anotação que nomeia a tabela no banco de dados como 'justification'
public class Justification {
	@Id//define o atributo como chave primária
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "justification_seq", sequenceName = "justification_seq")//gerador de sequencia para a tabela
	@GeneratedValue(generator = "justification_seq", strategy = GenerationType.SEQUENCE)// gera automaticamente os id's incrementais da tabela 
	@Column(nullable = false)//anotação que determina como coluna e não permite que seja nulo o valor atribuído
	private Long code;
	private String description;

	@Column(name = "created_at")
	private LocalDate createdAt = LocalDate.now();//pega o padrão de datas e a hora atual para a geracão ao salvar no banco
	//getters e setters
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	//equals e hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Justification other = (Justification) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}
