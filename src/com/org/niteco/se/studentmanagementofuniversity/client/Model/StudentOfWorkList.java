package com.org.niteco.se.studentmanagementofuniversity.client.Model;

import java.io.Serializable;

public class StudentOfWorkList implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Student student;
	private String subjectName;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}
