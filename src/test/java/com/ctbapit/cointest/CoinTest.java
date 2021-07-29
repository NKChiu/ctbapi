package com.ctbapit.cointest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.DEFAULT)
public class CoinTest {
	
	@Autowired
	private CoinController coinController;
	
	/**
	 * @description 測試呼叫查詢幣別對應表資料 API
	 */
	@Test
	public void test1() {
		CurrencyAllView currencyAllView = coinController.getAllCurrency();
		System.out.println("****************************** Answer Start ******************************");
		System.out.println("\t\t\t 測試呼叫查詢幣別對應表資料 API \n\n");
		System.out.println(new Gson().toJson(currencyAllView));
		System.out.println("\n\n ****************************** Answer End ******************************");
	}
	
	/**
	 * @description 測試呼叫新增幣別對應表資料 API
	 */
	@Test
	public void test2() {
		CurrencyBean currencyBeanInput = new CurrencyBean();
		currencyBeanInput.setCode("TWD");
		currencyBeanInput.setCodeChn("台幣");
		currencyBeanInput.setUpdateUser("SYSTEM");
		CurrencyBean output = coinController.addCurrency(currencyBeanInput);
		System.out.println("****************************** Answer Start ******************************");
		System.out.println("\t\t\t 測試呼叫新增幣別對應表資料 API \n\n");
		System.out.println(new Gson().toJson(output));
		System.out.println("\n\n ****************************** Answer End ******************************");
	}
	
	/**
	 * @description 測試呼叫更新幣別對應表資料 API
	 */
	@Test
	public void test3() {
		CurrencyBean currencyBeanInput = new CurrencyBean();
		currencyBeanInput.setCode("TWD");
		currencyBeanInput.setCodeChn("台W幣");
		currencyBeanInput.setUpdateUser("SYSTEM2");
		CurrencyBean output = coinController.updateCurrency(currencyBeanInput);
		System.out.println("****************************** Answer Start ******************************");
		System.out.println("\t\t\t 測試呼叫更新幣別對應表資料 API \n\n");
		System.out.println(new Gson().toJson(output));
		System.out.println("\n\n ****************************** Answer End ******************************");
	}
	
	/**
	 * @description 測試呼叫 coindesk API
	 */
	@Test
	public void test4() {
		CurrencyBean currencyBeanInput = new CurrencyBean();
		currencyBeanInput.setCode("TWD");
		CurrencyBean output = coinController.deleteCurrency(currencyBeanInput);
		System.out.println("****************************** Answer Start ******************************");
		System.out.println("\t\t\t 測試呼叫刪除幣別對應表資料 API \n\n");
		System.out.println(new Gson().toJson(output));
		System.out.println("\n\n ****************************** Answer End ******************************");
	}
	
	/**
	 * @description 測試呼叫 coindesk API
	 */
	@Test
	public void test5() {
		CurrentPriceView currentPriceView = coinController.getCoinDeskApi();
		System.out.println("****************************** Answer Start ******************************");
		System.out.println("\t\t\t 測試呼叫 coindesk API \n\n");
		System.out.println(new Gson().toJson(currentPriceView));
		System.out.println("\n\n ****************************** Answer End ******************************");
	}
	
	/**
	 * @description 測試呼叫資料轉換  API
	 */
	@Test
	public void test6() {
		TransCurrentPriceView transCurrentPriceView = coinController.getTransCoinDeskApi();
		System.out.println("****************************** Answer Start ******************************");
		System.out.println("\t\t\t 測試呼叫資料轉換  API \n\n");
		System.out.println(new Gson().toJson(transCurrentPriceView));
		System.out.println("\n\n ****************************** Answer End ******************************");
	}
	
	
}
