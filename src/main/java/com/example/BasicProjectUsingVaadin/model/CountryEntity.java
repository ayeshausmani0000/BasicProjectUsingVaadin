package com.example.BasicProjectUsingVaadin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="countries")
public class CountryEntity 
{
	@Override
	public String toString() {
		return  name ;
	}
	@Id
	@Column(name="country_id")
	private Integer id;
	
	@Column(name="country_iso_code")
	private String isoCode;
	
	@Column(name="country_name")
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIsoCode() {
		return isoCode;
	}
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
