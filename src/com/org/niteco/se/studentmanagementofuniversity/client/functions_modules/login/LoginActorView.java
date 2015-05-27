package com.org.niteco.se.studentmanagementofuniversity.client.functions_modules.login;

import com.org.niteco.se.studentmanagementofuniversity.client.StudentManagementService;
import com.org.niteco.se.studentmanagementofuniversity.client.StudentManagementServiceAsync;
import com.org.niteco.se.studentmanagementofuniversity.client.Model.User;
import com.org.niteco.se.studentmanagementofuniversity.client.functions_modules.MenuActorView.MenuActorView;
import com.org.niteco.se.studentmanagementofuniversity.client.functions_modules.WorkListView.WorkListView;
import com.org.niteco.se.studentmanagementofuniversity.client.i18n.AuthenticationConstants;
import com.org.niteco.se.studentmanagementofuniversity.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginActorView extends Composite implements EntryPoint {

	private AuthenticationConstants constants = GWT
			.create(AuthenticationConstants.class);

	private final StudentManagementServiceAsync managementServiceAsync = GWT
			.create(StudentManagementService.class);

	Label textLoginLabel;
	Button loginButton;
	TextBox usernameField;
	PasswordTextBox passwordField;
	Label errorLabel;

	private HorizontalPanel mainPage;

	MenuActorView menuActorView;
	StackPanel menuPanel;

	WorkListView workListView;
	VerticalPanel mainPageWorkList;

	public void onModuleLoad() {

		errorLabel = new Label();
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

		menuActorView = new MenuActorView(menuPanel);
		menuPanel = menuActorView.getMenuPanel();

		workListView = new WorkListView(mainPageWorkList);
		mainPageWorkList = workListView.getMainPageWorkList();

		mainPage = new HorizontalPanel();
		mainPage.add(menuPanel);
		mainPage.add(mainPageWorkList);

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
									RootPanel.get("mainPagePanel").add(mainPage);
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
