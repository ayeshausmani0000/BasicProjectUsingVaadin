package com.example.BasicProjectUsingVaadin;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.BasicProjectUsingVaadin.dao.ServiceRestDao;
import com.example.BasicProjectUsingVaadin.dao.MasteRestDao;
import com.example.BasicProjectUsingVaadin.dto.CountryDto;
import com.example.BasicProjectUsingVaadin.dto.StyleDto;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

@SpringView(name = SaveAndUpdateView.NAME)
public class SaveAndUpdateView extends FormLayout implements View {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "Update";
	private TextField styleNo;
	private TextField styleDesc;
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
		cancel = new Button("Cancel");
		save = new Button("Save");
		Iterable<CountryDto> countryEntities = presenterMasterDao.findAllCountry();
		ComboBox<CountryDto> countryComboBox = new ComboBox<CountryDto>("Select Country");
		countryComboBox.setItems((Collection<CountryDto>) countryEntities);
		
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
			
	
		cancel.addClickListener(e2 -> {
			getUI().getNavigator().navigateTo(StyleView.NAME);
		});
		
		addComponents(styleNo, styleDesc, countryComboBox, layout);
		
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

	}

}
