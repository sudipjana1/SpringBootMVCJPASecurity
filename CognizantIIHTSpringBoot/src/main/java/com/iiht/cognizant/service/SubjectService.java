package com.iiht.cognizant.service;


import java.util.List;
import java.util.Optional;

import com.iiht.cognizant.model.Subject;

public interface SubjectService {

	public void addSubject(Subject book);
	public Optional<Subject> searchSubjectbyId(long subjectId);
	public boolean deleteSubjectbyId(long subjectId) ;
	public List<Subject> getAllSubjects();
    List<Subject> findSubjectByDuration(int durationInHours);

}

