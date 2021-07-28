package com.ctbapit.model.vo;

public class BpiVo {
	
    private String code;
    private String symbol;
    private String rate;
    
    public BpiVo() {}
    
    public BpiVo(String code, String symbol, String rate) {
        this.code = code;
        this.symbol = symbol;
        this.rate = rate;
    }
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
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
