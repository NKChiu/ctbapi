package com.ctbapit.model.vo;

public class CurrentPriceTimeVo {
	
	private String updated;
	private String updatedISO;
	private String updateduk;
	
	public CurrentPriceTimeVo() {}
	
	public CurrentPriceTimeVo(String updated, String updatedISO, String updateduk) {
		this.updated = updated;
		this.updatedISO = updatedISO;
		this.updateduk = updateduk;
	}

	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getUpdatedISO() {
		return updatedISO;
	}
	public void setUpdatedISO(String updatedISO) {
		this.updatedISO = updatedISO;
	}
	public String getUpdateduk() {
		return updateduk;
	}
	public void setUpdateduk(String updateduk) {
		this.updateduk = updateduk;
	}
	
	
}
