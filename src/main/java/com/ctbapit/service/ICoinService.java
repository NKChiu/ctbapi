package com.ctbapit.service;

import java.util.List;

import com.ctbapit.model.bean.CurrencyBean;
import com.ctbapit.model.vo.CurrentPriceVo;

public interface ICoinService {
	
	/**
	 * @description API_1 : 查詢幣別對應表
	 */
	public List<CurrencyBean> getAllCurrency();
	
	/**
	 * @description API_2 : 新增幣別對應表資料
	 */
	public CurrencyBean addCurrency(CurrencyBean currencyBeanInput);
	
	/**
	 * @description API_3 : 更新幣別對應表資料
	 */
	public CurrencyBean updateCurrency(CurrencyBean currencyBeanInput);
	
	/**
	 * @description API_4 : 刪除幣別對應表資料
	 */
	public CurrencyBean deleteCurrency(CurrencyBean currencyBeanInput);
	
	/**
	 * @description API_5 : call coindesk API (原本的 API)
	 */
	public CurrentPriceVo getCoinDeskApi();
	
}
