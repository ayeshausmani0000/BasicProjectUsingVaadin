package com.example.BasicProjectUsingVaadin.dto;

import java.util.Set;

public class ItemDto {

	private Integer itemId;

	private String color;

	private String itemNo;

	private StyleDto style;

	private Set<ItemSizeDto> itemSizes;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public StyleDto getStyle() {
		return style;
	}

	public void setStyle(StyleDto style) {
		this.style = style;
	}

	public Set<ItemSizeDto> getItemSizes() {
		return itemSizes;
	}

	public void setItemSizes(Set<ItemSizeDto> itemSizes) {
		this.itemSizes = itemSizes;
	}

	@Override
	public String toString() {
		return itemId +" "+ color +" "+ itemNo;
	}

}
