package com.example.BasicProjectUsingVaadin.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import com.example.BasicProjectUsingVaadin.dao.ServiceRestDao;
import com.example.BasicProjectUsingVaadin.dto.CountryDto;
import com.example.BasicProjectUsingVaadin.dto.StyleDto;
import com.example.BasicProjectUsingVaadin.dto.StyleOverViewFilterDto;
import com.example.BasicProjectUsingVaadin.model.ClientEntity;
import com.example.BasicProjectUsingVaadin.model.CountryEntity;
import com.example.BasicProjectUsingVaadin.model.SeasonEntity;
import com.example.BasicProjectUsingVaadin.model.StyleEntity;
import com.example.BasicProjectUsingVaadin.model.StyleOverFilter;
import com.example.BasicProjectUsingVaadin.service.Service;

@RestController
public class ServiceRestDaoImpl implements ServiceRestDao 
{
	@Autowired
	@Qualifier("springDataServiceImpl")
	private Service service;

	@Override
	public List<StyleDto> findAllStyles() 
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
	public void createStyle(StyleDto styleDto) 
	{
		StyleEntity style=convertStyleDtoToEntity(styleDto);
		
		service.saveStyle(style);
	}

	@Override
	public StyleDto findByStyleId(Integer id) 
	{
		StyleEntity styleEntity = service.findByStyleId(id);
		StyleDto styleDto=convertStyleEntityToDTO(styleEntity);
		return styleDto;
	}

	@Override
	public void updateStyle(StyleDto styleDto) 
	{
		StyleEntity style=service.findByStyleId(styleDto.getId());
		style.setStyleNo(styleDto.getStyleNo());
		style.setDesc(styleDto.getDesc());
		style.setCountry(convertCountryDtoToEntity(styleDto.getCountry()));
		service.saveStyle(style);
		
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
	
		style.setCountry(convertCountryDtoToEntity(styleDto.getCountry()));
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
	public List<StyleDto> filterByStyleNoAndCountry(StyleOverViewFilterDto filterEntity) 
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
	
	@Override
	public boolean validateUser(String username, String password) {
		if(service.validateUser(username, password))
			return true;
		return false;
	}
	
	public StyleEntity convertStyleDtoToEntity(StyleDto styleDto){

	StyleEntity styleEntity = new StyleEntity();

	styleEntity.setStyleNo(styleDto.getStyleNo());
	styleEntity.setDesc(styleDto.getDesc());
	styleEntity.setCountry(convertCountryDtoToEntity(styleDto.getCountry()));
	
	return styleEntity;
	}
	
	
	public StyleDto convertStyleEntityToDTO(StyleEntity styleEntity)
	{
		StyleDto styleDto = new StyleDto();
	
		styleDto.setId(styleEntity.getId());
		styleDto.setStyleNo(styleEntity.getStyleNo());
		styleDto.setDesc(styleEntity.getDesc());
		styleDto.setCountry(convertCountryEntityToDTO(styleEntity.getCountry()));
		
		return styleDto;
	}
	
	public CountryEntity convertCountryDtoToEntity(CountryDto countryDto)
	{
		CountryEntity countryEntity = new CountryEntity();
		
		countryEntity.setId(countryDto.getId());
		countryEntity.setIsoCode(countryDto.getIsoCode());
		countryEntity.setName(countryDto.getName());
		
		return countryEntity;
	}
	
	
	public CountryDto convertCountryEntityToDTO(CountryEntity countryEntity)
	{
		CountryDto countryDto = new CountryDto();
		
		countryDto.setId(countryDto.getId());
		countryDto.setIsoCode(countryDto.getIsoCode());
		countryDto.setName(countryDto.getName());
		
		return countryDto;
	}

	

	
}
