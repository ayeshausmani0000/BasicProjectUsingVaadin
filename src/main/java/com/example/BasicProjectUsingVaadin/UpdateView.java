package com.example.BasicProjectUsingVaadin;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.BasicProjectUsingVaadin.impl.MasterServiceImpl;
import com.example.BasicProjectUsingVaadin.impl.SpringDataServiceImpl;
import com.example.BasicProjectUsingVaadin.model.CountryEntity;
import com.example.BasicProjectUsingVaadin.model.StyleEntity;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = UpdateView.NAME)
public class UpdateView extends VerticalLayout implements View {

	public static final String NAME = "Update";
	private TextField styleNo;
	private TextField styleDesc;
	private Button update;
	private Button cancel;
	int id = 0;

	@Autowired
	private SpringDataServiceImpl serviceImpl;

	@Autowired
	private MasterServiceImpl masterServiceImpl;

	@Override
	public void enter(ViewChangeEvent event) {
		styleNo = new TextField("Enter Style Number");
		styleDesc = new TextField("Enter Style Description");
		update = new Button("Update");
		cancel = new Button("Cancel");
		Iterable<CountryEntity> countryEntities = masterServiceImpl.findAllCountry();
		ComboBox<CountryEntity> countryComboBox = new ComboBox<CountryEntity>();
		countryComboBox.setItems((Collection<CountryEntity>) countryEntities);

		Set<StyleEntity> style = (Set<StyleEntity>) VaadinSession.getCurrent().getAttribute("Style");
		for (StyleEntity styleEntity : style) {
			styleNo.setValue(styleEntity.getStyleNo());
			styleDesc.setValue(styleEntity.getDesc());
			countryComboBox.setValue(styleEntity.getCountry());
			id = styleEntity.getId();
		}

		update.addClickListener(e -> {
			StyleEntity pStyle = serviceImpl.findByStyleId(id);
			pStyle.setStyleNo(styleNo.getValue());
			pStyle.setDesc(styleDesc.getValue());
			pStyle.setCountry(countryComboBox.getSelectedItem().get());
			serviceImpl.saveStyle(pStyle);
			getUI().getNavigator().navigateTo(StyleView.NAME);
		});

		cancel.addClickListener(e2 -> {
			getUI().getNavigator().navigateTo(StyleView.NAME);
		});

		addComponents(styleNo, styleDesc, countryComboBox, update, cancel);

	}

}
