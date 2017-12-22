package pl.com.meridium.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.com.meridium.entity.Holidays;

public interface HolidaysRepository extends JpaRepository<Holidays, Long> {
	@Query("Select h from Holidays h where h.date like %?1%")
	List<Holidays>	findAllByMonth(String	monthNamed);
}
