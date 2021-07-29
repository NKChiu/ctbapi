package com.ctbapit.model.config;

public class CurrentPriceConfig {
	
	public enum CurrentPriceColumn{
		TIME("time"), BPI("bpi"), DISCLAIMER("disclaimer"), CHART_NAME("chartName");
		
		private String code;
		
		private CurrentPriceColumn(String code) {
			this.code = code;
		}
		
		public String getCode() {
            return code;
        }
	}
	
	public enum CurrentPriceTimeColumn{
		UPDATED("updated"), UPDATED_ISO("updatedISO"), UPDATED_UK("updateduk");
		
		private String code;
		
		private CurrentPriceTimeColumn(String code) {
			this.code = code;
		}
		
		public String getCode() {
            return code;
        }
	}
	
	
	public enum CurrentPriceBpiColumn{
		CODE("code"), SYMBOL("symbol"), RATE("rate"),
		DESC("description"), RATE_FLOAT("rate_float");
		
		private String code;
		
		private CurrentPriceBpiColumn(String code) {
			this.code = code;
		}
		
		public String getCode() {
            return code;
        }
	}
}
