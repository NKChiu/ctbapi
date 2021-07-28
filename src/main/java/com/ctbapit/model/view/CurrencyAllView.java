package com.ctbapit.model.view;

import java.util.List;

import com.ctbapit.model.BaseOutput;
import com.ctbapit.model.bean.CurrencyBean;

public class CurrencyAllView extends BaseOutput{
	
	private List<CurrencyBean> currencyList;

	public List<CurrencyBean> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<CurrencyBean> currencyList) {
		this.currencyList = currencyList;
	}
	
	
}
