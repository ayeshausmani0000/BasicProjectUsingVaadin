package com.example.BasicProjectUsingVaadin;

import com.example.BasicProjectUsingVaadin.model.StyleEntity;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class DeleteWindow extends Window {
	

	public DeleteWindow() {
		center();
		Label label = new Label("Do You Really Want to Delete It");
		setClosable(false);
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		Button yes = new Button("YES"
				//,event -> close()

		);
		Button no = new Button("NO"
			//	, event -> {close();
		);

		setWidth("350px");
		setHeight("100px");
		VerticalLayout verticalLayout = new VerticalLayout();
		horizontalLayout.addComponents(yes, no);
		verticalLayout.addComponents(label, horizontalLayout);
		setContent(verticalLayout);
	}
}
