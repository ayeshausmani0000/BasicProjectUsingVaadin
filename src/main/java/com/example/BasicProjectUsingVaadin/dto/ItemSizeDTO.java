package com.example.BasicProjectUsingVaadin.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


public class ItemSizeDTO {

	
	private Integer itemsizeId;


	private Integer quantity;

	

	public Integer getItemsizeId() {
		return itemsizeId;
	}

	public void setItemsizeId(Integer itemsizeId) {
		this.itemsizeId = itemsizeId;
	}


	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	


	
	
}
