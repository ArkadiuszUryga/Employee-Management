package pl.com.meridium.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.com.meridium.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUser(String userName);
//	@Query("Select u from users u where u.ranga ='1' ")
//	List<User> findByranga();
	
	List<User> findByranga(int ranga);
}
