package br.com.sisdepe.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity//entidade a ser gerenciada pelo JPA
@Table(name = "grade")//anotação que nomeia a tabela no banco de dados como 'grade'
public class Grade {

	@Id//define o atributo como chave primária
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gradeSequence")//gerador de sequencia para a tabela
	@SequenceGenerator(name = "gradeSequence", sequenceName = "grade_seq", initialValue = 1, allocationSize = 1)// gera automaticamente os id's incrementais da tabela 
	@Column(nullable = false)//anotação que determina como coluna e não permite que seja nulo o valor atribuído
	private Long code;//codigo
	private String name;//nome
	private String period;//periodo
	@Enumerated(EnumType.STRING)//usa um ENUM de strings para determinar o turno
	private Shift shift;
	@ManyToOne
	private Course course; // relação de muitos para um de turmas e cursos
	
	
	
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

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
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
		Grade other = (Grade) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}
