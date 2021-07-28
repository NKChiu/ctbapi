package com.ctbapit.model;

import java.util.Map;

public class Currency {
	
	private String updateTime;
	
	private Map<String, CurrencyInfo> currencyInfo;

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Map<String, CurrencyInfo> getCurrencyInfo() {
		return currencyInfo;
	}

	public void setCurrencyInfo(Map<String, CurrencyInfo> currencyInfo) {
		this.currencyInfo = currencyInfo;
	}
	
	
}
