package com.example.BasicProjectUsingVaadin;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.BasicProjectUsingVaadin.dao.PresenterDao;
import com.example.BasicProjectUsingVaadin.dao.PresenterMasterDao;
import com.example.BasicProjectUsingVaadin.dto.CountryDto;
import com.example.BasicProjectUsingVaadin.dto.StyleDto;
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
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = UpdateView.NAME)
public class UpdateView extends FormLayout implements View {

	public static final String NAME = "Update";
	private TextField styleNo;
	private TextField styleDesc;
	private Button update;
	private Button cancel;
	int id = 0;

	@Autowired
	private PresenterDao presenterDao;

	@Autowired
	private PresenterMasterDao presenterMasterDao;
	// @Autowired
	// private SpringDataServiceImpl serviceImpl;
	//
	// @Autowired
	// private MasterServiceImpl masterServiceImpl;
	private Button save;
	private HorizontalLayout layout;

	@Override
	public void enter(ViewChangeEvent event) {
		layout = new HorizontalLayout();
		styleNo = new TextField("Enter Style Number");
		styleDesc = new TextField("Enter Style Description");
		update = new Button("Update");
		cancel = new Button("Cancel");
		save = new Button("Save");
		Iterable<CountryDto> countryEntities = presenterMasterDao.findAllCountry();
		ComboBox<CountryDto> countryComboBox = new ComboBox<CountryDto>("Select Country");
		countryComboBox.setItems((Collection<CountryDto>) countryEntities);
		String status = (String) VaadinSession.getCurrent().getAttribute("update");

		if (status.equals("update")) {
			Set<StyleDto> style = (Set<StyleDto>) VaadinSession.getCurrent().getAttribute("Style");
			for (StyleDto styleEntity : style) {
				styleNo.setValue(styleEntity.getStyleNo());
				styleDesc.setValue(styleEntity.getDesc());
				countryComboBox.setValue(styleEntity.getCountry());
				id = styleEntity.getId();
			}

			update.addClickListener(e -> {
				StyleDto pStyle = presenterDao.findByStyleId(id);
				pStyle.setStyleNo(styleNo.getValue());
				pStyle.setDesc(styleDesc.getValue());
				pStyle.setCountry(countryComboBox.getSelectedItem().get());
				presenterDao.saveStyle(pStyle);
				getUI().getNavigator().navigateTo(StyleView.NAME);
			});

			layout.addComponents(update, cancel);
			addComponents(styleNo, styleDesc, countryComboBox, layout);
		} else {
			save.addClickListener(e -> {
				StyleDto styleEntity = new StyleDto();

				styleEntity.setStyleNo(styleNo.getValue());
				styleEntity.setDesc(styleDesc.getValue());
				styleEntity.setCountry(countryComboBox.getSelectedItem().get());
				if (presenterDao.isStyleExistV(styleEntity)) {
					Notification.show("Already exist");

				} else {
					presenterDao.saveStyle(styleEntity);
					getUI().getNavigator().navigateTo(StyleView.NAME);
				}

			});

			layout.addComponents(save, cancel);
			addComponents(styleNo, styleDesc, countryComboBox, layout);
		}
		cancel.addClickListener(e2 -> {
			getUI().getNavigator().navigateTo(StyleView.NAME);
		});

	}

}
