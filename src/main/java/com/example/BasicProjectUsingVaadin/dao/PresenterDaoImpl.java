package com.example.BasicProjectUsingVaadin.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import com.example.BasicProjectUsingVaadin.model.ClientEntity;
import com.example.BasicProjectUsingVaadin.model.ItemEntity;
import com.example.BasicProjectUsingVaadin.model.ItemSizeEntity;
import com.example.BasicProjectUsingVaadin.model.SeasonEntity;
import com.example.BasicProjectUsingVaadin.model.StyleEntity;
import com.example.BasicProjectUsingVaadin.service.Service;

@RestController
public class PresenterDaoImpl implements PresenterDao {
	@Autowired
	@Qualifier("springDataServiceImpl")
	private Service service;

	@Override
	public void saveStyle(StyleEntity styleEntity) {
		service.saveStyle(styleEntity);
	}

	@Override
	public Iterable<StyleEntity> findAllStyles() {
		Iterable<StyleEntity> styleEntities = service.findAllStyles();
		List<StyleEntity> styleEntities1 = new ArrayList<StyleEntity>();

		for (StyleEntity styleEntity : styleEntities) {
			StyleEntity styles = new StyleEntity();
			styles.setStyleNo(styleEntity.getStyleNo());
			styles.setCountry(styleEntity.getCountry());
			styles.setDesc(styleEntity.getDesc());
			styleEntities1.add(styleEntity);
		}
		return styleEntities1;
	}

	@Override
	public StyleEntity findByStyleId(Integer id) {

		return null;
	}

	@Override
	public void deleteStyle(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveItemSize(ItemSizeEntity itemSizeEntity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<ItemSizeEntity> findAllItemSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemSizeEntity findByItemSizeId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveItem(ItemEntity itemEntity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<ItemEntity> findAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StyleEntity findByStyleIdWithItems(Integer styleid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemEntity findByItemId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isStyleExist(StyleEntity styleEntity, SeasonEntity seasonEntity, ClientEntity clientEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStyleExistV(StyleEntity styleEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateUser(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ItemEntity> findByItemNumber(String itemNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
