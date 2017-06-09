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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@SequenceGenerator(name = "STYLE_SEQ", allocationSize = 1, sequenceName="BASICSEQ")
@Table(name = "style")
@NamedEntityGraph(name = "graph.Style.items", 
attributeNodes = @NamedAttributeNode(value = "items",subgraph="graph.itemSizes"),
subgraphs = @NamedSubgraph(name = "graph.itemSizes", attributeNodes = @NamedAttributeNode("itemSizes")))
//@NamedQuery(name="selectQuery" ,query="SELECT s FROM StyleEntity s LEFT JOIN FETCH s.items WHERE s.id =:sid")
public class StyleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STYLE_SEQ")
	@Column(name = "style_id")
	private Integer id;

	@Column(name = "style_no")
	private String styleNo;

	@Column(name = "style_desc")
	private String desc;

	@ManyToOne
	@JoinColumn(name = "season_id")
	private SeasonEntity season;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private CountryEntity country;

	@OneToMany(mappedBy = "style", cascade = CascadeType.ALL,orphanRemoval=true)
	//@Transient
	//@LazyCollection(LazyCollectionOption.TRUE)
	private Set<ItemEntity> items;

	@ManyToOne
	@JoinColumn(name="client_id")
	private ClientEntity client;

	public StyleEntity() 
	{
		
	}
	
	public StyleEntity(Integer id, String styleNo) 
	{
		this.id=id;
		this.styleNo=styleNo;
	}
	
	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}

	public Set<ItemEntity> getItems() {
		return items;
	}

	public void setItems(Set<ItemEntity> items) {
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

	public SeasonEntity getSeason() {
		return season;
	}

	public void setSeason(SeasonEntity seasonId) {
		this.season = seasonId;
	}

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(CountryEntity countryid) {
		this.country = countryid;
	}

	@Override
	public String toString() {
		return "StyleEntity [id=" + id + ", styleNo=" + styleNo + ", desc="
				+ desc + ", season=" + season + ", country=" + country
				+ ", client=" + client + "]";
	}


	
	

}
