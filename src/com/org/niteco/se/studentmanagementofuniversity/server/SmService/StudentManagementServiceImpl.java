package com.org.niteco.se.studentmanagementofuniversity.server.SmService;

import java.sql.Connection;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.org.niteco.se.studentmanagementofuniversity.client.StudentManagementService;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.Student;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.StudentOfWorkList;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.User;
import com.org.niteco.se.studentmanagementofuniversity.server.Connection.Connect;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class StudentManagementServiceImpl extends RemoteServiceServlet
		implements StudentManagementService {
	
	Connect connection = new Connect();

	@Override
	public User checkLogin(String username, String password)
			throws IllegalArgumentException {

		String sqlCheckLogin = "select * from User where userName ='"
				+ username + "' and password ='" + password + "' ";

		try {
			Connection conn = connection.getConn();
			Statement select = conn.createStatement();
			ResultSet result = select.executeQuery(sqlCheckLogin);
			while (result.next()) {
				User user = new User();
				user.setUserName(result.getString("userName"));
				return user;
			}
			select.close();
			result.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Mysql Statement Error: " + sqlCheckLogin);
			e.printStackTrace();
		}

		return null;
	}

	public String removeStudent(List<Integer> ids) {
		
		String done = "done";
		
		for (int i = 0; i < ids.size(); i++) {
			String sqlRemoveStudent = "delete from Student where StudentId = "
					+ ids.get(i);

			try {
				Connection conn = connection.getConn();
				Statement remove = conn.createStatement();
				remove.executeUpdate(sqlRemoveStudent);
				return done;
			} catch (SQLException e) {
				System.err
						.println("Mysql Statement Error: " + sqlRemoveStudent);
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Student editStudent(int id) {
		
		String sqlSelectStudent = "select * from Student where studentId = " + id;
		
		try {
			Connection conn = connection.getConn();
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sqlSelectStudent);
			
			while (result.next()) {
				Student student = new Student();
				
				student.setStudentName(result.getString("studentName"));
				student.setId(result.getInt("StudentId"));
				student.setAddress(result.getString("address"));
				student.setBirthDay(result.getString("birthday"));
				student.setPhone(result.getString("phone"));
				student.setEmail(result.getString("email"));
				
				return student;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public List<StudentOfWorkList> getStudents() {

		String sqlSelectStudents = "select studentId,studentName,address,birthday,phone,email,subjectName from Student left join Subject on Student.subjectId_fk = Subject.subjectId";
		try {

			Connection conn = connection.getConn();
			Statement select = conn.createStatement();
			ResultSet result = select.executeQuery(sqlSelectStudents);

			List<StudentOfWorkList> students = new ArrayList<StudentOfWorkList>();
			while (result.next()) {
				StudentOfWorkList studentOfWorkList = new StudentOfWorkList();
				Student student = new Student();

				student.setStudentName(result.getString("studentName"));
				student.setId(result.getInt("StudentId"));
				student.setAddress(result.getString("address"));
				student.setBirthDay(result.getString("birthday"));
				student.setPhone(result.getString("phone"));
				student.setEmail(result.getString("email"));

				studentOfWorkList.setStudent(student);
				studentOfWorkList.setSubjectName(result
						.getString("subjectName"));

				students.add(studentOfWorkList);
			}
			return students;

		} catch (SQLException e) {
			System.err.println("Mysql Statement Error: " + sqlSelectStudents);
			e.printStackTrace();
		}

		return null;
	}

	public String updateStudent(int id, String studentName, String phone,
			String address, String email, String birthday) {
		
		String done = "done";
		
		String sqlUpdateStudent = "update Student set studentName ='"
				+ studentName + "', phone = '" + phone + "',address = '"
				+ address + "',email = '" + email + "',birthday='" + birthday
				+ "' where studentId = " + id;
		
		try {
			Connection conn = connection.getConn();
			Statement statement = conn.createStatement();
			statement.executeUpdate(sqlUpdateStudent);
			return done;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Student searchStudent(String studentName) {
		
		String sqlSearchStudent = "select studentId , studentName , address, birthday, phone, email, subjectName from Student left join Subject on Student.subjectId_fk = Subject.subjectId where studentName like '%" + studentName + "'";
		
		try {
			Connection conn = connection.getConn();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlSearchStudent);
			while (resultSet.next()) {
				Student student = new Student();
				
				student.setStudentName(resultSet.getString("studentName"));
				student.setAddress(resultSet.getString("address"));
				student.setBirthDay(resultSet.getString("birthday"));
				student.setEmail(resultSet.getString("email"));
				student.setId(resultSet.getInt("studnetId"));
				student.setPhone(resultSet.getString("phone"));
				
				return student;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String addStudent(String studentName, String phone,
			String address, String email, String birthday) {
		
		String sqlAddStudent = "insert into Student (studentName,address,birthday,phone,email) values ('" + studentName + "','" + address + "','" + birthday + "','"+ phone +"','" + email + "')";
		
		try {
			Connection conn = connection.getConn();
			Statement statement = conn.createStatement();
			statement.executeUpdate(sqlAddStudent);
			return "asdasd";
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
