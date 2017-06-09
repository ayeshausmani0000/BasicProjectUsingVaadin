package com.example.BasicProjectUsingVaadin;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.BasicProjectUsingVaadin.impl.MasterServiceImpl;
import com.example.BasicProjectUsingVaadin.impl.SpringDataServiceImpl;
import com.example.BasicProjectUsingVaadin.model.CountryEntity;
import com.example.BasicProjectUsingVaadin.model.StyleEntity;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = SaveView.NAME)
public class SaveView extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MasterServiceImpl masterServiceImpl;

	@Autowired
	private SpringDataServiceImpl serviceImpl;

	public static final String NAME = "Save";
	private TextField styleNo;
	private TextField styleDesc;

	private Button save;

	private Button cancel;

	@Override
	public void enter(ViewChangeEvent event) {
		styleNo = new TextField("Enter Style Number");
		styleDesc = new TextField("Enter Style Description");
		save = new Button("Save");
		cancel = new Button("Cancel");
		Iterable<CountryEntity> countryEntities = masterServiceImpl.findAllCountry();
		ComboBox<CountryEntity> countryComboBox = new ComboBox<CountryEntity>();
		countryComboBox.setItems((Collection<CountryEntity>) countryEntities);

		save.addClickListener(e -> {
			StyleEntity styleEntity = new StyleEntity();

			styleEntity.setStyleNo(styleNo.getValue());
			styleEntity.setDesc(styleDesc.getValue());
			styleEntity.setCountry(countryComboBox.getSelectedItem().get());
			if (serviceImpl.isStyleExistV(styleEntity)) {
				Notification.show("Already exist");
				getUI().getNavigator().navigateTo(StyleView.NAME);
			} else {
				serviceImpl.saveStyle(styleEntity);
				getUI().getNavigator().navigateTo(StyleView.NAME);
			}

		});

		cancel.addClickListener(e2 -> {
			getUI().getNavigator().navigateTo(StyleView.NAME);
		});

		addComponents(styleNo, styleDesc, countryComboBox, save, cancel);

	}

}
