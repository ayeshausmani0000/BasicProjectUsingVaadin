package com.example.BasicProjectUsingVaadin;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.BasicProjectUsingVaadin.impl.MasterServiceImpl;
import com.example.BasicProjectUsingVaadin.impl.SpringDataServiceImpl;
import com.example.BasicProjectUsingVaadin.model.CountryEntity;
import com.example.BasicProjectUsingVaadin.model.StyleEntity;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = StyleView.NAME)
public class StyleView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "Style";

	@Autowired
	private MasterServiceImpl masterServiceImpl;

	@Autowired
	private SpringDataServiceImpl serviceImpl;

	private TextField filter;
	private HorizontalLayout layout;
	private Button search;
	private Button update;
	private Button delete;
	private Button createStyle;
	private Button refresh;

	@Override
	public void enter(ViewChangeEvent event) {
		layout = new HorizontalLayout();
		filter = new TextField();
		search = new Button("Search");
		update = new Button("Update");
		delete = new Button("Delete");
		createStyle = new Button("Create Style");
		refresh = new Button("Refresh");
		Iterable<CountryEntity> countryEntities = masterServiceImpl.findAllCountry();
		ComboBox<CountryEntity> countryComboBox = new ComboBox<CountryEntity>();
		countryComboBox.setItems((Collection<CountryEntity>) countryEntities);
		// ListDataProvider<CountryEntity>
		// countryDataProvider=DataProvider.ofCollection((Collection<CountryEntity>)
		// countryEntities);

		Iterable<StyleEntity> styleEntities = serviceImpl.findAllStyles();
		Grid<StyleEntity> styleGrid = new Grid<StyleEntity>(StyleEntity.class);
		styleGrid.setColumns("id", "styleNo", "desc", "country");
		ListDataProvider<StyleEntity> styleDataProvider = DataProvider
				.ofCollection((Collection<StyleEntity>) styleEntities);

		/*
		 * filter.addValueChangeListener(e -> {
		 * styleDataProvider.setFilter(StyleEntity::getStyleNo, styleNo -> { if
		 * (styleNo == null) { return false; }
		 * 
		 * String a =styleNo.toString(); String filterLower = e.getValue();
		 * return a.equals(filterLower); }); });
		 */

		countryComboBox.addSelectionListener(e -> {
			styleDataProvider.setFilter(StyleEntity::getCountry, country -> {
				if (country == null) {
					return false;
				}
				String a = country.getName();
				String filterLower = e.getValue().getName();
				return a.equals(filterLower);
			});
		});

		refresh.addClickListener(e6 -> {
			styleDataProvider.clearFilters();
		});

		search.addClickListener(e1 -> {

			styleDataProvider.setFilter(StyleEntity::getStyleNo, styleNo -> {
				if (styleNo == null && filter.getValue() == null) {
					return false;
				}

				String a = styleNo.toString();
				String filterLower = filter.getValue();
				return a.contains(filterLower);

			});
		});

		styleGrid.addSelectionListener(e4 -> {
			if (styleGrid.asSingleSelect() != null) {
				update.addClickListener(e2 -> {

					VaadinSession.getCurrent().setAttribute("Style", styleGrid.getSelectedItems());
					getUI().getNavigator().navigateTo(UpdateView.NAME);
				});

				delete.addClickListener(e5 -> {
					DeleteWindow deleteWindow = new DeleteWindow();
					getUI().addWindow(deleteWindow);
					
					  deleteWindow.addListener(e -> {
					  Notification.show("component clicked"); });
					 
					// getUI().addWindow(deleteWindow);
				});
			} else {
				Notification.show("select an entry");
			}

		});

		createStyle.addClickListener(e3 -> {
			getUI().getNavigator().navigateTo(SaveView.NAME);
		});

		styleGrid.setDataProvider(styleDataProvider);
		layout.addComponents(filter, search, countryComboBox, createStyle, update, delete, refresh);
		layout.setSpacing(true);
		styleGrid.setSizeFull();
		addComponents(layout, styleGrid);
	}

}
