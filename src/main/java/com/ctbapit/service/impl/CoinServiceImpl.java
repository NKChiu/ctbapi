package com.ctbapit.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	
	@Override
	public CurrentPriceVo getCoinDeskApi() {
		
		CurrentPriceVo currentPrice = new CurrentPriceVo();
		
		try {
			ResponseEntity<String> response = restTemplate.getForEntity("https://api.coindesk.com/v1/bpi/currentprice.json",String.class);
			ObjectMapper mapper = new ObjectMapper();
			
			JsonNode root = mapper.readTree(response.getBody());
			
			JsonNode timeJson = root.get("time");
			JsonNode bpiJson = root.path("bpi");
			JsonNode disclaimerJson = root.get("disclaimer");
			JsonNode chartNameJson = root.get("chartName");
			
			CurrentPriceTimeVo currentPriceTimeVo =
	        			new CurrentPriceTimeVo(
	        					timeJson.get("updated").asText(),
	        					timeJson.get("updatedISO").asText(),
	        					timeJson.get("updateduk").asText() );
			
			currentPrice.setTime(currentPriceTimeVo);
			 
			 
			Map<String, BpiVo> bpiMap = new HashMap<String, BpiVo>();
			
			bpiJson.forEach(jsonNode ->{
				BpiVo bpiVo = new BpiVo(
						jsonNode.get("code").asText(), 
						jsonNode.get("symbol").asText(), 
						jsonNode.get("rate").asText());
				bpiMap.put(jsonNode.get("code").asText(), bpiVo);
				
			});
			currentPrice.setBpi(bpiMap);
			
			
			currentPrice.setDisclaimer(disclaimerJson.asText());
			currentPrice.setChartName(chartNameJson.asText());
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return currentPrice;
	}
	
	
	
	

}
