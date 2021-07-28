package com.ctbapit.model.view;

import java.util.Map;

import com.ctbapit.model.BaseOutput;


public class CurrentPriceView extends BaseOutput{
	
	private CurrentPriceTimeView time;
    
    private Map<String, BpiView> bpi;
    
    private String disclaimer;
    
    private String chartName;
    
    
    public CurrentPriceView() {}


	public CurrentPriceTimeView getTime() {
		return time;
	}


	public void setTime(CurrentPriceTimeView time) {
		this.time = time;
	}


	public Map<String, BpiView> getBpi() {
		return bpi;
	}


	public void setBpi(Map<String, BpiView> bpi) {
		this.bpi = bpi;
	}


	public String getDisclaimer() {
		return disclaimer;
	}


	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}


	public String getChartName() {
		return chartName;
	}


	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
    
    
	
}
