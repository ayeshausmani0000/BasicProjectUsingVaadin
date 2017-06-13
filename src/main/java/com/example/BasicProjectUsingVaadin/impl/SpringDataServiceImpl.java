package com.example.BasicProjectUsingVaadin.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.BasicProjectUsingVaadin.dao.EMDao;
import com.example.BasicProjectUsingVaadin.model.ClientEntity;
import com.example.BasicProjectUsingVaadin.model.ItemEntity;
import com.example.BasicProjectUsingVaadin.model.ItemSizeEntity;
import com.example.BasicProjectUsingVaadin.model.LoginEntity;
import com.example.BasicProjectUsingVaadin.model.SeasonEntity;
import com.example.BasicProjectUsingVaadin.model.StyleEntity;
import com.example.BasicProjectUsingVaadin.model.StyleOverFilter;
import com.example.BasicProjectUsingVaadin.repository.ItemRepository;
import com.example.BasicProjectUsingVaadin.repository.ItemSizeRepository;
import com.example.BasicProjectUsingVaadin.repository.LoginRepository;
import com.example.BasicProjectUsingVaadin.repository.StyleRepository;
import com.example.BasicProjectUsingVaadin.service.Service;
import com.vaadin.spring.annotation.SpringComponent;

@SpringComponent
public class SpringDataServiceImpl implements Service {

	@Autowired
	private StyleRepository styleRepository;

	@Autowired
	private ItemSizeRepository itemSizeRepository;

	@Autowired
	private ItemRepository itemRepository;

	@SuppressWarnings("unused")
	@Autowired
	private DataSource dataSource;

	@Autowired
	private EMDao emDao;

	@Autowired
	private LoginRepository loginRepository;

	public Set<ItemEntity> items = new HashSet<ItemEntity>();

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void saveStyle(StyleEntity styleEntity) {
		styleRepository.save(styleEntity);
	}

	@Override
	public Iterable<StyleEntity> findAllStyles() {
		return styleRepository.findAll();
	}

	@Override
	public StyleEntity findByStyleId(Integer styleid) {
		//StyleEntity styleEntity = styleRepository.findOne(styleid);
		StyleEntity styleEntity = styleRepository.findByIdUsingJpql(styleid);
		return styleEntity;

	}

	@Override
	public StyleEntity findByStyleIdWithItems(Integer styleid) {
		// StyleEntity styleEntity = styleRepository.findById(styleid);
		StyleEntity styleEntity = styleRepository.findByIdUsingJpql(styleid);
		return styleEntity;

	}

	@Override
	public void deleteStyle(Integer id) {
		styleRepository.delete(id);
	}

	@Override
	public void saveItemSize(ItemSizeEntity itemSizeEntity) {
		itemSizeRepository.save(itemSizeEntity);
	}

	@Override
	public Iterable<ItemSizeEntity> findAllItemSize() {
		return itemSizeRepository.findAll();
	}

	@Override
	public ItemSizeEntity findByItemSizeId(Integer itemSizeId) {
		return itemSizeRepository.findOne(itemSizeId);
	}

	@Override
	public void saveItem(ItemEntity itemEntity) {
		itemRepository.save(itemEntity);
	}

	@Override
	public Iterable<ItemEntity> findAllItems() {
		return itemRepository.findAll();
	}

	@Override
	public ItemEntity findByItemId(Integer itemId) {

		return itemRepository.findOne(itemId);
		// return itemRepository.findByitemId(itemId);
		// return itemRepository.findByIdUsingJpql(itemId);
	}

	@Override
	public boolean isStyleExist(StyleEntity styleEntity, SeasonEntity seasonEntity, ClientEntity clientEntity) {

		return false;
	}

	@Override
	public boolean validateUser(String username, String password) {

		LoginEntity user = loginRepository.findByUsernameAndPassword(username, password);
		if (user != null) {
			return true;
		}
		// int count = 0;
		// Iterable<LoginEntity> loginData = loginRepository.findAll();
		//
		// for (LoginEntity loginEntity : loginData) {
		// if (loginEntity.getUsername().equals(username) &&
		// loginEntity.getPassword().equals(password))
		// count++;
		// }
		// if (count > 0)
		// return true;
		return false;

	}

	@Override
	public List<ItemEntity> findByItemNumber(String itemNo) {

		List<ItemEntity> items = new ArrayList<ItemEntity>();

		Iterable<ItemEntity> itemEntities = itemRepository.findAll();
		for (ItemEntity itemEntity : itemEntities) {
			if (itemEntity.getItemNo().equals(itemNo)) {
				items.add(itemEntity);
			}
		}

		return items;

	}

	@Override
	public boolean isStyleExistV(StyleEntity styleEntity) {
		List<StyleEntity> styles = emDao.checkByStyleNo(styleEntity);
		if (styles.size() > 0) {
			return true;
		}
		return false;
	}
	
	
	public Iterable<StyleEntity> filterByStyleNoAndCountry(StyleOverFilter filterEntity)
	{
	return emDao.filterByStyleNoAndCountry(filterEntity);
	}

	
}
