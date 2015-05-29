package com.org.niteco.se.studentmanagementofuniversity.client.functions_modules.WorkListView;



import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.org.niteco.se.studentmanagementofuniversity.client.StudentManagementService;
import com.org.niteco.se.studentmanagementofuniversity.client.StudentManagementServiceAsync;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.Student;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.StudentOfWorkList;
import com.org.niteco.se.studentmanagementofuniversity.client.functions_modules.addObject.CreateStudentView;
import com.org.niteco.se.studentmanagementofuniversity.client.i18n.AuthenticationConstants;

public class WorkListView{
	
	private AuthenticationConstants constants = GWT.create(AuthenticationConstants.class);
	private final StudentManagementServiceAsync managementServiceAsync = GWT.create(StudentManagementService.class);
	
	private List<Integer> idStudentList;
	private HorizontalPanel rowTableStudent;
	private HorizontalPanel buttonList;
	
	CreateStudentView createStudentView;
	VerticalPanel addStudentPanel;
	
	Label lastUpdatedLabel;
	Label errorLabel;
	private VerticalPanel mainWorkList;
	private VerticalPanel mainPageWorkList;

	
	public WorkListView(VerticalPanel mainPageWorkList) {
		this.mainPageWorkList = mainPageWorkList;
		onModuleLoad();
	}
	
	public VerticalPanel getMainPageWorkList() {
		return mainPageWorkList;
	}

