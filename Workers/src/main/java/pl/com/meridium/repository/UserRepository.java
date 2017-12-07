package pl.com.meridium.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.meridium.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUser(String userName);
}
