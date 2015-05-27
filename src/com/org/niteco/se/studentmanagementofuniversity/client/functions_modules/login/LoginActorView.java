package com.org.niteco.se.studentmanagementofuniversity.client.functions_modules.login;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.org.niteco.se.studentmanagementofuniversity.client.StudentManagementService;
import com.org.niteco.se.studentmanagementofuniversity.client.StudentManagementServiceAsync;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.Student;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.StudentOfWorkList;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.User;
import com.org.niteco.se.studentmanagementofuniversity.client.functions_modules.addObject.CreateStudentView;
import com.org.niteco.se.studentmanagementofuniversity.client.i18n.AuthenticationConstants;
import com.org.niteco.se.studentmanagementofuniversity.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginActorView extends Composite implements EntryPoint {
	

	private AuthenticationConstants constants = GWT.create(AuthenticationConstants.class);

	private final StudentManagementServiceAsync managementServiceAsync = GWT.create(StudentManagementService.class);

	Label textLoginLabel;
	
	Button loginButton;
	
	TextBox usernameField;
	
	PasswordTextBox passwordField;
	
	Label errorLabel;

	Label lastUpdatedLabel;

	private VerticalPanel mainWorkList;

	private HorizontalPanel rowTableStudent;

	private HorizontalPanel mainPage;

	private HorizontalPanel buttonList;

	private List<Integer> idStudentList;
	
	
	VerticalPanel addStudentPanel;
	
	CreateStudentView createStudentView;
	
	
	
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
		
		buttonList.add(deleteStudentButton);
		buttonList.add(editStudentButton);
		
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
		buttonList.add(searchStudentButton);

		buttonList.addStyleName("buttonList");

		deleteStudentButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				managementServiceAsync.removeStudent(idStudentList,
						new AsyncCallback<String>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(String result) {
								DateTimeFormat dateFormat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM);
								lastUpdatedLabel.setText("Last update : " + dateFormat.format(new Date()));
								Window.alert(result);
							}
						});

			}
		});
		
		rowTableStudent = new HorizontalPanel();
		rowTableStudent.addStyleName("rowTable");

		
		rowTitleOfWorkList();
		
		mainWorkList = new VerticalPanel();
		mainWorkList.add(rowTableStudent);
		mainWorkList.addStyleName("mainWorklist");
		
		
		errorLabel = new Label();
		mainWorkList.add(errorLabel);

		
		usernameField = new TextBox();
		passwordField = new PasswordTextBox();
		usernameField.setText("");
		passwordField.setText("");
		
		textLoginLabel = new Label();
		textLoginLabel.setText(constants.login());
		
		
		loginButton = new Button(constants.login());
		loginButton.removeStyleName("gwt-Button");
		loginButton.addStyleName("loginButton");
		usernameField.addStyleName("textField");
		passwordField.addStyleName("textField");
		textLoginLabel.addStyleName("textLogin");

		final VerticalPanel loginPanel = new VerticalPanel();
		loginPanel.addStyleName("loginPanel");
		loginPanel.add(textLoginLabel);
		loginPanel.add(usernameField);
		loginPanel.add(passwordField);
		loginPanel.add(loginButton);
		loginPanel.add(errorLabel);
		errorLabel.addStyleName("errorStyle");
		
		/* MainPageView */

		TreeItem root1 = new TreeItem();
		root1.setText("Student");
		root1.addTextItem("Technology Imformation");
		root1.addTextItem("Envirament");
		root1.addTextItem("");

		TreeItem item1 = new TreeItem(new CheckBox("item3"));
		root1.addItem(item1);

		Tree t = new Tree();

		t.addItem(root1);

		final StackPanel menuPanel = new StackPanel();
		VerticalPanel a = new VerticalPanel();
		a.add(t);

		menuPanel.add(a, "Student");
		menuPanel.add(new Label("d"), "Subject");
		menuPanel.add(new Label("a"), "Class");
		menuPanel.add(new Label("g"), "Score");

		menuPanel.addStyleName("mainPanel");
		
		
		createStudentView = new CreateStudentView(addStudentPanel);
		addStudentPanel = createStudentView.getAddStudentPanel();
		
		
		mainPage = new HorizontalPanel();
		mainPage.add(menuPanel);
		mainPage.add(mainWorkList);
		

		RootPanel.get("mainPagePanel").add(loginPanel);

		usernameField.setFocus(true);
		usernameField.selectAll();

		class MyHandler implements ClickHandler, KeyUpHandler {
			public void onClick(ClickEvent event) {
				sendTextToServer();
			}

			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendTextToServer();
				}
			}

			private void sendTextToServer() {
				errorLabel.setText("");
				String textUserToServer = usernameField.getText();
				String textPassToserver = passwordField.getText();
				
				if (!FieldVerifier.isValidName(textUserToServer)
						|| !FieldVerifier.isValidName(textPassToserver)) {
					errorLabel.setText(constants.inputDataNull());
					return;
				}

				loginButton.setEnabled(false);

				managementServiceAsync.checkLogin(textUserToServer,
						textPassToserver, new AsyncCallback<User>() {

							@Override
							public void onSuccess(User user) {
								if (user == null) {
									errorLabel.setText(constants
											.notExistAccound());
									return;
								} else {
									RootPanel.get("mainPagePanel").remove(0);
									RootPanel.get("mainPagePanel")
											.add(mainPage);

								}
							}

							@Override
							public void onFailure(Throwable caught) {

							}
						});

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
										updateRowTableStudent
												.addStyleName("rowTable");

										final StudentOfWorkList studentOfWorkList = students
												.get(i);
										final Student student = studentOfWorkList
												.getStudent();

										rowUpdatesInWorkList(student, updateRowTableStudent, studentOfWorkList);
										mainWorkList.add(updateRowTableStudent);
										mainWorkList.add(buttonList);
									}
								}
							}

							@Override
							public void onFailure(Throwable caught) {

							}
						});
			}
		}

		MyHandler handler = new MyHandler();
		loginButton.addClickHandler(handler);
		usernameField.addKeyUpHandler(handler);
		passwordField.addKeyUpHandler(handler);
	}
}
