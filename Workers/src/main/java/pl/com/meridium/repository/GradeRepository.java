package pl.com.meridium.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.meridium.entity.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {

}
