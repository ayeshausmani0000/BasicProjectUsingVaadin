package com.example.BasicProjectUsingVaadin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.BasicProjectUsingVaadin.model.LoginEntity;

public interface LoginRepository extends CrudRepository<LoginEntity, Integer> {
	@Query("SELECT s FROM LoginEntity s WHERE s.username =:username AND s.password=:password")
	LoginEntity findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
