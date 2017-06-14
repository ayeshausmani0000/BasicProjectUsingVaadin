package com.example.BasicProjectUsingVaadin;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.BasicProjectUsingVaadin.dao.ServiceRestDao;
import com.example.BasicProjectUsingVaadin.dao.MasteRestDao;
import com.example.BasicProjectUsingVaadin.dto.CountryDto;
import com.example.BasicProjectUsingVaadin.dto.StyleDto;
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

@SpringView(name = UpdateView.NAME)
public class UpdateView extends FormLayout implements View {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "Update";
	private TextField styleNo;
	private TextField styleDesc;
	private Button update;
	private Button cancel;
	int id = 0;

	@Autowired
	private ServiceRestDao presenterDao;

	@Autowired
	private MasteRestDao presenterMasterDao;
	
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
			@SuppressWarnings("unchecked")
			Set<StyleDto> styleDtos = (Set<StyleDto>) VaadinSession.getCurrent().getAttribute("Style");
			for (StyleDto styleDto : styleDtos) {
				styleNo.setValue(styleDto.getStyleNo());
				styleDesc.setValue(styleDto.getDesc());
				countryComboBox.setValue(styleDto.getCountry());
				id = styleDto.getId();
			}

			update.addClickListener(e -> {
				StyleDto styleDto = presenterDao.findByStyleId(id);
				styleDto.setStyleNo(styleNo.getValue());
				styleDto.setDesc(styleDesc.getValue());
				styleDto.setCountry(countryComboBox.getSelectedItem().get());
				presenterDao.updateStyle(styleDto);
				getUI().getNavigator().navigateTo(StyleView.NAME);
			});

			layout.addComponents(update, cancel);
			addComponents(styleNo, styleDesc, countryComboBox, layout);
		} 
		
		else {
			save.addClickListener(e -> {
				StyleDto styleDto = new StyleDto();

				styleDto.setStyleNo(styleNo.getValue());
				styleDto.setDesc(styleDesc.getValue());
				styleDto.setCountry(countryComboBox.getSelectedItem().get());
				if (presenterDao.isStyleExistV(styleDto)) {
					Notification.show("Already exist");

				} else {
					presenterDao.createStyle(styleDto);
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