	public void rowUpdatesInWorkList(final Student student,HorizontalPanel updateRowTableStudent,StudentOfWorkList studentOfWorkList) {
		for (int j = 0; j < 9; j++) {
			Label lastUpdatedRow = new Label();

			lastUpdatedRow.addStyleName("updateElementRow");
			switch (j) {
			case 0:
				lastUpdatedRow.setText(String.valueOf(student.getId()));
				lastUpdatedRow.setWidth("40px");
				break;
			case 1:
				lastUpdatedRow.setText(student.getStudentName());
				break;
			case 2:
				lastUpdatedRow.setText(student.getBirthDay());
				break;
			case 3:
				lastUpdatedRow.setText(student.getAddress());
				break;
			case 4:
				lastUpdatedRow.setText(student.getEmail());
				break;
			case 5:
				lastUpdatedRow.setText(student.getPhone());
				break;
			case 6:
				lastUpdatedRow.setText(studentOfWorkList.getSubjectName());
				break;
			case 7:
				lastUpdatedRow.setText(constants.remove());
				lastUpdatedRow.addStyleName("aaa");
				break;
			case 8:
				idStudentList = new ArrayList<Integer>();
				CheckBox checkLabel = new CheckBox();

				checkLabel.addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								idStudentList.add(student.getId());
							}
						});
				updateRowTableStudent.add(checkLabel);
				return;
			}
			updateRowTableStudent.add(lastUpdatedRow);
		}
	}
	
	public void rowTitleOfWorkList() {

		for (int j = 0; j < 9; j++) {

			Label title = new Label();
			title.addStyleName("elementRow");
			switch (j) {
			case 0:
				title.setText(constants.id());
				title.setWidth("40px");
				break;
			case 1:
				title.setText(constants.studentName());
				break;
			case 2:
				title.setText(constants.birthDay());
				break;
			case 3:
				title.setText(constants.address());
				break;
			case 4:
				title.setText(constants.email());
				break;
			case 5:
				title.setText(constants.phone());
				break;
			case 6:
				title.setText(constants.subjectName());
				break;
			case 7:
				title.setText(constants.action());
				break;
			case 8:
				title.setText(constants.select());
				title.setWidth("60px");
				break;
			}
			rowTableStudent.add(title);
			
		}
	}
	
	
	
	
	public void onModuleLoad() {
		
		buttonList = new HorizontalPanel();
		Label addStudentButton = new Label(constants.add());
		Label deleteStudentButton = new Label(constants.delete());
		Label editStudentButton = new Label(constants.edit());
		Label searchStudentButton = new Label(constants.search());
		
		lastUpdatedLabel = new Label();
		lastUpdatedLabel.addStyleName("lastUpdateLabel");
		
		addStudentButton.addStyleName("button-worklist");
		deleteStudentButton.addStyleName("button-worklist");
		editStudentButton.addStyleName("button-worklist");
		searchStudentButton.addStyleName("button-worklist");
		
		buttonList.add(lastUpdatedLabel);
		buttonList.add(addStudentButton);
		buttonList.add(deleteStudentButton);
		buttonList.add(editStudentButton);
		
		createStudentView = new CreateStudentView(addStudentPanel);
		addStudentPanel = createStudentView.getAddStudentPanel();
		
		addStudentButton.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			mainWorkList.add(addStudentPanel);
			createStudentView.getAddStudentButton().addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					String addAddress = createStudentView.getAddressTextBox().getText();
					String addEmail =  createStudentView.getEmailTextBox().getText();
					String addStudentName = createStudentView.getStudentNameTextBox().getText();
					String addPhone = createStudentView.getPhoneTextBox().getText();
					String addBirthday = createStudentView.getBirthdayTextBox().getText();
					
					managementServiceAsync.addStudent(addStudentName, addPhone, addAddress, addEmail , addBirthday, new AsyncCallback<String>() {

						@Override
						public void onFailure(Throwable caught) {
							
						}

						@Override
						public void onSuccess(String result) {
							if(result == null){
								errorLabel.setText(constants.errorAddStudent());
							}else{
								errorLabel.setText(result);
							}
							
						}
					});
				}
			});
		}
	});
	
	
	
	editStudentButton.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			mainWorkList.add(addStudentPanel);
			for(int i = 0 ; i < idStudentList.size() ; i++){
				final int id = idStudentList.get(i);
				managementServiceAsync.editStudent(id,new AsyncCallback<Student>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Student result) {
						createStudentView.getAddressTextBox().setText(result.getAddress());
						createStudentView.getEmailTextBox().setText(result.getEmail());
						createStudentView.getStudentNameTextBox().setText(result.getStudentName());
						createStudentView.getPhoneTextBox().setText(result.getPhone());
						createStudentView.getBirthdayTextBox().setText(result.getPhone());
						createStudentView.getAddStudentButton().setText(constants.update());
						
						createStudentView.getAddStudentButton().addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								String addAddress = createStudentView.getAddressTextBox().getText();
								String addEmail =  createStudentView.getEmailTextBox().getText();
								String addStudentName = createStudentView.getStudentNameTextBox().getText();
								String addPhone = createStudentView.getPhoneTextBox().getText();
								String addBirthday = createStudentView.getBirthdayTextBox().getText();
								
								managementServiceAsync.updateStudent(id,addStudentName, addPhone, addAddress, addEmail , addBirthday, new AsyncCallback<String>() {

									@Override
									public void onFailure(Throwable caught) {
										
									}

									@Override
									public void onSuccess(String result) {
										if(result == null){
											errorLabel.setText(constants.errorAddStudent());
										}else{
											errorLabel.setText(result);
										}
										
									}
								});
							}
						});
						
						
					}
				});
				
				
			}
			
		}
	});
	
		
		
		mainPageWorkList = new VerticalPanel();
		
		rowTableStudent = new HorizontalPanel();
		rowTableStudent.addStyleName("rowTable");
		rowTitleOfWorkList();
		
		mainWorkList = new VerticalPanel();
		mainWorkList.add(rowTableStudent);
		mainWorkList.addStyleName("mainWorklist");
		
		errorLabel = new Label();
		mainWorkList.add(errorLabel);
		
		mainPageWorkList.add(mainWorkList);
		mainPageWorkList.addStyleName("mainPageWorkList");
		//mainPageWorkList.add(buttonList);
		
		managementServiceAsync
		.getStudents(new AsyncCallback<List<StudentOfWorkList>>() {

			@Override
			public void onSuccess(
					List<StudentOfWorkList> students) {
				if (students == null) {
					errorLabel.setText(constants.dataNull());
				} else {
					for (int i = 0; i < students.size(); i++) {
						HorizontalPanel updateRowTableStudent = new HorizontalPanel();
						updateRowTableStudent.addStyleName("rowTable");

						final StudentOfWorkList studentOfWorkList = students.get(i);
						final Student student = studentOfWorkList.getStudent();
						rowUpdatesInWorkList(student, updateRowTableStudent, studentOfWorkList);
						
						mainWorkList.add(updateRowTableStudent);
					}
				}
			}

			@Override
			public void onFailure(Throwable caught) {

			}
		});
	}
}
