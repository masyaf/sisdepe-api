package br.com.sisdepe.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sisdepe.api.model.User;
import br.com.sisdepe.api.model.UserType;

public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findByType(UserType type);
	
}
