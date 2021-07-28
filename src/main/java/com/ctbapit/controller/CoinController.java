package com.ctbapit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctbapit.entity.CurrencyEntity;
import com.ctbapit.model.bean.CurrencyBean;
import com.ctbapit.model.view.BpiView;
import com.ctbapit.model.view.CurrencyAllView;
import com.ctbapit.model.view.CurrentPriceTimeView;
import com.ctbapit.model.view.CurrentPriceView;
import com.ctbapit.model.view.TransCurrentPriceView;
import com.ctbapit.model.vo.BpiVo;
import com.ctbapit.model.vo.CurrentPriceTimeVo;
import com.ctbapit.model.vo.CurrentPriceVo;
import com.ctbapit.service.ICoinService;
import com.ctbapit.util.DateUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
public class CoinController {
	
	private static Logger logger = LoggerFactory.getLogger(CoinController.class);
	
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
			currencyAllView.setReturnMessage("幣別對應資料為空");
		}
		return currencyAllView;
	}
	
	//2
	@PostMapping(path="/addCurrency")
	@ResponseBody
	public CurrencyBean addCurrency(@RequestBody CurrencyBean currencyBeanInput) {
		CurrencyBean ouptut = new CurrencyBean();
		
		CurrencyBean currencyBean = coinService.addCurrency(currencyBeanInput);
		if(currencyBean.isSuccess()) {
			ouptut.setSuccess(true);
		}else {
			ouptut.setSuccess(false);
			ouptut.setReturnMessage(currencyBean.getReturnMessage());
		}
		
		return ouptut;
	}
	
	//3
	
	//4
	
	
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
			currentPriceView.setReturnMessage(currentPriceVo.getReturnMessage());
		}
		 
		 return currentPriceView;
	}
	
	//6
	@GetMapping(path="/getTransCoinDeskApi")
	@ResponseBody
	public TransCurrentPriceView getTransCoinDeskApi() {
		TransCurrentPriceView transCurrentPriceView = new TransCurrentPriceView();
		boolean goNext = true;
		String errMsg = "";
		
		logger.info("取得幣別對應資料");
		List<CurrencyBean> currencyBeanList = coinService.getAllCurrency();
		if(CollectionUtils.isEmpty(currencyBeanList)) {
			goNext = false;
			errMsg = "幣別對應資料為空";
			logger.info(errMsg);
		}
		
		String updateTime = null;
		Map<String, BpiView> currencyInfo = new HashMap<>();
		
		if(goNext) {
			logger.info("call coindesk api currentprice");
			CurrentPriceVo currentPriceVo = coinService.getCoinDeskApi();
			if(currentPriceVo.isSuccess()) {
				// 整理 updateTime 
		        updateTime = this.arrangeTransCurrentPriceViewUpdateTime(currentPriceVo);
		        // 整理 幣別資訊
		        currencyInfo = this.arrangeTransCurrentPriceViewCurrencyInfo(currentPriceVo, currencyBeanList);
			}else {
				goNext = false;
				errMsg = currentPriceVo.getReturnMessage();
			}
		}
		
		// 整理 output
		if(goNext) {
			transCurrentPriceView.setUpdateTime(updateTime);
			transCurrentPriceView.setCurrencyInfo(currencyInfo);
			transCurrentPriceView.setSuccess(true);
		}else {
			transCurrentPriceView.setSuccess(false);
			transCurrentPriceView.setReturnMessage(errMsg);
		}
		
		
		return transCurrentPriceView;
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
	
	
	/**
	 * @descriptino arrange getTransCoinDeskApi UpdateTime output
	 */
	private String arrangeTransCurrentPriceViewUpdateTime(CurrentPriceVo currentPriceVo) {
		String updateTime = null;
		CurrentPriceTimeVo currentPriceTimeVo = currentPriceVo.getTime();
		currentPriceTimeVo.getUpdatedISO();
        Date dateISO = DateUtil.parse(currentPriceTimeVo.getUpdatedISO(), DateUtil.FORMAT_TIME_ISO);
        updateTime = DateUtil.format(dateISO, DateUtil.FORMAT_TIME_HRS);
        return updateTime;
	}
	
	/**
	 * @descriptino arrange getTransCoinDeskApi CurrencyInfo output
	 */
	private Map<String, BpiView> arrangeTransCurrentPriceViewCurrencyInfo(CurrentPriceVo currentPriceVo, List<CurrencyBean> currencyBeanList){
		Map<String, BpiView> currencyInfo = new HashMap<>();
		
		Map<String, BpiVo> bpiVoMap = currentPriceVo.getBpi();
		for(Entry<String, BpiVo> key : bpiVoMap.entrySet()) {
			BpiView bpiView = new BpiView();
			BpiVo bpiVo = key.getValue();
			bpiView.setCode(bpiVo.getCode());
			
			CurrencyBean currencyBean = currencyBeanList.stream().filter(b -> b.getCode().equals(bpiVo.getCode())).findAny().orElse(null);
			if(currencyBean!= null) {
				bpiView.setCodeChn(currencyBean.getCodeChn());
			}
			
			bpiView.setRate(bpiVo.getRate());
			bpiView.setSymbol(StringEscapeUtils.unescapeHtml4(bpiVo.getSymbol()));
			currencyInfo.put(key.getKey(), bpiView);
		}
		
		return currencyInfo;
		
	}
}
