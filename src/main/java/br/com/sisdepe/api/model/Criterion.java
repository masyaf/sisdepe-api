package br.com.sisdepe.api.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity//entidade a ser gerenciada pelo JPA
@Table(name = "criterion")//anotação que nomeia a tabela no banco de dados como 'criterion'
public class Criterion {

	@Id//define o atributo como chave primária
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "criterion_seq", sequenceName = "criterion_seq")//gerador de sequencia
	@GeneratedValue(generator = "criterion_seq", strategy = GenerationType.SEQUENCE)// gera automaticamente os id's ta tabela 
	@Column(nullable = false)//anotação que determina como coluna e não permite que seja nulo o valor atribuído
	private Long code;//chave primária
	private String name;
	private BigDecimal point = BigDecimal.ZERO;
	
	
	
	//getters e setters
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
	public BigDecimal getPoint() {
		return point;
	}
	public void setPoint(BigDecimal point) {
		this.point = point;
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
		Criterion other = (Criterion) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	
	
	
}
