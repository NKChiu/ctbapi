package com.ctbapit.cointest;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ctbapit.CtbapiApplication;
import com.ctbapit.config.UnitTestConfig;
import com.ctbapit.dao.ICoinDao;
import com.ctbapit.entity.CurrencyEntity;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CtbapiApplication.class, UnitTestConfig.class})
public class CoinDaoTest {
	
	@Autowired
	private ICoinDao coinDao;
	
	@Test
	public void findAll() {
		List<CurrencyEntity> findAll = (List<CurrencyEntity>) coinDao.findAll();
		System.out.println(new Gson().toJson(findAll));
	}
	
	@Test
	public void findByCode() {
		
		CurrencyEntity currencyEntityList = coinDao.findByCode("TWD");
		System.out.println(new Gson().toJson(currencyEntityList));
	}
	
	@Test
	public void save() {
		CurrencyEntity currencyEntity = new CurrencyEntity();
		currencyEntity.setCode("TWD");
		currencyEntity.setCodeChn("台幣");
		currencyEntity.setUpdateUser("SYSTEM");
		currencyEntity.setUpdateDate(new Date());
		CurrencyEntity saveEntity = coinDao.save(currencyEntity);
		System.out.println(new Gson().toJson(saveEntity));
	}
	
	@Test
	public void udpate() {
		/**
		CurrencyEntity currencyEntity = coinDao.findById(1).get();
		currencyEntity.setCodeChn("台灣幣");
		currencyEntity.setUpdateDate(new Date());
		**/
		
		CurrencyEntity currencyEntity = coinDao.findByCode("TWD");
		currencyEntity.setCodeChn("台幣");
		currencyEntity.setUpdateDate(new Date());
		
		CurrencyEntity saveCurrency = coinDao.save(currencyEntity);
		System.out.println(new Gson().toJson(saveCurrency));
	}
	
	@Test
	public void delete() {
		/**
		CurrencyEntity currencyEntity = coinDao.findById(1).get();
		**/
		
		CurrencyEntity currencyEntity = coinDao.findByCode("TWD");
		
		coinDao.delete(currencyEntity);
	}
	
}
