package br.com.sisdepe.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sisdepe.api.model.User;
import br.com.sisdepe.api.model.UserType;
import br.com.sisdepe.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAllTeachers() {
		List<User> teachers = userRepository.findByType(UserType.TEACHER);
		return teachers;
	}

	public List<User> findAllCoordinators() {
		List<User> coordinators = userRepository.findByType(UserType.COORDINATOR);
		return coordinators;
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User findOne(Long code) {
		return userRepository.findOne(code);
	}

}
