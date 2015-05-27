package com.org.niteco.se.studentmanagementofuniversity.client.functions_modules.WorkListView;



import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.Student;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.StudentOfWorkList;
import com.org.niteco.se.studentmanagementofuniversity.client.i18n.AuthenticationConstants;

public class WorkListView{
	private List<Integer> idStudentList;
	private AuthenticationConstants constants = GWT.create(AuthenticationConstants.class);
	private HorizontalPanel rowTableStudent;
	
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
	
	private FlexTable tableStudent = new FlexTable();
	private VerticalPanel mainPage = new VerticalPanel();
	private Label lastUpdatedLabel = new Label();
	
	
	
	public void onModuleLoad() {
		tableStudent.setText(0, 0, "Id");
		tableStudent.setText(0, 1, "Student Name");
		tableStudent.setText(0, 2, "Address");
		tableStudent.setText(0, 3, "BirthDay");
		tableStudent.setText(0, 4, "phone");
		tableStudent.setText(0, 5, "Email");
		
		mainPage.add(tableStudent);
		mainPage.add(lastUpdatedLabel);
	
	}
}
