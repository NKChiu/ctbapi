package com.ctbapit.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CURRENCY_ENTITY")
public class CurrencyEntity {

	private int id;
	private String code;
	private String codeChn;
	private String updateUser;
	private Date updateDate;
	
	public CurrencyEntity() {}
	
	public CurrencyEntity(int id, String code, String codeChn, String updateUser, Date updateDate) {
		this.id = id;
		this.code = code;
		this.codeChn = codeChn;
		this.updateUser = updateUser;
		this.updateDate = updateDate;
	}
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_CURRENCY", allocationSize = 1)
    @GeneratedValue(generator = "generator", strategy = GenerationType.SEQUENCE)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "CODE", nullable = false)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name = "CODE_CHN", nullable = false)
	public String getCodeChn() {
		return codeChn;
	}
	public void setCodeChn(String codeChn) {
		this.codeChn = codeChn;
	}
	
	@Column(name = "UPDATE_USER", nullable = false)
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	@Column(name = "UPDATE_DATE", nullable = false)
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
