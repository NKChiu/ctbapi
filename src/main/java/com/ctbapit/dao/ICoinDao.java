package com.ctbapit.dao;


import org.springframework.data.repository.CrudRepository;

import com.ctbapit.entity.CurrencyEntity;

public interface ICoinDao extends CrudRepository<CurrencyEntity, Integer>{
	
	public CurrencyEntity findByCode(String code);
	
}
