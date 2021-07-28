package com.ctbapit.cointest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ctbapit.CtbapiApplication;
import com.ctbapit.config.UnitTestConfig;
import com.ctbapit.controller.CoinController;
import com.ctbapit.model.view.CurrentPriceView;
import com.ctbapit.model.vo.CurrentPriceVo;
import com.ctbapit.service.ICoinService;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CtbapiApplication.class, UnitTestConfig.class})
public class CoinTest {
	
	@Autowired
	private CoinController coinController;
	
	@Test
	public void getConCoinDeskApi() {
		CurrentPriceView currentPriceView = coinController.getCoinDeskApi();
		System.out.println(new Gson().toJson(currentPriceView));
	}
	
	@Autowired
	private ICoinService coinService;
		
	@Test
	public void getCoinDeskApi() {
		CurrentPriceVo coinDeskApi = coinService.getCoinDeskApi();
		System.out.println(new Gson().toJson(coinDeskApi));
	}
}
