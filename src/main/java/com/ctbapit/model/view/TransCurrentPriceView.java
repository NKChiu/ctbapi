package com.ctbapit.model.view;

import java.util.Map;

import com.ctbapit.model.BaseOutput;

public class TransCurrentPriceView extends BaseOutput{
	
	private String updateTime;
	
	private Map<String, TransCurrentPriceBpiView> currencyInfo;

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Map<String, TransCurrentPriceBpiView> getCurrencyInfo() {
		return currencyInfo;
	}

	public void setCurrencyInfo(Map<String, TransCurrentPriceBpiView> currencyInfo) {
		this.currencyInfo = currencyInfo;
	}
	
	
	
}
