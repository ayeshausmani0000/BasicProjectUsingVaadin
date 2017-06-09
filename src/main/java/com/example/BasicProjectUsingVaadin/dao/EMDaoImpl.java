package com.example.BasicProjectUsingVaadin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.example.BasicProjectUsingVaadin.model.ClientEntity;
import com.example.BasicProjectUsingVaadin.model.SeasonEntity;
import com.example.BasicProjectUsingVaadin.model.StyleEntity;
import com.vaadin.spring.annotation.SpringComponent;

@SpringComponent
public class EMDaoImpl implements EMDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<StyleEntity> checkByStyleNo(StyleEntity styleEntity) {
		Query query = entityManager
				.createQuery("SELECT s FROM StyleEntity s WHERE s.styleNo=:styleNumber AND s.country=:countryId");
		query.setParameter("styleNumber", styleEntity.getStyleNo());
		query.setParameter("countryId", styleEntity.getCountry());
		List<StyleEntity> styleEntities = query.getResultList();
		return styleEntities;// TODO Auto-generated method stub
	}

	// @Override
	// public List<StyleEntity> checkByStyleNo(StyleEntity styleEntity,
	// SeasonEntity seasonEntity, ClientEntity clientEntity)
	// {
	// Query query=entityManager.createQuery("SELECT s FROM StyleEntity s WHERE
	// s.styleNo=:styleNumber AND s.season=:seasonId AND s.client=:clientId");
	// query.setParameter("styleNumber", styleEntity.getStyleNo());
	// query.setParameter("seasonId", seasonEntity);
	// query.setParameter("clientId", clientEntity);
	// @SuppressWarnings("unchecked")
	// List<StyleEntity> styleEntities=query.getResultList();
	// return styleEntities;
	// }

}
