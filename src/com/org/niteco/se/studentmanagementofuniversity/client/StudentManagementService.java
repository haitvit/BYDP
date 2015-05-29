package com.org.niteco.se.studentmanagementofuniversity.client;



import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.Student;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.StudentOfWorkList;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.User;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("student")
public interface StudentManagementService extends RemoteService {
	User checkLogin(String User , String pass) throws IllegalArgumentException;
	
	List<StudentOfWorkList> getStudents() throws IllegalArgumentException;
	
	String removeStudent(List<Integer> ids) throws IllegalArgumentException;
	
	String addStudent(String studentName, String phone,String address, String email, String birthday) throws IllegalArgumentException;
	
	String updateStudent(int id,String studentName, String phone,String address, String email, String birthday) throws IllegalArgumentException;
	
	Student searchStudent(String username) throws IllegalArgumentException;
	
	Student editStudent(int id) throws IllegalArgumentException;
}
