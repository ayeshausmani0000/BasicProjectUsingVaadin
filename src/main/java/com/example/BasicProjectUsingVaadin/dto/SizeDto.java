package com.example.BasicProjectUsingVaadin.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class SizeDto {
	
	private Integer sizeId;
	
	private String sizeCode;
		
	public Integer getSizeId() {
		return sizeId;
	}
	public void setSizeId(Integer sizeId) {
		this.sizeId = sizeId;
	}
	public String getSizeCode() {
		return sizeCode;
	}
	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}
	@Override
	public String toString() {
		return "SizeEntity [sizeCode=" + sizeCode + "]";
	}
	
	

	
}
