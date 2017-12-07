package pl.com.meridium.converter;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.com.meridium.entity.Dates;
import pl.com.meridium.repository.DateRepository;


public class DateConverter implements Converter<Dates, Date>{

//	@Autowired
//	private DateRepository dateRepository;
//	
	@Override
	public Date convert(Dates source) {
//		Dates dates=dateRepository.findOne((long) Date.parse(source));
		return null;
	}

}
