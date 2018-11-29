package br.com.sisdepe.api.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity//entidade a ser gerenciada pelo JPA
@Table(name = "course")//anotação que nomeia a tabela no banco de dados como 'course'
public class Course {
	@Id//define o atributo como chave primária
	@SequenceGenerator(name = "course_seq", sequenceName = "course_seq", schema = "public", allocationSize = 1)//gerador de sequencia
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "course_seq")// gera automaticamente os id's ta tabela 
    @Column(nullable = false)//anotação que determina como coluna e não permite que seja nulo o valor atribuído
	private Long code;//chave primária

	@NotEmpty//anotação que determina que não pode ser vazio
	private String name;

	private String description;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//OneToMany - determina uma relação de um para muitos de grades e course 
	private List<Grade> grades;//variável de lista de turmas dentro do curso

	@ManyToMany
	@JoinTable(name = "course_user", joinColumns = @JoinColumn(name = "code_course")
				, inverseJoinColumns = @JoinColumn(name = "code_user"))//existe uma relação de muitos para muitos de course para user
	private Set<User> users;//variável de lista de usuarios dentro do curso
	
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
	//equals e hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@JsonIgnoreProperties({ "password", "isActive", "login" })
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}
