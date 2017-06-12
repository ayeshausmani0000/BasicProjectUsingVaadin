package com.example.BasicProjectUsingVaadin;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.BasicProjectUsingVaadin.dao.PresenterDao;
import com.example.BasicProjectUsingVaadin.impl.MasterServiceImpl;
import com.example.BasicProjectUsingVaadin.impl.SpringDataServiceImpl;
import com.example.BasicProjectUsingVaadin.model.CountryEntity;
import com.example.BasicProjectUsingVaadin.model.StyleEntity;
import com.example.BasicProjectUsingVaadin.model.StyleOverFilter;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = StyleView.NAME)
public class StyleView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "Style";

	@Autowired
	private PresenterDao presenterDao;

	@Autowired
	private MasterServiceImpl masterServiceImpl;
	
	@Autowired
	private SpringDataServiceImpl serviceImpl;

	private TextField filter;
	private HorizontalLayout layout;
	private Button search;
	private Button update;
	private Button delete;
	private Button addStyle;
	private Button refresh;
	private ComboBox<CountryEntity> comboboxFilter;

	@Override
	public void enter(ViewChangeEvent event) {
		layout = new HorizontalLayout();
		filter = new TextField();
		search = new Button("Search");
		update = new Button("Update");
		delete = new Button("Delete");
		addStyle = new Button("Add Style");
		refresh = new Button("Refresh");
		Iterable<CountryEntity> countryEntities = masterServiceImpl.findAllCountry();
		comboboxFilter = new ComboBox<CountryEntity>();
		comboboxFilter.setItems((Collection<CountryEntity>) countryEntities);

		Iterable<StyleEntity> styleEntities = serviceImpl.findAllStyles();
		Grid<StyleEntity> styleGrid = new Grid<StyleEntity>(StyleEntity.class);
		styleGrid.setColumns("id", "styleNo", "desc", "country");
		ListDataProvider<StyleEntity> styleDataProvider = DataProvider
				.ofCollection((Collection<StyleEntity>) styleEntities);

		// comboboxFilter.addSelectionListener(e -> {
		// styleDataProvider.setFilter(StyleEntity::getCountry, country -> {
		// if (country == null) {
		// return false;
		// }
		// String a = country.getName();
		// String filterLower = e.getValue().getName();
		// return a.equals(filterLower);
		// });
		// });

		search.addClickListener(e -> {
			StyleOverFilter filterEntity = new StyleOverFilter();
			filterEntity.setStyleNo(filter.getValue());
			filterEntity.setCountry(comboboxFilter.getValue());
			Iterable<StyleEntity> filterStyle = serviceImpl.filterByStyleNoAndCountry(filterEntity);

			ListDataProvider<StyleEntity> dataProvider1 = DataProvider
					.ofCollection((Collection<StyleEntity>) filterStyle);
			styleGrid.setDataProvider(dataProvider1);

		});

		/*
		 * countryComboBox.addSelectionListener(e -> {
		 * styleDataProvider.setFilter(StyleEntity::getCountry, country -> { if
		 * (country == null) { return false; } String a = country.getName();
		 * String filterLower = e.getValue().getName(); return
		 * a.equals(filterLower); }); });
		 */

		refresh.addClickListener(e6 -> {
			styleGrid.setDataProvider(styleDataProvider);
		});

		// search.addClickListener(e1 -> {
		//
		// styleDataProvider.setFilter(StyleEntity::getStyleNo, styleNo -> {
		// if (styleNo == null && filter.getValue() == null) {
		// return false;
		// }
		//
		// String a = styleNo.toString();
		// String filterLower = filter.getValue();
		// return a.contains(filterLower);
		//
		// });
		// });

		search.addClickListener(e -> {
			StyleOverFilter filterEntity = new StyleOverFilter();
			filterEntity.setStyleNo(filter.getValue());
			filterEntity.setCountry(comboboxFilter.getValue());
			Iterable<StyleEntity> filterStyle = serviceImpl.filterByStyleNoAndCountry(filterEntity);

			ListDataProvider<StyleEntity> dataProvider1 = DataProvider
					.ofCollection((Collection<StyleEntity>) filterStyle);
			styleGrid.clearSortOrder();
			styleGrid.setDataProvider(dataProvider1);

		});
		/*
		 * search.addClickListener(e1 -> {
		 * 
		 * styleDataProvider.setFilter(StyleEntity::getStyleNo, styleNo -> { if
		 * (styleNo == null && filter.getValue() == null) { return false; }
		 * 
		 * String a = styleNo.toString(); String filterLower =
		 * filter.getValue(); return a.contains(filterLower);
		 * 
		 * }); });
		 */

		styleGrid.addSelectionListener(e4 -> {
			if (styleGrid.asSingleSelect() != null) {
				update.addClickListener(e2 -> {
					VaadinSession.getCurrent().setAttribute("update", "update");
					VaadinSession.getCurrent().setAttribute("Style", styleGrid.getSelectedItems());
					getUI().getNavigator().navigateTo(UpdateView.NAME);
				});

				delete.addClickListener(e5 -> {
					DeleteWindow deleteWindow = new DeleteWindow();
					getUI().addWindow(deleteWindow);

					deleteWindow.addListener(e -> {
						Notification.show("component clicked");
					});

				});
			}

		});

		addStyle.addClickListener(e3 -> {
			VaadinSession.getCurrent().setAttribute("update", "add");
			getUI().getNavigator().navigateTo(UpdateView.NAME);
		});

		styleGrid.setDataProvider(styleDataProvider);
		layout.addComponents(filter, comboboxFilter, search, addStyle, update, delete, refresh);
		layout.setSpacing(true);
		styleGrid.setSizeFull();
		addComponents(layout, styleGrid);
	}

}
