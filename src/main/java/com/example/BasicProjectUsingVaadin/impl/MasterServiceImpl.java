package com.example.BasicProjectUsingVaadin.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.BasicProjectUsingVaadin.model.ClientEntity;
import com.example.BasicProjectUsingVaadin.model.CountryEntity;
import com.example.BasicProjectUsingVaadin.model.SeasonEntity;
import com.example.BasicProjectUsingVaadin.model.SizeEntity;
import com.example.BasicProjectUsingVaadin.repository.ClientRepository;
import com.example.BasicProjectUsingVaadin.repository.CountryRepository;
import com.example.BasicProjectUsingVaadin.repository.SeasonRepository;
import com.example.BasicProjectUsingVaadin.repository.SizeRepository;
import com.example.BasicProjectUsingVaadin.service.MasterService;
import com.vaadin.spring.annotation.SpringComponent;

@SpringComponent
public class MasterServiceImpl implements MasterService {
	@Autowired
	private SeasonRepository seasonRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private SizeRepository sizeRepository;

	@SuppressWarnings("unused")
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Iterable<SeasonEntity> findAllSeason() {
		// TODO Auto-generated method stub
		return seasonRepository.findAll();
	}

	@Override
	public Iterable<CountryEntity> findAllCountry() {
		// TODO Auto-generated method stub
		return countryRepository.findAll();
	}

	@Override
	public Iterable<ClientEntity> findAllClient() {
		// TODO Auto-generated method stub
		return clientRepository.findAll();
	}

	@Override
	public SeasonEntity findSeasonById(Integer id) {
		// TODO Auto-generated method stub
		return seasonRepository.findOne(id);
	}

	@Override
	public CountryEntity findCountryById(Integer id) {
		// TODO Auto-generated method stub
		return countryRepository.findOne(id);
	}

	@Override
	public ClientEntity findClientById(Integer id) {
		// TODO Auto-generated method stub
		return clientRepository.findOne(id);
	}

	@Override
	public Iterable<SizeEntity> findAllSize() {
		// TODO Auto-generated method stub
		return sizeRepository.findAll();
	}

	@Override
	public SizeEntity findSizeById(Integer id) {
		// TODO Auto-generated method stub
		return sizeRepository.findOne(id);
	}
	
	
}
