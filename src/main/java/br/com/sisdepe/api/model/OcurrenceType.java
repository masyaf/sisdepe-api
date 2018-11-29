package br.com.sisdepe.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity//entidade a ser gerenciada pelo JPA
@Table(name = "ocurrence_type")//anotação que nomeia a tabela no banco de dados como 'ocurrency_type'
public class OcurrenceType {
	@Id//define o atributo como chave primária
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "ocurrence_type_seq", sequenceName = "ocurrence_type_seq")//gerador de sequencia para a tabela
	@GeneratedValue(generator = "ocurrence_type_seq", strategy = GenerationType.SEQUENCE)// gera automaticamente os id's incrementais da tabela 
	@Column(nullable = false)//anotação que determina como coluna e não permite que seja nulo o valor atribuído
	private Long code;
	@NotNull //anotação que garante que que não seja persistido um dado nulo neste campo
	@Column(nullable = false)//também garante que é uma coluna no banco e que não pode ser nula.
	private String name;
	//d
	
	//getters e setters
	public OcurrenceType() {
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
		OcurrenceType other = (OcurrenceType) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}
