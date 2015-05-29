package com.org.niteco.se.studentmanagementofuniversity.client.functions_modules.addObject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.org.niteco.se.studentmanagementofuniversity.client.i18n.AuthenticationConstants;

public class CreateStudentView {

	private AuthenticationConstants constants = GWT
			.create(AuthenticationConstants.class);

	Label studentNameLabel;
	Label phoneLabel;
	Label birthdayLabel;
	Label emailLabel;
	Label addressLabel;

	TextBox studentNameTextBox;
	TextBox phoneTextBox;
	TextBox emailTextBox;

	TextBox addressTextBox;
	TextBox birthdayTextBox;

	Button addStudentButton;

	private HorizontalPanel fieldStudentPanel;

	public Button getAddStudentButton() {
		return addStudentButton;
	}

	public TextBox getStudentNameTextBox() {
		return studentNameTextBox;
	}

	public void setStudentNameTextBox(TextBox studentNameTextBox) {
		this.studentNameTextBox = studentNameTextBox;
	}

	public TextBox getPhoneTextBox() {
		return phoneTextBox;
	}

	public void setPhoneTextBox(TextBox phoneTextBox) {
		this.phoneTextBox = phoneTextBox;
	}

	public TextBox getEmailTextBox() {
		return emailTextBox;
	}

	public void setEmailTextBox(TextBox emailTextBox) {
		this.emailTextBox = emailTextBox;
	}

	public TextBox getAddressTextBox() {
		return addressTextBox;
	}

	public void setAddressTextBox(TextBox addressTextBox) {
		this.addressTextBox = addressTextBox;
	}

	public TextBox getBirthdayTextBox() {
		return birthdayTextBox;
	}

	public void setBirthdayTextBox(TextBox birthdayTextBox) {
		this.birthdayTextBox = birthdayTextBox;
	}

	public void setAddStudentButton(Button addStudentButton) {
		this.addStudentButton = addStudentButton;
	}

	private VerticalPanel addStudentPanel;

	public CreateStudentView(VerticalPanel addStudentPanel) {
		this.addStudentPanel = addStudentPanel;
		onModuleLoad();
	}

	private void onModuleLoad() {

		studentNameLabel = new Label();
		phoneLabel = new Label();
		birthdayLabel = new Label();
		emailLabel = new Label();
		addressLabel = new Label();

		studentNameLabel.setText(constants.studentName());
		phoneLabel.setText(constants.phone());
		birthdayLabel.setText(constants.birthDay());
		emailLabel.setText(constants.email());
		addressLabel.setText(constants.address());

		studentNameTextBox = new TextBox();
		phoneTextBox = new TextBox();
		emailTextBox = new TextBox();
		addressTextBox = new TextBox();
		birthdayTextBox = new TextBox();
		addStudentButton = new Button(constants.add());
		addStudentButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

			}
		});

		addStudentButton.removeStyleName("gwt-Button");
		addStudentButton.addStyleName("addStudentButton");

		addStudentPanel = new VerticalPanel();

		for (int i = 0; i < 5; i++) {
			fieldStudentPanel = new HorizontalPanel();
			TextBox text = null;
			Label label = null;
			switch (i) {
			case 0:
				label = studentNameLabel;
				text = studentNameTextBox;
				break;
			case 1:
				label = addressLabel;
				text = addressTextBox;
				break;
			case 2:
				label = phoneLabel;
				text = phoneTextBox;
				break;
			case 3:
				label = emailLabel;
				text = emailTextBox;
				break;
			case 4:
				label = birthdayLabel;
				text = birthdayTextBox;
				break;
			}

			label.addStyleName("labelStudent");

			fieldStudentPanel.addStyleName("fieldStudentPanel");

			fieldStudentPanel.add(label);
			fieldStudentPanel.add(text);
			addStudentPanel.add(fieldStudentPanel);
		}

		addStudentPanel.add(addStudentButton);
		addStudentPanel.addStyleName("addStudentPanel");

	}

	public VerticalPanel getAddStudentPanel() {
		return addStudentPanel;
	}

}
