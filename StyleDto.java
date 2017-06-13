package com.example.BasicProjectUsingVaadin.dto;

import java.util.Set;


public class StyleDto {
	private Integer id;
	private String styleNo;
	private String desc;
	private SeasonDto season;
	private CountryDto country;
	private ClientDto client;
	private Set<ItemDto> items;

	public Set<ItemDto> getItems() {
		return items;
	}

	public void setItems(Set<ItemDto> items) {
		this.items = items;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStyleNo() {
		return styleNo;
	}

	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public CountryDto getCountry() {
		return country;
	}

	public void setCountry(CountryDto country) {
		this.country = country;
	}

	public SeasonDto getSeason() {
		return season;
	}

	public void setSeason(SeasonDto season) {
		this.season = season;
	}

	public ClientDto getClient() {
		return client;
	}

	public void setClient(ClientDto client) {
		this.client = client;
	}

}
