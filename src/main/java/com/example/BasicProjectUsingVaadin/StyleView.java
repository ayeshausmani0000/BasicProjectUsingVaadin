package com.example.BasicProjectUsingVaadin;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.BasicProjectUsingVaadin.dto.StyleDto;

import com.example.BasicProjectUsingVaadin.dao.PresenterMasterDao;
import com.example.BasicProjectUsingVaadin.dao.PresenterServiceDao;
import com.example.BasicProjectUsingVaadin.dto.CountryDto;
import com.example.BasicProjectUsingVaadin.dto.StyleOverViewFilterDto;
import com.vaadin.addon.pagination.Pagination;
import com.vaadin.addon.pagination.PaginationChangeListener;
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
import com.vaadin.ui.renderers.*;

@SpringView(name = StyleView.NAME)
public class StyleView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "Style";

	@Autowired
	private PresenterServiceDao presenterDao;

	@Autowired
	private PresenterMasterDao presenterMasterDao;

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
	private Integer id1;
	Pagination pagination;
	Label label;
	private TextField textField2;
	private TextField textField;

	@Override
	public void enter(ViewChangeEvent event) {

		final int page = 1;

		final int limit = 5;
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

		List<StyleDto> styleEntities = presenterDao.findAllStyles();

		final long total = Long.valueOf(styleEntities.size());
		Grid<StyleDto> styleGrid = new Grid<StyleDto>(StyleDto.class);

		styleGrid.setColumns("id", "country");
		// styleGrid.getColumn("id").setEditable(true).setEditable(getNoEditableField());
		label=new Label();
		ListDataProvider<StyleDto> styleDataProvider = DataProvider.ofCollection((Collection<StyleDto>) styleEntities);

		textField = new TextField();
		textField2 = new TextField();
		styleGrid.addColumn(StyleDto::getStyleNo).setEditorComponent(textField, StyleDto::setStyleNo)
				.setCaption("StyleNo").setExpandRatio(1);

		styleGrid.addColumn(StyleDto::getDesc).setEditorComponent(textField2, StyleDto::setDesc).setCaption("Desc")
				.setExpandRatio(1);
//		styleGrid.addColumn(StyleDto::getId).setEditorComponent(label, StyleDto::getId).setCaption("id")
//				.setExpandRatio(0);
		/*styleGrid.addColumn(StyleDto::getId).setEditorComponent(textField2, StyleDto::getId).setCaption("Desc")
		.setExpandRatio(1);
*/
		/*
		 * styleDataProvider.addDataProviderListener(e6->{ List<StyleDto>
		 * styleDtos=(List<StyleDto>) styleDataProvider.getItems();
		 * 
		 * for (StyleDto styleDto : styleDtos) { id = styleDto.getId();
		 * textField.setValue(styleDto.getStyleNo());
		 * textField2.setValue(styleDto.getDesc()); } });
		 */
		// for(StyleDto style:styleEntities)
		// {
		// id1=style.getId();
		// System.out.println(id1);
		// textField.setValue(style.getStyleNo());
		// textField2.setValue(style.getDesc());
		// }
		// List<StyleDto> styleDtos=(List<StyleDto>)
		// styleDataProvider..getItems();

		styleGrid.getEditor().addSaveListener(e5 -> {
			/*Set<StyleDto> selectedDto = (Set<StyleDto>) styleGrid;
			for (StyleDto dto : selectedDto) {
				id1 = dto.getId();
				System.out.println("id1 is"+id1);
			}*/
			
			StyleDto styleDto = presenterDao.findByStyleId(id1);
			styleDto.setStyleNo(textField.getValue());
			styleDto.setDesc(textField2.getValue());
			
			// presenterDao.updateStyle(styleDto);

			/*VaadinSession.getCurrent().setAttribute("update", "update");
			VaadinSession.getCurrent().setAttribute("Style",styleDto  styleGrid.getSelectedItems() );
			getUI().getNavigator().navigateTo(UpdateView.NAME);*/
		});
		styleGrid.getEditor().setEnabled(true);

		final Pagination pagination = createPagination(total, page, limit);

		pagination.addPageChangeListener(new PaginationChangeListener() {

			@Override

			public void changed(PaginationResource event) {
				styleGrid.setItems(styleEntities.subList(event.fromIndex(), event.toIndex()));

				styleGrid.scrollToStart();

			}

		});
		refresh.addClickListener(e6 -> {
			styleGrid.setDataProvider(styleDataProvider);
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
						Set<StyleDto> style1 = (Set<StyleDto>) styleGrid.getSelectedItems();
						for (StyleDto styleEntity : style1) {
							id = styleEntity.getId();
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
					getUI().getNavigator().navigateTo(SaveAndUpdateView.NAME);
				});

			}

		});

		addStyle.addClickListener(e3 -> {
			VaadinSession.getCurrent().setAttribute("update", "add");
			getUI().getNavigator().navigateTo(SaveAndUpdateView.NAME);
		});

		styleGrid.setDataProvider(styleDataProvider);
		addComponent(pagination);
		layout.addComponents(filter, comboboxFilter, search, addStyle, update, delete, refresh);

		layout.setSpacing(true);
		styleGrid.setSizeFull();
		addComponents(layout, styleGrid);

	}

	private Pagination createPagination(long total, int page, int limit) {

		final PaginationResource paginationResource = PaginationResource.newBuilder().setTotal(total).setPage(page)
				.setLimit(limit).build();

		final Pagination pagination = new Pagination(paginationResource);

		pagination.setItemsPerPage(5, 6, 7, 8);

		return pagination;

	}
}
