package com.example.BasicProjectUsingVaadin.dao;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.example.BasicProjectUsingVaadin.model.ClientEntity;
import com.example.BasicProjectUsingVaadin.model.ItemEntity;
import com.example.BasicProjectUsingVaadin.model.ItemSizeEntity;
import com.example.BasicProjectUsingVaadin.model.SeasonEntity;
import com.example.BasicProjectUsingVaadin.model.StyleEntity;

@RestController
public interface PresenterDao {
	public void saveStyle(StyleEntity styleEntity);

	public Iterable<StyleEntity> findAllStyles();

	public StyleEntity findByStyleId(Integer id);
	
	public void deleteStyle(Integer id);

	public void saveItemSize(ItemSizeEntity itemSizeEntity);

	public Iterable<ItemSizeEntity> findAllItemSize();

	public ItemSizeEntity findByItemSizeId(Integer id);

	public void saveItem(ItemEntity itemEntity);

	public Iterable<ItemEntity> findAllItems();
	
	StyleEntity findByStyleIdWithItems(Integer styleid);

	public ItemEntity findByItemId(Integer id);
	
	public boolean isStyleExist(StyleEntity styleEntity, SeasonEntity seasonEntity, ClientEntity clientEntity);
	
	public boolean isStyleExistV(StyleEntity styleEntity);
	
	public boolean validateUser(String username,String password);
	
	public List<ItemEntity> findByItemNumber(String itemNo);
}
