package com.example.BasicProjectUsingVaadin.dao;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import com.example.BasicProjectUsingVaadin.dto.StyleDto;
import com.example.BasicProjectUsingVaadin.dto.StyleOverViewFilterDto;


@RestController
public interface ServiceRestDao {
	public void createStyle(StyleDto styleDto);
	
	public void updateStyle(StyleDto styleDto);

	public List<StyleDto> findAllStyles();

	public StyleDto findByStyleId(Integer id);
	
	public void deleteStyle(Integer id);
	
	public boolean isStyleExistV(StyleDto styleEntity);
	
	public boolean validateUser(String username,String password);
	
	public List<StyleDto> filterByStyleNoAndCountry(StyleOverViewFilterDto filterEntity);
}
