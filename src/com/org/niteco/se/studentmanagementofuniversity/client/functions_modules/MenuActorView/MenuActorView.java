package com.org.niteco.se.studentmanagementofuniversity.client.functions_modules.MenuActorView;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MenuActorView {
	private StackPanel menuPanel;

	public MenuActorView(StackPanel menuPanel) {
		this.menuPanel = menuPanel;
		onModuleLoad();
	}

	public StackPanel getMenuPanel() {
		return menuPanel;
	}

	public void setMenuPanel(StackPanel menuPanel) {
		this.menuPanel = menuPanel;
	}

	public void onModuleLoad() {

		TreeItem root1 = new TreeItem();
		root1.setText("Student");
		root1.addTextItem("Technology Imformation");
		root1.addTextItem("Envirament");
		root1.addTextItem("");

		TreeItem item1 = new TreeItem(new CheckBox("item3"));
		root1.addItem(item1);

		Tree t = new Tree();

		t.addItem(root1);

		menuPanel = new StackPanel();
		VerticalPanel a = new VerticalPanel();
		a.add(t);

		menuPanel.add(a, "Student");
		menuPanel.add(new Label("d"), "Subject");
		menuPanel.add(new Label("a"), "Class");
		menuPanel.add(new Label("g"), "Score");

		menuPanel.addStyleName("mainPanel");
	}

}
