package com.ctbapit.model.view;

import java.util.Map;

import com.ctbapit.model.BaseOutput;

public class TransCurrentPriceView extends BaseOutput{
	
	private String updateTime;
	
	private Map<String, BpiView> currencyInfo;

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Map<String, BpiView> getCurrencyInfo() {
		return currencyInfo;
	}

	public void setCurrencyInfo(Map<String, BpiView> currencyInfo) {
		this.currencyInfo = currencyInfo;
	}
	
	
}
