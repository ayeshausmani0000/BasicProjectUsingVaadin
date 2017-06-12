package com.example.BasicProjectUsingVaadin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.BasicProjectUsingVaadin.impl.SpringDataServiceImpl;

@RestController
public class StyleController {
	@Autowired
	private SpringDataServiceImpl serviceImpl;
	
}
