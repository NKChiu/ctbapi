package com.ctbapit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.ctbapit.dao.ICoinDao;
import com.ctbapit.entity.CurrencyEntity;
import com.ctbapit.model.bean.CurrencyBean;
import com.ctbapit.model.vo.BpiVo;
import com.ctbapit.model.vo.CurrentPriceTimeVo;
import com.ctbapit.model.vo.CurrentPriceVo;
import com.ctbapit.service.ICoinService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Service
public class CoinServiceImpl implements ICoinService{
	
	private static Logger logger = LoggerFactory.getLogger(CoinServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ICoinDao coinDao;
	

	@Override
	public List<CurrencyBean> getAllCurrency() {
		List<CurrencyBean> currencyBeanList = new ArrayList<>();
		
		try {
			logger.info("查詢幣別對應資料");
			 List<CurrencyEntity> currencyEntityList = (List<CurrencyEntity>) coinDao.findAll();
			 if(!CollectionUtils.isEmpty(currencyEntityList)) {
				 for(CurrencyEntity currencyEntity : currencyEntityList) {
					 CurrencyBean currencyBean = new CurrencyBean();
					 currencyBean.setCode(currencyEntity.getCode());
					 currencyBean.setCodeChn(currencyEntity.getCodeChn());
					 currencyBeanList.add(currencyBean);
				 }
			 }
		}catch(Exception e) {
			logger.error("查詢幣別對應資料失敗: " + e.getMessage());
		}
		return currencyBeanList;
	}


	@Override
	public CurrencyBean addCurrency(CurrencyBean currencyBeanInput) {
		CurrencyBean output = new CurrencyBean();
		boolean goNext = true;
		String errMsg = null;
		
		try {
			logger.info("查詢幣別對應資料是否重複");
			CurrencyEntity currencyEntity = coinDao.findByCode(currencyBeanInput.getCode());
			if(currencyEntity != null) {
				goNext = false;
				errMsg = "幣別對應資料重複 : " + currencyBeanInput.getCode();
			}
		}catch(Exception e) {
			goNext = false;
			errMsg = "查詢幣別對應資料是否重複失敗: " + e.getMessage();
			logger.error(errMsg);
		}
		
		String code = "";
		String codeChn = "";
		
		if(goNext) {
			try {
				logger.info("新增幣別對應資料");
				CurrencyEntity currencyEntity = new CurrencyEntity();
				currencyEntity.setCode(currencyBeanInput.getCode());
				currencyEntity.setCodeChn(currencyBeanInput.getCodeChn());
				currencyEntity.setUpdateUser("SYSTEM");
				currencyEntity.setUpdateDate(new Date());
				CurrencyEntity saveEntity = coinDao.save(currencyEntity);
				if(saveEntity != null) {
					code = saveEntity.getCode();
					codeChn = saveEntity.getCodeChn();
				}
			}catch(Exception e) {
				goNext = false;
				errMsg = "新增幣別對應資料失敗: " + e.getMessage();
				logger.error(errMsg);
			}
		}
		
		if(goNext) {
			output.setSuccess(true);
			output.setCode(code);
			output.setCodeChn(codeChn);
		}else {
			output.setSuccess(false);
			output.setRetunrMessage(errMsg);
		}
		
		return output;
	}

	
	
	@Override
	public CurrentPriceVo getCoinDeskApi() {
		
		CurrentPriceVo currentPrice = new CurrentPriceVo();
		boolean goNext = true;
		String errMsg = "";
		
		CurrentPriceTimeVo currentPriceTimeVo = null;
		Map<String, BpiVo> bpiMap = new HashMap<>();
		String disclaimer = null;
		String chartName = null;
		
		try {
			logger.info("call coindesk api currentprice");
			
			ResponseEntity<String> response = restTemplate.getForEntity("https://api.coindesk.com/v1/bpi/currentprice.json",String.class);
			ObjectMapper mapper = new ObjectMapper();
			
			JsonNode root = mapper.readTree(response.getBody());
			
			JsonNode timeJson = root.get("time");
			JsonNode bpiJson = root.path("bpi");
			JsonNode disclaimerJson = root.get("disclaimer");
			JsonNode chartNameJson = root.get("chartName");
			
			currentPriceTimeVo = new CurrentPriceTimeVo(
				        					timeJson.get("updated").asText(),
				        					timeJson.get("updatedISO").asText(),
				        					timeJson.get("updateduk").asText() );
			
			bpiJson.forEach(jsonNode ->{
				BpiVo bpiVo = new BpiVo(
						jsonNode.get("code").asText(), 
						jsonNode.get("symbol").asText(), 
						jsonNode.get("rate").asText(),
						jsonNode.get("description").asText(),
						jsonNode.get("rate_float").asDouble());
				bpiMap.put(jsonNode.get("code").asText(), bpiVo);
				
			});
			
			
			disclaimer = disclaimerJson.asText();
			chartName = chartNameJson.asText();
			
		} catch (Exception e) {
			goNext = false;
			errMsg = "call coindesk api currentprice error: " + e.getMessage();
			logger.error(errMsg);
		}
		
		// arrange data
		if(goNext) {
			currentPrice.setSuccess(true);
			currentPrice.setTime(currentPriceTimeVo);
			currentPrice.setBpi(bpiMap);
			currentPrice.setDisclaimer(disclaimer);
			currentPrice.setChartName(chartName);
		}else {
			currentPrice.setSuccess(false);
			currentPrice.setRetunrMessage(errMsg);
		}
		
		return currentPrice;
	}








	
	
	
	
	

}
