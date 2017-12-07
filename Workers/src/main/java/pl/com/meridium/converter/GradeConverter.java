package pl.com.meridium.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.com.meridium.entity.Grade;
import pl.com.meridium.repository.GradeRepository;



public class GradeConverter implements Converter<String, Grade>{
	@Autowired
	private GradeRepository gradeRepository;
	
	@Override
	
	public Grade convert(String source) {
		Grade grade=gradeRepository.findOne((long) Integer.parseInt(source));
		return grade;
	}
	

}
