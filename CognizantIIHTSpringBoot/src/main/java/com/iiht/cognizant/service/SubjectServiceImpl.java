package com.iiht.cognizant.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.iiht.cognizant.model.Subject;
import com.iiht.cognizant.repository.SubjectRepository;
@Component
public class SubjectServiceImpl implements SubjectService{
	@Autowired
	SubjectRepository subjectdao;

	@Override
	public void addSubject(Subject subject) {
		subjectdao.save(subject);
	}

	@Override
	public Optional<Subject> searchSubjectbyId(long subjectId) {
		Optional<Subject> subject = null;
		subject = subjectdao.findById(subjectId);
		return subject;
	}
	
	@Override	
	public boolean deleteSubjectbyId(long subjectId) {
		
		if(subjectdao.findById(subjectId) == null) {
			return false;
		}else {
			subjectdao.deleteById(subjectId);
			return true;
		}
		
	}
	
	@Override
	public List<Subject> getAllSubjects() {
		return subjectdao.findAll();
	}

	@Override
	public List<Subject> findSubjectByDuration(int durationInHours) {
		List<Subject> subjects = subjectdao.findSubjectByDuration(durationInHours);
		System.out.println(subjects.toString());
		return subjects;
	}

}

