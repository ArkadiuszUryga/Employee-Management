package pl.com.meridium.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import pl.com.meridium.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUser(String userName);
//	@Query("Select u from users u where u.ranga ='1' ")
//	List<User> findByranga();
	
	List<User> findByranga(int ranga);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET hoursForNextMonth=:hours where id=:id", nativeQuery = true)
	public void setHoursNext(@Param("hours") int hours, @Param("id") long id);
}
