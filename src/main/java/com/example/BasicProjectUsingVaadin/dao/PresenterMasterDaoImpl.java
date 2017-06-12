package com.example.BasicProjectUsingVaadin.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.BasicProjectUsingVaadin.dto.ClientDto;
import com.example.BasicProjectUsingVaadin.dto.CountryDto;
import com.example.BasicProjectUsingVaadin.dto.SeasonDto;
import com.example.BasicProjectUsingVaadin.dto.SizeDto;
import com.example.BasicProjectUsingVaadin.model.ClientEntity;
import com.example.BasicProjectUsingVaadin.model.CountryEntity;
import com.example.BasicProjectUsingVaadin.model.SeasonEntity;
import com.example.BasicProjectUsingVaadin.model.SizeEntity;
import com.example.BasicProjectUsingVaadin.service.MasterService;

@RestController
public class PresenterMasterDaoImpl implements PresenterMasterDao {

	@Autowired
	private MasterService masterService;

	@Override
	public Iterable<SeasonDto> findAllSeason() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<CountryDto> findAllCountry() {
		Iterable<CountryEntity> countries = masterService.findAllCountry();
		List<CountryDto> countriesDto = new ArrayList<CountryDto>();
		for (CountryEntity countryEntity : countries) {
			CountryDto countryDto = new CountryDto();
			countryDto.setId(countryEntity.getId());
			countryDto.setIsoCode(countryEntity.getIsoCode());
			countryDto.setName(countryEntity.getName());
			countriesDto.add(countryDto);
		}
		return countriesDto;
	}

	@Override
	public Iterable<ClientDto> findAllClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<SizeDto> findAllSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SeasonEntity findSeasonById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CountryEntity findCountryById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientEntity findClientById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SizeEntity findSizeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
