package com.ctbapit.dao;


import org.springframework.data.repository.CrudRepository;

import com.ctbapit.entity.CurrencyEntity;

public interface ICoinDao extends CrudRepository<CurrencyEntity, Integer>{
	
	/**
	 * @description 查詢幣別資料 by code
	 */
	public CurrencyEntity findByCode(String code);
	
}
