package com.ctbapit.model.view;

public class TransCurrentPriceBpiView {
	
	private String code;
	private String codeChn;
    private String symbol;
    private String rate;
    
    public TransCurrentPriceBpiView() {}
    
	public TransCurrentPriceBpiView(String code, String codeChn, String symbol, String rate) {
		this.code = code;
		this.codeChn = codeChn;
		this.symbol = symbol;
		this.rate = rate;
	}

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

	public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }

}
