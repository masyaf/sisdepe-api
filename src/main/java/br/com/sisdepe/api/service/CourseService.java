package br.com.sisdepe.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sisdepe.api.model.Course;
import br.com.sisdepe.api.model.Grade;
import br.com.sisdepe.api.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public Course update(Long code, Course course) {
		Course courseSave = findByCode(code);
		BeanUtils.copyProperties(course, courseSave, "code");
		return courseRepository.save(courseSave);
	}

	public void addGradeToCourse(Course courseSaved, List<Grade> grades) {
		courseSaved.getGrades().addAll(grades);
		courseRepository.save(courseSaved);
	}

	public Course findByCode(Long code) {
		Course course = courseRepository.findOne(code);
		if (course == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return course;
	}

	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	public Course save(Course course) {
		return courseRepository.save(course);
	}
}
