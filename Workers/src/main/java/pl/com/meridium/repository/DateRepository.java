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
	
	//Dates findFirstBystatus(int	status);
	
	@Query(value = "select * from dates where date between :date1 and :date2 and status=:status", nativeQuery = true)
	List<Dates> findByDateBetweenAndStatus1(@Param("date1") Date date1, @Param("date2") Date date2, @Param("status") int status);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE dates SET status=:status2 where date between :date1 and :date2 and status=:status", nativeQuery = true)
	public void changeStatusToTwo(@Param("date1") Date date1, @Param("date2") Date date2, @Param("status") int status, @Param("status2") int status2);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE dates SET user_id=:user_id where date between :date1 and :date2 and status=:status", nativeQuery = true)
	public void changeUserId(@Param("date1") Date date1, @Param("date2") Date date2, @Param("status") int status, @Param("user_id") long user_id);

}
