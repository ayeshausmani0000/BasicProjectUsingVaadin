package com.example.BasicProjectUsingVaadin.dto;

public class ItemSizeDto {
	private Integer itemsizeId;

	private SizeDto size;

	private Integer quantity;

	private ItemDto item;

	public Integer getItemsizeId() {
		return itemsizeId;
	}

	public void setItemsizeId(Integer itemsizeId) {
		this.itemsizeId = itemsizeId;
	}

	public SizeDto getSize() {
		return size;
	}

	public void setSize(SizeDto size) {
		this.size = size;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ItemDto getItem() {
		return item;
	}

	public void setItem(ItemDto item) {
		this.item = item;
	}

}
