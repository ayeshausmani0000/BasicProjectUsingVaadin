package com.example.BasicProjectUsingVaadin;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.BasicProjectUsingVaadin.dao.ServiceRestDao;
import com.example.BasicProjectUsingVaadin.dao.MasteRestDao;
import com.example.BasicProjectUsingVaadin.dto.CountryDto;
import com.example.BasicProjectUsingVaadin.dto.StyleDto;
import com.example.BasicProjectUsingVaadin.dto.StyleOverViewFilterDto;
import com.vaadin.addon.pagination.Pagination;
import com.vaadin.addon.pagination.PaginationResource;
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
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SpringView(name = StyleView.NAME)
public class StyleView extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "Style";

	@Autowired
	private ServiceRestDao presenterDao;

	@Autowired
	private MasteRestDao presenterMasterDao;

	private TextField filter;
	private HorizontalLayout layout;
	private Button search;
	private Button delete;
	private Button addStyle;
	private Button refresh;
	private ComboBox<CountryDto> comboboxFilter;
	private final Window window = new Window();
	private Integer id;
	private TextField textField;
	private Button yesButton;
	private Button noButton;
	private Pagination pagination = new Pagination();
	private Integer id1;
	private TextField textField2;

	@Override
	public void enter(ViewChangeEvent event) {
		layout = new HorizontalLayout();
		filter = new TextField();
		search = new Button("Search");
		delete = new Button("Delete");
		addStyle = new Button("Add Style");
		refresh = new Button("Refresh");
		textField = new TextField();
		yesButton = new Button("Yes");
		noButton = new Button("No");

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
		List<StyleDto> styleEntities = presenterDao.findAllStyles();

		Grid<StyleDto> styleGrid = new Grid<StyleDto>(StyleDto.class);
		styleGrid.setColumns("id");

		ListDataProvider<StyleDto> styleDataProvider = DataProvider.ofCollection((Collection<StyleDto>) styleEntities.subList(0, 5));
		textField = new TextField();
		textField2 = new TextField();
		ComboBox<CountryDto> comboBox = new ComboBox<CountryDto>();
		comboBox.setItems((Collection<CountryDto>) countryEntities);
		styleGrid.addColumn(StyleDto::getStyleNo)
		.setEditorComponent(textField, StyleDto::setStyleNo ).setCaption("StyleNo")
		.setExpandRatio(1);

		styleGrid.addColumn(StyleDto::getDesc).setEditorComponent(textField2, StyleDto::setDesc).setCaption("Desc")
		.setExpandRatio(1);

		styleGrid.addColumn(StyleDto::getCountry).setEditorComponent(comboBox, StyleDto::setCountry).setCaption("country")
		.setExpandRatio(1);

		styleGrid.getEditor().setEnabled(true);

		styleGrid.getEditor().addSaveListener(e5->{
		
			
			StyleDto styleDto = e5.getBean();
			StyleDto updateStyleDto=presenterDao.findByStyleId(styleDto.getId());
	
			updateStyleDto.setStyleNo(textField.getValue());
			updateStyleDto.setDesc(textField2.getValue());
			updateStyleDto.setCountry(comboBox.getSelectedItem().get());
			presenterDao.updateStyle(updateStyleDto);

		});

		refresh.addClickListener(e6 -> {
			styleGrid.setData(createPagination(styleEntities.size(), 1, 5));
		});

		search.addClickListener(e -> {
			StyleOverViewFilterDto filterEntity = new StyleOverViewFilterDto();
			filterEntity.setStyleNo(filter.getValue());
			filterEntity.setCountry(comboboxFilter.getValue());
			Iterable<StyleDto> filterStyle = presenterDao.filterByStyleNoAndCountry(filterEntity);

			ListDataProvider<StyleDto> dataProvider1 = DataProvider.ofCollection((Collection<StyleDto>) filterStyle);
			styleGrid.clearSortOrder();
			styleGrid.setDataProvider(dataProvider1);

		});

		styleGrid.addSelectionListener(e7 -> {
			delete.addClickListener(e8 -> {
				if (window.getParent() == null) {
					UI.getCurrent().addWindow(window);

					if (styleGrid.asSingleSelect() != null) {
						Set<StyleDto> style = (Set<StyleDto>) styleGrid.getSelectedItems();
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

		addStyle.addClickListener(e3 -> {
			getUI().getNavigator().navigateTo(SaveAndUpdateView.NAME);
		});

		pagination = createPagination(styleEntities.size(), 1, 5);

		pagination.addPageChangeListener(e10 -> {
			styleGrid.setItems(styleEntities.subList(e10.fromIndex(), e10.toIndex()));

		});

		styleGrid.setDataProvider(styleDataProvider);
		styleGrid.getEditor().setEnabled(true);

		layout.addComponents(filter, comboboxFilter, search, addStyle,  delete, refresh);
		layout.setSpacing(true);
		styleGrid.setSizeFull();

		addComponents(layout, pagination, styleGrid);
	}

	private Pagination createPagination(int total, int page, int limit) {
		final PaginationResource paginationResource = PaginationResource.newBuilder().setTotal(total).setPage(page)
				.setLimit(limit).build();
		final Pagination pagination = new Pagination(paginationResource);
		pagination.setItemsPerPageVisible(false);
		return pagination;
	}

}
