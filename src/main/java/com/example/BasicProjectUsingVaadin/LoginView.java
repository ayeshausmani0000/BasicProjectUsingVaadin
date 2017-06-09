package com.example.BasicProjectUsingVaadin;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.BasicProjectUsingVaadin.impl.SpringDataServiceImpl;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;



@SpringView(name=LoginView.NAME)
public class LoginView extends VerticalLayout implements View{

	private static final long serialVersionUID = 1L;
	public static final String NAME = "Login";
	private TextField username;
	private PasswordField password;
	private Button submit;
	
	@Autowired
	private SpringDataServiceImpl serviceImpl;


	@Override
	public void enter(ViewChangeEvent event) {
		
		FormLayout loginForm=new FormLayout();
		username=new TextField("Enter User Name");
		
		password=new PasswordField("Enter Password");
		
		submit=new Button("Submit");
		
		submit.addClickListener(e->{
			if(serviceImpl.validateUser(username.getValue(), password.getValue()))
			{
				getUI().getNavigator().navigateTo(
						StyleView.NAME);
			}
			else
			{
				Notification.show("Invalid Username/Password");
			}
		});
		
		loginForm.addComponents(username,password,submit);
		loginForm.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		addComponent(loginForm);
	}
	

}
