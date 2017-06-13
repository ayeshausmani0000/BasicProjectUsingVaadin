package com.example.BasicProjectUsingVaadin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.example.BasicProjectUsingVaadin.model.CountryEntity;
import com.example.BasicProjectUsingVaadin.model.StyleEntity;
import com.example.BasicProjectUsingVaadin.model.StyleOverFilter;
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

	@Override
	public List<StyleEntity> filterByStyleNoAndCountry(StyleOverFilter filterEntity) {
    String styleNo=filterEntity.getStyleNo();
    CountryEntity country=filterEntity.getCountry();
		StringBuffer sb = new StringBuffer();
		Query query = null;
		sb.append("SELECT s  FROM StyleEntity s WHERE  1 = 1 ");
		
		if (styleNo != null && !styleNo.equals("")) {
			sb.append(" AND s.styleNo=:styleNumber");
		}
		if (country != null) {
			sb.append(" AND s.country=:country  ");
		}
		query = entityManager.createQuery(sb.toString());
		if (styleNo != null && !styleNo.equals("")) {
			query.setParameter("styleNumber", styleNo);
		}
		if (country != null) {
			query.setParameter("country", country);
		}

		List<StyleEntity> styles = query.getResultList();
		return styles;
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
