package br.com.sisdepe.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sisdepe.api.model.Grade;
import br.com.sisdepe.api.repository.GradeRepository;

@Service
public class GradeService {

	@Autowired
	private GradeRepository gradeRepository;

	public Grade update(Long code, Grade grade) {
		Grade gradeSaved = gradeRepository.findOne(code);
		if (gradeSaved == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(grade, gradeSaved, "code");
		return gradeRepository.save(gradeSaved);
	}
}
