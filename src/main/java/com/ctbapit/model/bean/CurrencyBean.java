package com.ctbapit.model.bean;

import com.ctbapit.model.BaseOutput;

public class CurrencyBean extends BaseOutput{
	
	private String code;
	private String codeChn;
	
	public CurrencyBean() {}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeChn() {
		return codeChn;
	}

	public void setCodeChn(String codeChn) {
		this.codeChn = codeChn;
	}
	
}
