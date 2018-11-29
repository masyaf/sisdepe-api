package br.com.sisdepe.api.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity//entidade a ser gerenciada pelo JPA
@Table(name = "project")//anotação que nomeia a tabela no banco de dados como 'project'
public class Project {

	@Id//define o atributo como chave primária
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "project_seq", sequenceName = "project_seq")//gerador de sequencia para a tabela
	@GeneratedValue(generator = "project_seq", strategy = GenerationType.TABLE)// gera automaticamente os id's incrementais da tabela 
	@Column(nullable = false)//anotação que determina como coluna e não permite que seja nulo o valor atribuído
	private Long code;

	@NotEmpty // anotação que impede que seja feito um `bind` de valores vazios neste campo
	private String name;

	@NotEmpty  // anotação que impede que seja feito um `bind` de valores vazios neste campo
	private String summary;

	@Column(name = "created_at")//atributo que pega a data atual com padrão local
	private LocalDate createdAt = LocalDate.now();

	@Column(name = "requested_date")// data de requisição
	private LocalDate requestedDate;

	private Status status;
	
	@OneToMany(cascade = CascadeType.ALL)// relação de um para muitos de criterios para projeto, um projeto tem muitos criterios
	@LazyCollection(LazyCollectionOption.FALSE)//evita o erro de lazyException, ao carregar os projetos ele nao carrega todas os criterios e vice-versa
	private List<Criterion> criterions;

	@OneToMany(cascade = CascadeType.ALL)// relação de um para muitos de criterios para projeto, um projeto tem muitos criterios
	@LazyCollection(LazyCollectionOption.FALSE)//evita o erro de lazyException, ao carregar os projetos ele nao carrega todas as justificativas e vice-versa
	private Set<Justification> justifications;
	
    @OneToOne// relação de um para um de um usuario requisitando um projeto
	private User requesting;

	@OneToOne // relação de um para um de uma turma para um projeto
	private Grade grade;

	@OneToOne // relação de um para um de um curso requisitando um projeto
	private Course course;
	
	
	
	public Project() {

	}
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;

	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Criterion> getCriterions() {
		return criterions;
	}

	public void setCriterions(List<Criterion> criterions) {
		this.criterions = criterions;
	}


	public Set<Justification> getJustifications() {
		return justifications;
	}

	public void setJustifications(Set<Justification> justifications) {
		this.justifications = justifications;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {

		this.grade = grade;
	}
	



	@JsonIgnoreProperties({ "grades" })
	public Course getCourse() {
		return course;
	}

	@JsonIgnoreProperties({ "password", "active", "login" })
	public User getRequesting() {
		return requesting;
	}

	public void setRequesting(User requesting) {
		this.requesting = requesting;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public LocalDate getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(LocalDate requestedDate) {
		this.requestedDate = requestedDate;
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
		Project other = (Project) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}
