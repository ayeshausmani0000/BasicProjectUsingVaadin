package com.example.BasicProjectUsingVaadin.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "ITEM_SEQ", allocationSize = 1, sequenceName="BASICSEQ")
@Table(name = "item")
public class ItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SEQ")
	@Column(name = "item_id")
	private Integer itemId;

	@Column(name = "item_color")
	private String color;

	@Column(name = "item_number")
	private String itemNo;

	@ManyToOne
	@JoinColumn(name = "style_id")
	private StyleEntity style;

	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL,orphanRemoval=true)
	private Set<ItemSizeEntity> itemSizes;

	public Set<ItemSizeEntity> getItemSizes() {
		return itemSizes;
	}

	public void setItemSizes(Set<ItemSizeEntity> itemSizes) {
		this.itemSizes = itemSizes;
	}

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

	public StyleEntity getStyle() {
		return style;
	}

	public void setStyle(StyleEntity style) {
		this.style = style;
	}

	@Override
	public String toString() {
		return "ItemEntity [itemId=" + itemId + ", color=" + color
				+ ", itemNo=" + itemNo + "]";
	}

}
