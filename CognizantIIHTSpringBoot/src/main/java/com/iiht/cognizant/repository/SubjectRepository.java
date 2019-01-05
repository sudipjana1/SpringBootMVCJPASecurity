package com.iiht.cognizant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iiht.cognizant.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
	@Query("SELECT s FROM Subject s WHERE " +
            "LOWER(s.durationInHours) = :durationInHours" )
    List<Subject> findSubjectByDuration(@Param("durationInHours") int durationInHours);

}
