package com.example.BasicProjectUsingVaadin.dto;

import com.example.BasicProjectUsingVaadin.model.CountryEntity;

public class StyleOverViewFilterDto {
	private String styleNo;
	private CountryDto country;

	public String getStyleNo() {
		return styleNo;
	}

	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}

	public CountryDto getCountry() {
		return country;
	}

	public void setCountry(CountryDto country) {
		this.country = country;
	}

}
