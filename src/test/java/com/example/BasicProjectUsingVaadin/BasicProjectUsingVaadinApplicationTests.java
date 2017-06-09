package com.example.BasicProjectUsingVaadin;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.BasicProjectUsingVaadin.impl.EntityManagerServiceImpl;
import com.example.BasicProjectUsingVaadin.impl.SpringDataServiceImpl;
import com.example.BasicProjectUsingVaadin.model.ClientEntity;
import com.example.BasicProjectUsingVaadin.model.CountryEntity;
import com.example.BasicProjectUsingVaadin.model.ItemEntity;
import com.example.BasicProjectUsingVaadin.model.ItemSizeEntity;
import com.example.BasicProjectUsingVaadin.model.SeasonEntity;
import com.example.BasicProjectUsingVaadin.model.SizeEntity;
import com.example.BasicProjectUsingVaadin.model.StyleEntity;
import com.example.BasicProjectUsingVaadin.service.MasterService;
import com.example.BasicProjectUsingVaadin.service.Service;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations = "classpath:springconfig.xml")
public class BasicProjectUsingVaadinApplicationTests {

	ConfigurableApplicationContext ctx = null;
	MasterService masterService = null;

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("springconfig.xml");
		masterService = (MasterService) ctx.getBean("masterServiceImpl");

	}

	@Test
	public void testSaveStyle() {
		Service styleService = (Service) ctx.getBean("serviceImpl");
		ClientEntity client = masterService.findClientById(1);
		CountryEntity country = masterService.findCountryById(2);
		SeasonEntity season = masterService.findSeasonById(2);

		StyleEntity style = new StyleEntity();
		style.setStyleNo("Ay546");
		style.setDesc("floral shirt");
		style.setSeason(season);
		style.setCountry(country);
		style.setClient(client);

		styleService.saveStyle(style);

		ctx.close();
	}

	@Test
	public void testSaveStyleWithItems() {

		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
				"springconfig.xml");

		Service styleService = (Service) ctx.getBean("serviceImpl");
		@SuppressWarnings("unused")
		ClientEntity client = masterService.findClientById(1);
		CountryEntity country = masterService.findCountryById(2);
		SeasonEntity season = masterService.findSeasonById(2);

		StyleEntity style = new StyleEntity();
		style.setStyleNo("Ay12356");
		style.setDesc("floral shirt");
		style.setSeason(season);
		style.setCountry(country);

		ItemEntity item1 = new ItemEntity();
		item1.setItemNo("AY49687");
		item1.setColor("blue");
		item1.setStyle(style);

		ItemEntity item2 = new ItemEntity();
		item2.setItemNo("AY4967");
		item2.setColor("green");
		item2.setStyle(style);

		Set<ItemEntity> items = new HashSet<ItemEntity>();
		items.add(item1);
		items.add(item2);

		style.setItems(items);

		styleService.saveStyle(style);

		ctx.close();
	}

	@Test
	public void testSaveItemSize() {
		Service itemSizeService = (Service) ctx.getBean("itemSizeServiceImpl");
		SizeEntity sizeEntity = masterService.findSizeById(1);

		ItemSizeEntity itemSizeEntity = new ItemSizeEntity();
		itemSizeEntity.setQuantity(20);
		itemSizeEntity.setSize(sizeEntity);
		itemSizeService.saveItemSize(itemSizeEntity);

	}

	@Test
	public void testSaveItemWithItemSizes() {

		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
				"springconfig.xml");
		Service styleService = (Service) ctx.getBean("serviceImpl");

		StyleEntity style = styleService.findByStyleId(113);
		SizeEntity size = masterService.findSizeById(1);

		ItemEntity item = new ItemEntity();
		item.setItemNo("H123");
		item.setColor("Blue");
		item.setStyle(style);

		ItemSizeEntity itemSize = new ItemSizeEntity();
		itemSize.setSize(size);
		itemSize.setItem(item);
		itemSize.setQuantity(10);
		ItemSizeEntity itemSize1 = new ItemSizeEntity();
		itemSize1.setSize(size);
		itemSize1.setItem(item);
		itemSize1.setQuantity(2);

		Set<ItemSizeEntity> itemSizeEntities = new HashSet<ItemSizeEntity>();
		itemSizeEntities.add(itemSize);
		itemSizeEntities.add(itemSize1);

		item.setItemSizes(itemSizeEntities);

		styleService.saveItem(item);

		ctx.close();
	}

	@Test
	public void testSaveStyleItemsWithItemSizes() {
		Service styleService = (Service) ctx.getBean("serviceImpl");

		ClientEntity client = masterService.findClientById(1);
		CountryEntity country = masterService.findCountryById(2);
		SeasonEntity season = masterService.findSeasonById(2);
		SizeEntity size = masterService.findSizeById(1);

		Set<ItemEntity> itemEntites = new HashSet<ItemEntity>();
		Set<ItemSizeEntity> itemSizeEntites = new HashSet<ItemSizeEntity>();

		StyleEntity styleEntity = new StyleEntity();
		// styleEntity.setId(id);
		styleEntity.setStyleNo("SeqTest8");
		styleEntity.setDesc("JACKET");
		styleEntity.setItems(itemEntites);
		styleEntity.setClient(client);
		styleEntity.setCountry(country);
		styleEntity.setSeason(season);

		ItemEntity item1 = new ItemEntity();
		// item1.setItemId(100);
		item1.setItemNo("RAVI123");
		item1.setColor("Red");

		ItemSizeEntity itemSizeEntity1 = new ItemSizeEntity();
		itemSizeEntity1.setQuantity(10);
		itemSizeEntity1.setSize(size);
		itemSizeEntity1.setItem(item1);

		ItemSizeEntity itemSizeEntity2 = new ItemSizeEntity();
		itemSizeEntity2.setQuantity(20);
		itemSizeEntity2.setSize(size);
		itemSizeEntity2.setItem(item1);

		itemSizeEntites.add(itemSizeEntity1);
		itemSizeEntites.add(itemSizeEntity2);

		item1.setItemSizes(itemSizeEntites);
		item1.setStyle(styleEntity);

		itemEntites.add(item1);

		styleService.saveStyle(styleEntity);

		ctx.close();
	}

	@Test
	public void testUpdateStyleWithItem() {
		Service styleService = (Service) ctx.getBean("serviceImpl");
		StyleEntity styleEntity = styleService.findByStyleId(224);
		styleEntity.setDesc("Red Jeans");
		styleEntity.setStyleNo("H1505");

		ItemEntity itemEntity = styleService.findByItemId(223);
		itemEntity.setColor("RED");
		itemEntity.setStyle(styleEntity);

		ItemEntity itemEntity1 = styleService.findByItemId(233);
		itemEntity1.setColor("BLACK");
		itemEntity1.setStyle(styleEntity);

		Set<ItemEntity> itemEntites = new HashSet<ItemEntity>();
		itemEntites.add(itemEntity);
		itemEntites.add(itemEntity1);

		styleEntity.setItems(itemEntites);
		styleService.saveStyle(styleEntity);

		ctx.close();
	}

	// @Test
	public void testUpdateItemWithItemSize() {
		Service styleService = (Service) ctx.getBean("serviceImpl");
		ItemEntity itemEntity = styleService.findByItemId(161);
		itemEntity.setColor("YELLOW");
		itemEntity.setItemNo("HM1505");

		ItemSizeEntity itemSizeEntity = styleService.findByItemSizeId(201);
		itemSizeEntity.setQuantity(5);
		itemSizeEntity.setItem(itemEntity);

		ItemSizeEntity itemSizeEntity1 = styleService.findByItemSizeId(240);
		itemSizeEntity1.setQuantity(10);
		itemSizeEntity1.setItem(itemEntity);

		Set<ItemSizeEntity> itemSizeEntities = new HashSet<ItemSizeEntity>();
		itemSizeEntities.add(itemSizeEntity);
		itemSizeEntities.add(itemSizeEntity1);

		itemEntity.setItemSizes(itemSizeEntities);
		styleService.saveItem(itemEntity);

		ctx.close();
	}

	@Test
	public void testUpdateStyleWithItemSize() {
		Service styleService = (Service) ctx.getBean("serviceImpl");
		StyleEntity style = styleService.findByStyleId(36);
		style.setDesc("new");
		Set<ItemEntity> itemEntities = style.getItems();

		for (ItemEntity itemEntity : itemEntities) {
			itemEntity.setColor("RED");
			Set<ItemSizeEntity> itemSizes = itemEntity.getItemSizes();
			for (ItemSizeEntity itemSizeEntity : itemSizes) {
				itemSizeEntity.setQuantity(4);
			}
		}
		styleService.saveStyle(style);
		ctx.close();
	}

	@Test
	public void testRetrieveStyle() {
		SpringDataServiceImpl styleService = (SpringDataServiceImpl) ctx
				.getBean("serviceImpl");
		StyleEntity styleEntity = styleService.findByStyleIdWithItems(50);
		System.out.println(styleEntity.getItems());
		Set<ItemEntity> items = styleEntity.getItems();
		for (Iterator<ItemEntity> iterator = items.iterator(); iterator
				.hasNext();) {
			ItemEntity itemEntity = iterator.next();
			System.out.println(itemEntity.getItemSizes());
			Set<ItemSizeEntity> itemSizes = itemEntity.getItemSizes();
			for (ItemSizeEntity itemSizeEntity : itemSizes) {
				System.out.println(itemSizeEntity);
			}

		}

	}

	@Test
	public void testDeleteStyle() {
		Service styleService = (Service) ctx.getBean("serviceImpl");
		styleService.deleteStyle(166);

	}

	// Retrieved data using Constructor
	@Test
	public void testRetrieveStyleWithEntityManager() {
		Service entityManagerService = (Service) ctx
				.getBean("entityManagerService");
		StyleEntity styleEntity = entityManagerService.findByStyleId(50);
		System.out
				.println(styleEntity.getDesc() + " " + styleEntity.getItems());
		/*
		 * Iterable<StyleEntity>
		 * styleIterable=entityManagerService.findAllStyles(); for (StyleEntity
		 * styleEntity : styleIterable) {
		 * System.out.println(styleEntity.getId()+" "+styleEntity.getStyleNo());
		 * }
		 */
	}

	// Retrieved data using Native query
	@Test
	public void testRetrieveStyleUsingNativeQuery() {
		Service entityManagerService = (Service) ctx
				.getBean("entityManagerService");

		StyleEntity styleEntity = entityManagerService.findByStyleId(50);
		System.out.println(styleEntity);
		/*
		 * Iterable<StyleEntity>
		 * styleIterable=entityManagerService.findAllStyles(); for (StyleEntity
		 * styleEntity : styleIterable) {
		 * System.out.println(styleEntity.getId()); }
		 */
	}

	// Retrieved data using Named query
	@Test
	public void testRetrieveStyleUsingNamedQuery() {
		Service entityManagerService = (Service) ctx
				.getBean("entityManagerService");
		StyleEntity styleEntity = entityManagerService.findByStyleId(50);
		System.out.println(styleEntity.getItems());
		/*
		 * Iterable<StyleEntity>
		 * styleIterable=entityManagerService.findAllStyles(); for (StyleEntity
		 * styleEntity : styleIterable) {
		 * System.out.println(styleEntity.getId()+
		 * " "+styleEntity.getStyleNo()+" "+styleEntity.getDesc()); }
		 */
	}

	@Test
	public void testSaveStyleWithEntityManager() {
		Service entityMangaer = (Service) ctx.getBean("entityManagerService");

		ClientEntity client = masterService.findClientById(1);
		CountryEntity country = masterService.findCountryById(2);
		SeasonEntity season = masterService.findSeasonById(2);
		SizeEntity size = masterService.findSizeById(1);

		Set<ItemEntity> itemEntites = new HashSet<ItemEntity>();
		Set<ItemSizeEntity> itemSizeEntites = new HashSet<ItemSizeEntity>();

		StyleEntity styleEntity = new StyleEntity();
		// styleEntity.setId(37);
		styleEntity.setStyleNo("Jeans");
		styleEntity.setDesc("JACKET");
		styleEntity.setItems(itemEntites);
		styleEntity.setClient(client);
		styleEntity.setCountry(country);
		styleEntity.setSeason(season);

		ItemEntity item1 = new ItemEntity();
		item1.setItemNo("HEMLATA545");
		item1.setColor("Red");

		ItemSizeEntity itemSizeEntity1 = new ItemSizeEntity();
		itemSizeEntity1.setQuantity(10);
		itemSizeEntity1.setSize(size);
		itemSizeEntity1.setItem(item1);

		ItemSizeEntity itemSizeEntity2 = new ItemSizeEntity();
		itemSizeEntity2.setQuantity(20);
		itemSizeEntity2.setSize(size);
		itemSizeEntity2.setItem(item1);

		itemSizeEntites.add(itemSizeEntity1);
		itemSizeEntites.add(itemSizeEntity2);

		item1.setItemSizes(itemSizeEntites);
		item1.setStyle(styleEntity);

		itemEntites.add(item1);

		entityMangaer.saveStyle(styleEntity);

		ctx.close();
	}

	@Test
	public void testHibernateInitializer() {

		Service entityMangaer = (Service) ctx.getBean("entityManagerService");
		StyleEntity styleEntity = entityMangaer.findByStyleId(61);
		Set<ItemEntity> itemEntities = styleEntity.getItems();
		for (ItemEntity itemEntity : itemEntities) {
			System.out.println(itemEntity.getColor() + " "
					+ itemEntity.getItemSizes());
		}
	}

	@Test
	public void testValidation() {
		EntityManagerServiceImpl entityMangaer = (EntityManagerServiceImpl) ctx
				.getBean("entityManagerService");

		ClientEntity client = masterService.findClientById(1);
		CountryEntity country = masterService.findCountryById(2);
		SeasonEntity season = masterService.findSeasonById(2);
		SizeEntity size = masterService.findSizeById(1);

		Set<ItemEntity> itemEntites = new HashSet<ItemEntity>();
		Set<ItemSizeEntity> itemSizeEntites = new HashSet<ItemSizeEntity>();

		StyleEntity styleEntity = new StyleEntity();
		styleEntity.setStyleNo("ay1505");
		styleEntity.setDesc("JACKET");
		styleEntity.setItems(itemEntites);
		styleEntity.setClient(client);
		styleEntity.setCountry(country);
		styleEntity.setSeason(season);

		ItemEntity item1 = new ItemEntity();
		item1.setItemNo("HEMLATA545");
		item1.setColor("Red");

		ItemSizeEntity itemSizeEntity1 = new ItemSizeEntity();
		itemSizeEntity1.setQuantity(10);
		itemSizeEntity1.setSize(size);
		itemSizeEntity1.setItem(item1);

		ItemSizeEntity itemSizeEntity2 = new ItemSizeEntity();
		itemSizeEntity2.setQuantity(20);
		itemSizeEntity2.setSize(size);
		itemSizeEntity2.setItem(item1);

		itemSizeEntites.add(itemSizeEntity1);
		itemSizeEntites.add(itemSizeEntity2);

		item1.setItemSizes(itemSizeEntites);
		item1.setStyle(styleEntity);

		itemEntites.add(item1);

		if (entityMangaer.isStyleExist(styleEntity, season, client))
			System.out.println("Duplicate");
		else
			entityMangaer.saveStyle(styleEntity);

		ctx.close();
	}

}
