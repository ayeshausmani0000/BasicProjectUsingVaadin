package com.example.BasicProjectUsingVaadin.dao;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.example.BasicProjectUsingVaadin.dto.StyleDto;
import com.example.BasicProjectUsingVaadin.dto.StyleOverViewFilterDto;
import com.example.BasicProjectUsingVaadin.model.ClientEntity;
import com.example.BasicProjectUsingVaadin.model.ItemEntity;
import com.example.BasicProjectUsingVaadin.model.ItemSizeEntity;
import com.example.BasicProjectUsingVaadin.model.SeasonEntity;
//import com.example.BasicProjectUsingVaadin.model.StyleEntity;
import com.example.BasicProjectUsingVaadin.model.StyleOverFilter;

@RestController
public interface PresenterDao {
	public void saveStyle(StyleDto styleDto);

	public Iterable<StyleDto> findAllStyles();

	public StyleDto findByStyleId(Integer id);
	
	public void deleteStyle(Integer id);

	public void saveItemSize(ItemSizeEntity itemSizeEntity);

	public Iterable<ItemSizeEntity> findAllItemSize();

	public ItemSizeEntity findByItemSizeId(Integer id);

	public void saveItem(ItemEntity itemEntity);

	public Iterable<ItemEntity> findAllItems();
	
	StyleDto findByStyleIdWithItems(Integer styleid);

	public ItemEntity findByItemId(Integer id);
	
	public boolean isStyleExist(StyleDto styleEntity, SeasonEntity seasonEntity, ClientEntity clientEntity);
	
	public boolean isStyleExistV(StyleDto styleEntity);
	
	public boolean validateUser(String username,String password);
	
	public List<ItemEntity> findByItemNumber(String itemNo);
	
	public Iterable<StyleDto> filterByStyleNoAndCountry(StyleOverViewFilterDto filterEntity);
}
