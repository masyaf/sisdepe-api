package br.com.sisdepe.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sisdepe.api.model.User;
import br.com.sisdepe.api.model.UserType;

public interface UserRepository extends JpaRepository<User, Long>{
	//herda de JpaRepository que parametriza o tipo Ocorrencia e a chave do tipo Long
			//implicitamente os métodos de salvar,alterar, excluir,listar todos e listar por id estão inclusos
	List<User> findByType(UserType type);//lista de usuario filtrado pelo tipo
	
}
