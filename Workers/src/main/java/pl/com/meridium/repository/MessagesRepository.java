package pl.com.meridium.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.meridium.entity.Messages;



public interface MessagesRepository extends JpaRepository<Messages, Long> {

}
