package com.org.niteco.se.studentmanagementofuniversity.client;



import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.Student;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.StudentOfWorkList;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.User;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface StudentManagementServiceAsync {
	void checkLogin(String user,String pass,AsyncCallback<User> asyncCallback)
			throws IllegalArgumentException;
	
	void getStudents(AsyncCallback<List<StudentOfWorkList>> student) throws IllegalArgumentException;
	
	void removeStudent(List<Integer> ids,AsyncCallback<String> done) throws IllegalArgumentException;
	
	void addStudent(String studentName, String phone,
			String address, String email, String birthday,AsyncCallback<String> validate) throws IllegalArgumentException;
	
	void updateStudent(int id,String studentName, String phone,
			String address, String email, String birthday,AsyncCallback<String> validate) throws IllegalArgumentException;
	
	void searchStudent(String studentName,AsyncCallback<Student> student) throws IllegalArgumentException;
	
	void editStudent(int id,AsyncCallback<Student> student) throws IllegalArgumentException;
}
