package com.ctbapit.service;

import java.util.List;

import com.ctbapit.entity.CurrencyEntity;
import com.ctbapit.model.bean.CurrencyBean;
import com.ctbapit.model.vo.CurrentPriceVo;

public interface ICoinService {
	//1
	public List<CurrencyBean> getAllCurrency();
	//5
	public CurrentPriceVo getCoinDeskApi();
	
}
