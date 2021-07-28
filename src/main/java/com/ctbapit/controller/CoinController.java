package com.ctbapit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctbapit.entity.CurrencyEntity;
import com.ctbapit.model.bean.CurrencyBean;
import com.ctbapit.model.view.BpiView;
import com.ctbapit.model.view.CurrencyAllView;
import com.ctbapit.model.view.CurrentPriceTimeView;
import com.ctbapit.model.view.CurrentPriceView;
import com.ctbapit.model.vo.BpiVo;
import com.ctbapit.model.vo.CurrentPriceTimeVo;
import com.ctbapit.model.vo.CurrentPriceVo;
import com.ctbapit.service.ICoinService;
import com.google.gson.Gson;

@RestController
public class CoinController {
	
	@Autowired
	private ICoinService coinService;
	
	//1
	@GetMapping(path="/getAllCurrency")
	@ResponseBody
	public CurrencyAllView getAllCurrency() {
		CurrencyAllView currencyAllView = new CurrencyAllView();
		
		List<CurrencyBean> currencyBeanList = coinService.getAllCurrency();
		if(!CollectionUtils.isEmpty(currencyBeanList)) {
			currencyAllView.setCurrencyList(currencyBeanList);
			currencyAllView.setSuccess(true);
		}else {
			currencyAllView.setRetunrMessage("幣別對應資料為空");
		}
		return currencyAllView;
	}
	
	//5
	@GetMapping(path="/getCoinDeskApi")
	@ResponseBody
	public CurrentPriceView getCoinDeskApi() {
		CurrentPriceView currentPriceView = new CurrentPriceView();
		
		CurrentPriceVo currentPriceVo = coinService.getCoinDeskApi();
		if(currentPriceVo.isSuccess()) {
			currentPriceView = arrangeCurrentPriceView(currentPriceVo);
			currentPriceView.setSuccess(true);
		}else {
			currentPriceView.setSuccess(false);
			currentPriceView.setRetunrMessage(currentPriceVo.getRetunrMessage());
		}
		 
		 return currentPriceView;
	}
	
	
	
	/**
	 * @description arrange getCoinDeskApi output
	 */
	private CurrentPriceView arrangeCurrentPriceView(CurrentPriceVo currentPriceVo) {
		
		CurrentPriceView currentPriceView = new CurrentPriceView();
		
		CurrentPriceTimeVo currentPriceTimeVo = currentPriceVo.getTime();
		CurrentPriceTimeView currentPriceTimeView = new CurrentPriceTimeView();
		currentPriceTimeView.setUpdated(currentPriceTimeVo.getUpdated());
		currentPriceTimeView.setUpdatedISO(currentPriceTimeVo.getUpdatedISO());
		currentPriceTimeView.setUpdateduk(currentPriceTimeVo.getUpdateduk());
		
		Map<String, BpiView> bpiViewMap = new HashMap<>();
		
		Map<String, BpiVo> bpiVoMap = currentPriceVo.getBpi();
		
		for(Entry<String, BpiVo> key : bpiVoMap.entrySet()) {
			BpiView bpiView = new BpiView();
			BpiVo bpiVo = key.getValue();
			bpiView.setCode(bpiVo.getCode());
			bpiView.setSymbol(bpiVo.getSymbol());
			bpiView.setRate(bpiVo.getRate());
			bpiView.setDescription(bpiVo.getDescription());
			bpiView.setRate_float(bpiVo.getRate_float());
			
			bpiViewMap.put(key.getKey(), bpiView);
		}
		
		currentPriceView.setTime(currentPriceTimeView);
		currentPriceView.setBpi(bpiViewMap);
		currentPriceView.setDisclaimer(currentPriceVo.getDisclaimer());
		currentPriceView.setChartName(currentPriceVo.getChartName());
		
		return currentPriceView;
	}
	
}
