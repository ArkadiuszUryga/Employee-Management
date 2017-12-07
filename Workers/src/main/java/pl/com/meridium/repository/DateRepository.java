package pl.com.meridium.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.meridium.entity.Dates;

public interface DateRepository extends JpaRepository<Dates, Long> {

}
