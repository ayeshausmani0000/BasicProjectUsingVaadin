package com.example.BasicProjectUsingVaadin;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.BasicProjectUsingVaadin.dao.PresenterDao;
import com.example.BasicProjectUsingVaadin.dao.PresenterMasterDao;
import com.example.BasicProjectUsingVaadin.dto.CountryDto;
import com.example.BasicProjectUsingVaadin.dto.StyleDto;
import com.example.BasicProjectUsingVaadin.dto.StyleOverViewFilterDto;
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
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SpringView(name = StyleView.NAME)
public class StyleView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "Style";

	@Autowired
	private PresenterDao presenterDao;
	
	@Autowired
	private PresenterMasterDao presenterMasterDao;
	
	

	/*@Autowired
	private MasterServiceImpl masterServiceImpl;

	@Autowired
	private SpringDataServiceImpl serviceImpl;*/

	private TextField filter;
	private HorizontalLayout layout;
	private Button search;
	private Button update;
	private Button delete;
	private Button addStyle;
	private Button refresh;
	private ComboBox<CountryDto> comboboxFilter;
	private final Window window = new Window();
	private Integer id;

	@Override
	public void enter(ViewChangeEvent event) {
		layout = new HorizontalLayout();
		filter = new TextField();
		search = new Button("Search");
		update = new Button("Update");
		delete = new Button("Delete");
		addStyle = new Button("Add Style");
		refresh = new Button("Refresh");
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		final VerticalLayout popupVLayout = new VerticalLayout();
		Label label = new Label("Do You Really Want to Delete It");
		HorizontalLayout horizontalLayout1 = new HorizontalLayout();
		horizontalLayout1.addComponents(yesButton, noButton);
		popupVLayout.addComponents(label, horizontalLayout1);

		window.setContent(popupVLayout);
		window.center();
		window.setWidth("350px");
		window.setHeight("100px");

		Iterable<CountryDto> countryEntities = presenterMasterDao.findAllCountry();
		comboboxFilter = new ComboBox<CountryDto>();
		comboboxFilter.setItems((Collection<CountryDto>) countryEntities);

		// Iterable<StyleEntity> styleEntities = serviceImpl.findAllStyles();
		Iterable<StyleDto> styleEntities = presenterDao.findAllStyles();
		Grid<StyleDto> styleGrid = new Grid<StyleDto>(StyleDto.class);
		styleGrid.setColumns("id", "styleNo", "desc", "country");
		ListDataProvider<StyleDto> styleDataProvider = DataProvider
				.ofCollection((Collection<StyleDto>) styleEntities);

		// search.addClickListener(e -> {
		// StyleOverFilter filterEntity = new StyleOverFilter();
		// filterEntity.setStyleNo(filter.getValue());
		// filterEntity.setCountry(comboboxFilter.getValue());
		// Iterable<StyleEntity> filterStyle =
		// serviceImpl.filterByStyleNoAndCountry(filterEntity);
		//
		// ListDataProvider<StyleEntity> dataProvider1 = DataProvider
		// .ofCollection((Collection<StyleEntity>) filterStyle);
		// styleGrid.setDataProvider(dataProvider1);
		//
		// });

		refresh.addClickListener(e6 -> {
			styleGrid.setDataProvider(styleDataProvider);
		});

		search.addClickListener(e -> {
			StyleOverViewFilterDto filterEntity = new StyleOverViewFilterDto();
			filterEntity.setStyleNo(filter.getValue());
			filterEntity.setCountry(comboboxFilter.getValue());
			Iterable<StyleDto> filterStyle = presenterDao.filterByStyleNoAndCountry(filterEntity);

			ListDataProvider<StyleDto> dataProvider1 = DataProvider
					.ofCollection((Collection<StyleDto>) filterStyle);
			styleGrid.clearSortOrder();
			styleGrid.setDataProvider(dataProvider1);

		});

		styleGrid.addSelectionListener(e7 -> {
			delete.addClickListener(e8 -> {
				if (window.getParent() == null) {
					UI.getCurrent().addWindow(window);

					if (styleGrid.asSingleSelect() != null) {
						Set<StyleDto>  style = (Set<StyleDto>) styleGrid.getSelectedItems();
						for (StyleDto styleDto : style) {
							id = styleDto.getId();
						}
						yesButton.addClickListener(e -> {
							presenterDao.deleteStyle(id);
							window.close();
						});

						noButton.focus();
						noButton.addClickListener(e -> {
							window.close();
						});
					}
				}
			});
		});

		styleGrid.addSelectionListener(e4 -> {
			if (styleGrid.asSingleSelect() != null) {
				update.addClickListener(e2 -> {
					VaadinSession.getCurrent().setAttribute("update", "update");
					VaadinSession.getCurrent().setAttribute("Style", styleGrid.getSelectedItems());
					getUI().getNavigator().navigateTo(UpdateView.NAME);
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
