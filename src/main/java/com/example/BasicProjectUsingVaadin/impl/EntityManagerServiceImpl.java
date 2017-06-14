package com.example.BasicProjectUsingVaadin.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.BasicProjectUsingVaadin.dao.EMDao;
import com.example.BasicProjectUsingVaadin.model.ClientEntity;
import com.example.BasicProjectUsingVaadin.model.ItemEntity;
import com.example.BasicProjectUsingVaadin.model.ItemSizeEntity;
import com.example.BasicProjectUsingVaadin.model.SeasonEntity;
import com.example.BasicProjectUsingVaadin.model.StyleEntity;
import com.example.BasicProjectUsingVaadin.model.StyleOverFilter;
import com.example.BasicProjectUsingVaadin.service.Service;
import com.vaadin.spring.annotation.SpringComponent;

@SpringComponent
public class EntityManagerServiceImpl implements Service {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unused")
	@Autowired
	private DataSource dataSource;
	
	@SuppressWarnings("unused")
	@Autowired
	private EMDao emDao;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	@Transactional
	public void saveStyle(StyleEntity styleEntity) 
	{	
		if (styleEntity.getId() == null) 
		{
			entityManager.persist(styleEntity);
		} else 
		{
			
			entityManager.merge(styleEntity);
		}

	}

	//Retrieval using constructor
/*	@Override
	public Iterable<StyleEntity> findAllStyles() {
		Query query = entityManager.createQuery("SELECT new StyleEntity(s.id,s.styleNo) from StyleEntity s ");
		Iterable<StyleEntity> styleEntity = query.getResultList();		
		return styleEntity;
	}*/
	
	
	//Retrieval using native query
	/*@Override
	public Iterable<StyleEntity> findAllStyles() {
		List<StyleEntity> styleEntity = entityManager.createNativeQuery("SELECT * from style",StyleEntity.class).getResultList();
		for (StyleEntity styleEntity2 : styleEntity) 
		{
			
		}
		return styleEntity;
	}*/
	
	//Retrieval using named query
	@Override
	
	public Iterable<StyleEntity> findAllStyles() {
		Query query = entityManager.createNamedQuery("selectQuery");
		@SuppressWarnings("unchecked")
		Iterable<StyleEntity> styleEntity = query.getResultList();		
		return styleEntity;
	}

	@Override
	@Transactional(propagation=Propagation.NEVER)
	public ItemEntity findByItemId(Integer id) 
	{
		ItemEntity itemEntity = entityManager.find(ItemEntity.class, id);
		return itemEntity;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public StyleEntity findByStyleId(Integer id) 
	{

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT s FROM StyleEntity s ");
		sb.append(" LEFT JOIN FETCH s.items i ");
		sb.append(" LEFT JOIN FETCH i.itemSizes  ");
		sb.append("  WHERE s.id =:sid ");
		
		ItemEntity itemEntity=findByItemId(52);
		System.out.println(itemEntity.getItemNo());
		Query query= entityManager.createQuery(sb.toString());
		query.setParameter("sid", id);
		
		return (StyleEntity) query.getSingleResult();
		/*
		StyleEntity styleEntity=(StyleEntity) entityManager.createNamedQuery("StyleMappingXML").getSingleResult();
		
		return styleEntity;*/
		
	/*	StyleEntity styleEntity=entityManager.find(StyleEntity.class, id);
		Hibernate.initialize(styleEntity.getItems());
		Set<ItemEntity> itemEntities=styleEntity.getItems();
		for (ItemEntity itemEntity : itemEntities) {
			Hibernate.initialize(itemEntity.getItemSizes());
		}*/
	       /* Object styleEntity=(Object) entityManager.createNativeQuery("select i.item_id from item i join style s"
	    	 		+ " on i.style_id=s.style_id join itemsize z on i.item_id=z.item_id "+"WHERE s.style_id=61").getSingleResult();*/
	        
	    
	    //   StyleEntity styleEntity=(StyleEntity) entityManager.createNamedQuery("selectQuery").setParameter("sid", id).getSingleResult();
	     //   return styleEntity;
	}

	@Override
	public void saveItemSize(ItemSizeEntity itemSizeEntity) {
		if (itemSizeEntity.getItemsizeId() == null) {
			entityManager.persist(itemSizeEntity);
		} else {
			entityManager.merge(itemSizeEntity);
		}
	}

	@Override
	public Iterable<ItemSizeEntity> findAllItemSize() {
		Query query = entityManager.createQuery("select i from ItemSizeEntity i");
		@SuppressWarnings("unchecked")
		Iterable<ItemSizeEntity> itemSizeEntity = query.getResultList();
		return itemSizeEntity;
	}

	@Override
	public ItemSizeEntity findByItemSizeId(Integer id) {
		ItemSizeEntity itemSizeEntity = entityManager.find(
				ItemSizeEntity.class, id);
		return itemSizeEntity;
	}

	@Override
	public void saveItem(ItemEntity itemEntity) {
		if (itemEntity.getItemId() == null) {
			entityManager.persist(itemEntity);
		} else {
			entityManager.merge(itemEntity);
		}
	}

	@Override
	public Iterable<ItemEntity> findAllItems() {
		Query query = entityManager.createQuery("select i from ItemEntity i");
		@SuppressWarnings("unchecked")
		Iterable<ItemEntity> itemEntity = query.getResultList();
		return itemEntity;
	}

	

	@Override
	public void deleteStyle(Integer styleId) 
	{
		entityManager.remove(styleId);
	}

	@Override
	public StyleEntity findByStyleIdWithItems(Integer styleid) 
	{
		return null;
	}

	@Override
	public boolean isStyleExist(StyleEntity styleEntity,
			SeasonEntity seasonEntity, ClientEntity clientEntity) {
		//List<StyleEntity> styleEntities=emDao.checkByStyleNo(styleEntity, seasonEntity, clientEntity);
		//if(styleEntities.size()>0)
		//	return true;
		
		return false;
	}
	
	/*public List<StyleEntity> checkByStyleNo(StyleEntity styleEntity,
			SeasonEntity seasonEntity, ClientEntity clientEntity) 
	{
		Query query=entityManager.createQuery("SELECT s FROM StyleEntity s WHERE s.styleNo=:styleNumber AND s.season=:seasonId AND s.client=:clientId");
		query.setParameter("styleNumber", styleEntity.getStyleNo());
		query.setParameter("seasonId", seasonEntity);
		query.setParameter("clientId", clientEntity);
		List<StyleEntity> styleEntities=query.getResultList();
		return styleEntities;
	}
*/
	
	@Override
	public boolean validateUser(String username, String password)
	{
		
		
		return false;
		
	}

	@Override
	public List<ItemEntity> findByItemNumber(String itemNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isStyleExistV(StyleEntity styleEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<StyleEntity> filterByStyleNoAndCountry(StyleOverFilter filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}
}
