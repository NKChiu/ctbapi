package com.ctbapit.model.view;

import java.util.List;

import com.ctbapit.model.BaseOutput;
import com.ctbapit.model.bean.CurrencyBean;

public class CurrencyAllView extends BaseOutput{
	
	private List<CurrencyView> currencyList;

	public List<CurrencyView> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<CurrencyView> currencyList) {
		this.currencyList = currencyList;
	}

}
