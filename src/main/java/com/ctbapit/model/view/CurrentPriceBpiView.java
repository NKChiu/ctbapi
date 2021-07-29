package com.ctbapit.model.view;

public class CurrentPriceBpiView {
	
	private String code;
    private String symbol;
    private String rate;
    private String description;
    private Double rate_float;
    
    public CurrentPriceBpiView() {}
    
	public CurrentPriceBpiView(String code, String symbol, String rate, String description, Double rate_float) {
		this.code = code;
		this.symbol = symbol;
		this.rate = rate;
		this.description = description;
		this.rate_float = rate_float;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRate_float() {
		return rate_float;
	}

	public void setRate_float(Double rate_float) {
		this.rate_float = rate_float;
	}
}
