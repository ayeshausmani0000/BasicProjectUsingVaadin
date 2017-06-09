package com.example.BasicProjectUsingVaadin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "ITEM_SIZE_SEQ", allocationSize = 1, sequenceName="BASICSEQ")
@Table(name = "itemsize")
public class ItemSizeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SIZE_SEQ")
	@Column(name = "itemsize_id")
	private Integer itemsizeId;

	@ManyToOne
	@JoinColumn(name = "size_id")
	private SizeEntity size;

	@Column(name = "quantity")
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name="item_id")
	private ItemEntity item;

	public ItemEntity getItem() {
		return item;
	}

	public void setItem(ItemEntity item) {
		this.item = item;
	}

	public Integer getItemsizeId() {
		return itemsizeId;
	}

	public void setItemsizeId(Integer itemsizeId) {
		this.itemsizeId = itemsizeId;
	}

	public SizeEntity getSize() {
		return size;
	}

	public void setSize(SizeEntity size) {
		this.size = size;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ItemSizeEntity [itemsizeId=" + itemsizeId + ", size=" + size
				+ ", quantity=" + quantity + "]";
	}


	
	
}
