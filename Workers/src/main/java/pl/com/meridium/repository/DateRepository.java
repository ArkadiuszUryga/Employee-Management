package pl.com.meridium.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import pl.com.meridium.entity.Dates;


public interface DateRepository extends JpaRepository<Dates, Long> {

	@Transactional
	 public void deleteBydateAfter(Date expiryDate);
	
//	@Modifying
//    @Transactional
//    @Query("DELETE FROM dates m WHERE m.date > :date")
//    int removeYoungerThan(@Param("date") java.sql.Date date);

	@Query(value = "select * from dates where date between :date1 and :date2", nativeQuery = true)
	List<Dates> findByDateBetweenParam1AndParam2(@Param("date1") Date date1, @Param("date2") Date date2);

}
