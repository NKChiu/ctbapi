package com.ctbapit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.ctbapit.dao.ICoinDao;
import com.ctbapit.entity.CurrencyEntity;
import com.ctbapit.model.bean.CurrencyBean;
import com.ctbapit.model.config.CurrentPriceConfig;
import com.ctbapit.model.config.CurrentPriceConfig.CurrentPriceBpiColumn;
import com.ctbapit.model.config.CurrentPriceConfig.CurrentPriceColumn;
import com.ctbapit.model.config.CurrentPriceConfig.CurrentPriceTimeColumn;
import com.ctbapit.model.vo.BpiVo;
import com.ctbapit.model.vo.CurrentPriceTimeVo;
import com.ctbapit.model.vo.CurrentPriceVo;
import com.ctbapit.service.ICoinService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Service
public class CoinServiceImpl implements ICoinService{
	
	private static Logger logger = LoggerFactory.getLogger(CoinServiceImpl.class);
	
	@Value("${coindeskUrl}")
	private String COINDESK_URL; // call coindesk url
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ICoinDao coinDao;
	

	/**
	 * @see com.ctbapit.service.ICoinService#getAllCurrency()
	 **/
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


	/**
	 * @see com.ctbapit.service.ICoinService#addCurrency(com.ctbapit.model.bean.CurrencyBean)
	 **/
	@Override
	public CurrencyBean addCurrency(CurrencyBean currencyBeanInput) {
		CurrencyBean output = new CurrencyBean();
		boolean goNext = true;
		String errMsg = null;
		
		try {
			logger.info("1. 查詢幣別對應資料是否重複");
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
				logger.info("2. 新增幣別對應資料");
				CurrencyEntity currencyEntity = new CurrencyEntity();
				currencyEntity.setCode(currencyBeanInput.getCode());
				currencyEntity.setCodeChn(currencyBeanInput.getCodeChn());
				currencyEntity.setUpdateUser(currencyBeanInput.getUpdateUser());
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
		
		logger.info("3. 整理輸出");
		if(goNext) {
			output.setSuccess(true);
			output.setCode(code);
			output.setCodeChn(codeChn);
		}else {
			output.setSuccess(false);
			output.setReturnMessage(errMsg);
		}
		
		return output;
	}
	
	

	/**
	 * @see com.ctbapit.service.ICoinService#updateCurrency(com.ctbapit.model.bean.CurrencyBean)
	 **/
	@Override
	public CurrencyBean updateCurrency(CurrencyBean currencyBeanInput) {
		CurrencyBean output = new CurrencyBean();
		boolean goNext = true;
		String errMsg = null;
		
		CurrencyEntity currencyEntity = null;
		try {
			logger.info("1. 查詢更新之幣別對應資料是否存在");
			currencyEntity = coinDao.findByCode(currencyBeanInput.getCode());
			if(currencyEntity == null) {
				goNext = false;
				errMsg = "更新之幣別對應資料不存在 : " + currencyBeanInput.getCode();
			}
		}catch(Exception e) {
			goNext = false;
			errMsg = "查詢更新之幣別對應資料是否存在失敗: " + e.getMessage();
			logger.error(errMsg);
		}
		
		String code = "";
		String codeChn = "";
		
		if(goNext) {
			try {
				logger.info("2. 更新幣別對應資料");
				currencyEntity.setCodeChn(currencyBeanInput.getCodeChn());
				currencyEntity.setUpdateUser(currencyBeanInput.getUpdateUser());
				currencyEntity.setUpdateDate(new Date());
				CurrencyEntity addCurrency = coinDao.save(currencyEntity);
				if(addCurrency != null) {
					code = addCurrency.getCode();
					codeChn = addCurrency.getCodeChn();
				}
			}catch(Exception e) {
				goNext = false;
				errMsg = "更新幣別對應資料失敗: " + e.getMessage();
				logger.error(errMsg);
			}
		}
		
		logger.info("3. 整理輸出");
		if(goNext) {
			output.setSuccess(true);
			output.setCode(code);
			output.setCodeChn(codeChn);
		}else {
			output.setSuccess(false);
			output.setReturnMessage(errMsg);
		}
		
		return output;
	}
	
	
	/**
	 * @see com.ctbapit.service.ICoinService#deleteCurrency(com.ctbapit.model.bean.CurrencyBean)
	 **/
	@Override
	public CurrencyBean deleteCurrency(CurrencyBean currencyBeanInput) {

		CurrencyBean output = new CurrencyBean();
		boolean goNext = true;
		String errMsg = null;
		
		CurrencyEntity currencyEntity = null;
		try {
			logger.info("1. 查詢刪除之幣別對應資料是否存在");
			currencyEntity = coinDao.findByCode(currencyBeanInput.getCode());
			if(currencyEntity == null) {
				goNext = false;
				errMsg = "刪除之幣別對應資料不存在 : " + currencyBeanInput.getCode();
			}
		}catch(Exception e) {
			goNext = false;
			errMsg = "查詢刪除之幣別對應資料是否存在失敗: " + e.getMessage();
			logger.error(errMsg);
		}
		
		if(goNext) {
			try {
				logger.info("2. 刪除幣別對應資料: " + currencyEntity.getCode());
				coinDao.delete(currencyEntity);
			}catch(Exception e) {
				goNext = false;
				errMsg = "更新幣別對應資料失敗: " + e.getMessage();
				logger.error(errMsg);
			}
		}
		
		logger.info("3. 整理輸出");
		if(goNext) {
			output.setSuccess(true);
		}else {
			output.setSuccess(false);
			output.setReturnMessage(errMsg);
		}
		
		return output;
	}

	
	/**
	 * @see com.ctbapit.service.ICoinService#getCoinDeskApi()
	 **/
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
			logger.info("1. call coindesk api currentprice");
			
			ResponseEntity<String> response = restTemplate.getForEntity(COINDESK_URL,String.class);
			ObjectMapper mapper = new ObjectMapper();
			
			JsonNode root = mapper.readTree(response.getBody());
			
			JsonNode timeJson = root.get(CurrentPriceColumn.TIME.getCode());
			JsonNode bpiJson = root.path(CurrentPriceColumn.BPI.getCode());
			JsonNode disclaimerJson = root.get(CurrentPriceColumn.DISCLAIMER.getCode());
			JsonNode chartNameJson = root.get(CurrentPriceColumn.CHART_NAME.getCode());
			
			currentPriceTimeVo = new CurrentPriceTimeVo(
											timeJson.get(CurrentPriceTimeColumn.UPDATED.getCode()) != null ? timeJson.get(CurrentPriceTimeColumn.UPDATED.getCode()).asText() : "",
				        					timeJson.get(CurrentPriceTimeColumn.UPDATED_ISO.getCode()) != null ? timeJson.get(CurrentPriceTimeColumn.UPDATED_ISO.getCode()).asText() : "",
				        					timeJson.get(CurrentPriceTimeColumn.UPDATED_UK.getCode()) != null ? timeJson.get(CurrentPriceTimeColumn.UPDATED_UK.getCode()).asText() : "" );
			
			bpiJson.forEach(jsonNode ->{
				BpiVo bpiVo = new BpiVo(
						jsonNode.get(CurrentPriceBpiColumn.CODE.getCode()) != null ? jsonNode.get(CurrentPriceBpiColumn.CODE.getCode()).asText() : "", 
						jsonNode.get(CurrentPriceBpiColumn.SYMBOL.getCode()) != null ? jsonNode.get(CurrentPriceBpiColumn.SYMBOL.getCode()).asText() : "", 
						jsonNode.get(CurrentPriceBpiColumn.RATE.getCode()) != null ? jsonNode.get(CurrentPriceBpiColumn.RATE.getCode()).asText() : "",
						jsonNode.get(CurrentPriceBpiColumn.DESC.getCode()) != null ? jsonNode.get(CurrentPriceBpiColumn.DESC.getCode()).asText() : "",
						jsonNode.get(CurrentPriceBpiColumn.RATE_FLOAT.getCode()) != null ? jsonNode.get(CurrentPriceBpiColumn.RATE_FLOAT.getCode()).asDouble() : 0 );
				bpiMap.put(jsonNode.get(CurrentPriceBpiColumn.CODE.getCode()) != null ? jsonNode.get(CurrentPriceBpiColumn.CODE.getCode()).asText() : "code", bpiVo);
				
			});
			
			
			disclaimer = disclaimerJson.asText();
			chartName = chartNameJson.asText();
			
		} catch (Exception e) {
			goNext = false;
			errMsg = "call coindesk api currentprice error: " + e.getMessage();
			logger.error(errMsg);
		}
		
		
		logger.info("2. 整理輸出");
		if(goNext) {
			currentPrice.setSuccess(true);
			currentPrice.setTime(currentPriceTimeVo);
			currentPrice.setBpi(bpiMap);
			currentPrice.setDisclaimer(disclaimer);
			currentPrice.setChartName(chartName);
		}else {
			currentPrice.setSuccess(false);
			currentPrice.setReturnMessage(errMsg);
		}
		
		return currentPrice;
	}

	
}
