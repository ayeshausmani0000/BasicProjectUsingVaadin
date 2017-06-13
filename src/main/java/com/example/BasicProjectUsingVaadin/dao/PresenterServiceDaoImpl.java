package com.example.BasicProjectUsingVaadin.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import com.example.BasicProjectUsingVaadin.dto.CountryDto;
import com.example.BasicProjectUsingVaadin.dto.StyleDto;
import com.example.BasicProjectUsingVaadin.dto.StyleOverViewFilterDto;
import com.example.BasicProjectUsingVaadin.model.ClientEntity;
import com.example.BasicProjectUsingVaadin.model.CountryEntity;
import com.example.BasicProjectUsingVaadin.model.ItemEntity;
import com.example.BasicProjectUsingVaadin.model.ItemSizeEntity;
import com.example.BasicProjectUsingVaadin.model.SeasonEntity;
import com.example.BasicProjectUsingVaadin.model.StyleEntity;
import com.example.BasicProjectUsingVaadin.model.StyleOverFilter;
import com.example.BasicProjectUsingVaadin.service.Service;

@RestController
public class PresenterServiceDaoImpl implements PresenterServiceDao 
{
	@Autowired
	@Qualifier("springDataServiceImpl")
	private Service service;

	@Override
	public Iterable<StyleDto> findAllStyles() 
	{
		Iterable<StyleEntity> styleEntities = service.findAllStyles();
		List<StyleDto> styleEntities1 = new ArrayList<StyleDto>();

		for (StyleEntity styleEntity : styleEntities) {
			StyleDto styles = new StyleDto();
			styles.setId(styleEntity.getId());
			styles.setStyleNo(styleEntity.getStyleNo());
			styles.setDesc(styleEntity.getDesc());
		
			CountryDto countryDto = new CountryDto();
			countryDto.setId(styleEntity.getCountry().getId());
			countryDto.setIsoCode(styleEntity.getCountry().getIsoCode());
			countryDto.setName(styleEntity.getCountry().getName());
			styles.setCountry(countryDto);
		
			styleEntities1.add(styles);
		}
		return styleEntities1;
	}

	@Override
	public void deleteStyle(Integer id) 
	{
		service.deleteStyle(id);
	}

	@Override
	public void saveItemSize(ItemSizeEntity itemSizeEntity) 
	{

	}

	@Override
	public Iterable<ItemSizeEntity> findAllItemSize() 
	{
		return null;
	}

	@Override
	public ItemSizeEntity findByItemSizeId(Integer id) 
	{
		return null;
	}

	@Override
	public void saveItem(ItemEntity itemEntity) 
	{

	}

	@Override
	public Iterable<ItemEntity> findAllItems() 
	{
		return null;
	}

	@Override
	public ItemEntity findByItemId(Integer id) 
	{
		return null;
	}

	@Override
	public boolean validateUser(String username, String password) 
	{
		return false;
	}

	@Override
	public List<ItemEntity> findByItemNumber(String itemNo) 
	{
		return null;
	}

	@Override
	public void saveStyle(StyleDto styleDto) 
	{
		StyleEntity style = new StyleEntity();
		style.setId(styleDto.getId());
		style.setStyleNo(styleDto.getStyleNo());
		style.setDesc(styleDto.getDesc());
		CountryEntity country = new CountryEntity();
		country.setId(styleDto.getCountry().getId());
		country.setIsoCode(styleDto.getCountry().getIsoCode());
		country.setName(styleDto.getCountry().getName());
		style.setCountry(country);

		service.saveStyle(style);
	}

	@Override
	public StyleDto findByStyleId(Integer id) 
	{
		StyleDto styleDto = new StyleDto();
		StyleEntity styleEntity = service.findByStyleId(id);
		styleDto.setId(styleEntity.getId());
		styleDto.setStyleNo(styleEntity.getStyleNo());
		styleDto.setDesc(styleEntity.getDesc());
		return styleDto;
	}

	@Override
	public StyleDto findByStyleIdWithItems(Integer styleid) 
	{
		return null;
	}

	@Override
	public boolean isStyleExist(StyleDto styleEntity, SeasonEntity seasonEntity, ClientEntity clientEntity) 
	{
		return false;
	}

	@Override
	public boolean isStyleExistV(StyleDto styleDto) 
	{
		StyleEntity style = new StyleEntity();
		style.setId(styleDto.getId());
		style.setStyleNo(styleDto.getStyleNo());
		style.setDesc(styleDto.getDesc());
		if (style.getSeason() != null) 
		{
			SeasonEntity season = new SeasonEntity();
			season.setId(styleDto.getSeason().getId());
			season.setDescription(styleDto.getSeason().getDescription());
			season.setName(styleDto.getSeason().getName());
			style.setSeason(season);
		}
		CountryEntity country = new CountryEntity();
		country.setId(styleDto.getCountry().getId());
		country.setIsoCode(styleDto.getCountry().getIsoCode());
		country.setName(styleDto.getCountry().getName());
		style.setCountry(country);
		if (style.getClient() != null) 
		{
			ClientEntity client = new ClientEntity();
			client.setId(styleDto.getClient().getId());
			client.setClientName(styleDto.getClient().getClientName());
			style.setClient(client);
		}
		return service.isStyleExistV(style);

	}

	@Override
	public Iterable<StyleDto> filterByStyleNoAndCountry(StyleOverViewFilterDto filterEntity) 
	{

		StyleOverFilter filterStyleEntity = new StyleOverFilter();
		filterStyleEntity.setStyleNo(filterEntity.getStyleNo());
		CountryEntity country = new CountryEntity();
		if (filterEntity.getCountry() != null) 
		{
			country.setId(filterEntity.getCountry().getId());
			country.setIsoCode(filterEntity.getCountry().getIsoCode());
			country.setName(filterEntity.getCountry().getName());
			filterStyleEntity.setCountry(country);
		}
		Iterable<StyleEntity> styleEntities = service.filterByStyleNoAndCountry(filterStyleEntity);
		List<StyleDto> styleEntities1 = new ArrayList<StyleDto>();

		for (StyleEntity styleEntity : styleEntities) 
		{
			StyleDto styles = new StyleDto();
			styles.setId(styleEntity.getId());
			styles.setStyleNo(styleEntity.getStyleNo());
			styles.setDesc(styleEntity.getDesc());

			CountryDto countryDto = new CountryDto();
			countryDto.setId(styleEntity.getCountry().getId());
			countryDto.setIsoCode(styleEntity.getCountry().getIsoCode());
			countryDto.setName(styleEntity.getCountry().getName());
			styles.setCountry(countryDto);
		
			styleEntities1.add(styles);
		}
		return styleEntities1;
	}

}
