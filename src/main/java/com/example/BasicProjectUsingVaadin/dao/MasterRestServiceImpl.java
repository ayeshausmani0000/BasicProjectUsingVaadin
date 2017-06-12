package com.example.BasicProjectUsingVaadin.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.BasicProjectUsingVaadin.dto.ClientDTO;
import com.example.BasicProjectUsingVaadin.dto.CountryDTO;
import com.example.BasicProjectUsingVaadin.dto.SeasonDTO;
import com.example.BasicProjectUsingVaadin.dto.SizeDTO;
import com.example.BasicProjectUsingVaadin.impl.MasterServiceImpl;
import com.example.BasicProjectUsingVaadin.model.CountryEntity;
import com.example.BasicProjectUsingVaadin.service.MasterService;


@RestController
public class MasterRestServiceImpl implements MasterRestService{

	@Autowired
	private MasterServiceImpl masterServiceImpl;
	
	@Override
	public List<SeasonDTO> findAllSeason() {
		return null;
	}

	@Override
	public List<CountryDTO> findAllCountry() {
		Iterable<CountryEntity> countryEntities=masterServiceImpl.findAllCountry();
		List<CountryDTO> countryDTOs=new ArrayList<CountryDTO>();
		for (CountryEntity countryEntity : countryEntities) {
			CountryDTO countryDTO=new CountryDTO();
			countryDTO.setName(countryEntity.getName());
			countryDTO.setIsoCode(countryEntity.getIsoCode());
			countryDTOs.add(countryDTO);
		}
		return countryDTOs;
	}

	@Override
	public List<ClientDTO> findAllClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SizeDTO> findAllSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SeasonDTO findSeasonById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CountryDTO findCountryById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientDTO findClientById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SizeDTO findSizeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
