package br.com.sisdepe.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sisdepe.api.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
