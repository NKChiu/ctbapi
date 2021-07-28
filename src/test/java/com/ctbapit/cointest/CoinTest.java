package com.ctbapit.cointest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ctbapit.CtbapiApplication;
import com.ctbapit.config.UnitTestConfig;
import com.ctbapit.controller.CoinController;
import com.ctbapit.model.bean.CurrencyBean;
import com.ctbapit.model.view.CurrencyAllView;
import com.ctbapit.model.view.CurrentPriceView;
import com.ctbapit.model.view.TransCurrentPriceView;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CtbapiApplication.class, UnitTestConfig.class})
public class CoinTest {
	
	@Autowired
	private CoinController coinController;
	
	@Test
	public void getAllCurrency() {
		CurrencyAllView currencyAllView = coinController.getAllCurrency();
		System.out.println("****************************** Answer Start ******************************\n\n");
		System.out.println(new Gson().toJson(currencyAllView));
		System.out.println("\n\n ****************************** Answer End ******************************");
	}
	
	
	@Test
	public void addCurrency() {
		CurrencyBean currencyBeanInput = new CurrencyBean();
		currencyBeanInput.setCode("TWD");
		currencyBeanInput.setCodeChn("台幣");
		currencyBeanInput.setUpdateUser("SYSTEM");
		CurrencyBean output = coinController.addCurrency(currencyBeanInput);
		System.out.println("****************************** Answer Start ******************************\n\n");
		System.out.println(new Gson().toJson(output));
		System.out.println("\n\n ****************************** Answer End ******************************");
	}
	
	@Test
	public void updateCurrency() {
		CurrencyBean currencyBeanInput = new CurrencyBean();
		currencyBeanInput.setCode("TWD");
		currencyBeanInput.setCodeChn("台W幣");
		currencyBeanInput.setUpdateUser("SYSTEM2");
		CurrencyBean output = coinController.updateCurrency(currencyBeanInput);
		System.out.println("****************************** Answer Start ******************************\n\n");
		System.out.println(new Gson().toJson(output));
		System.out.println("\n\n ****************************** Answer End ******************************");
	}
	
	@Test
	public void deleteCurrency() {
		CurrencyBean currencyBeanInput = new CurrencyBean();
		currencyBeanInput.setCode("TWD");
		CurrencyBean output = coinController.deleteCurrency(currencyBeanInput);
		System.out.println("****************************** Answer Start ******************************\n\n");
		System.out.println(new Gson().toJson(output));
		System.out.println("\n\n ****************************** Answer End ******************************");
	}
	
	
	@Test
	public void getConCoinDeskApi() {
		CurrentPriceView currentPriceView = coinController.getCoinDeskApi();
		System.out.println("****************************** Answer Start ******************************\n\n");
		System.out.println(new Gson().toJson(currentPriceView));
		System.out.println("\n\n ****************************** Answer End ******************************");
	}
	
	
	@Test
	public void getTransCoinDeskApi() {
		TransCurrentPriceView transCurrentPriceView = coinController.getTransCoinDeskApi();
		System.out.println("****************************** Answer Start ******************************\n\n");
		System.out.println(new Gson().toJson(transCurrentPriceView));
		System.out.println("\n\n ****************************** Answer End ******************************");
	}
	
	
}
