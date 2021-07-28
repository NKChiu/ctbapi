package com.ctbapit.model.vo;

import java.util.Map;

public class CurrentPriceVo {
	
    private CurrentPriceTimeVo time;
    
    private Map<String, BpiVo> bpi;
    
    private String disclaimer;
    
    private String chartName;
    
    
    public CurrentPriceVo() {}
    
    
	public CurrentPriceTimeVo getTime() {
		return time;
	}

	public void setTime(CurrentPriceTimeVo time) {
		this.time = time;
	}

	public Map<String, BpiVo> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, BpiVo> bpi) {
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
