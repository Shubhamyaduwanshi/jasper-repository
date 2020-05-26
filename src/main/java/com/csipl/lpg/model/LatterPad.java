package com.csipl.lpg.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mylatterpad")
public class LatterPad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String tital;
	private Long mobile;
	private String address;
	private Date date;
	public LatterPad() {}
	
	public LatterPad(long id, String tital, long mobile) {
		this.id = id;
		this.tital = tital;
		this.mobile = mobile;
	}
	public LatterPad(String tital, long mobile,String address) {
		this.tital = tital;
		this.mobile = mobile;
		this.address=address;
		this.date=new Date();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTital() {
		return tital;
	}
	public void setTital(String tital) {
		this.tital = tital;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "LatterPad [id=" + id + ", tital=" + tital + ", mobile=" + mobile + ", date=" + date + "]";
	}
	
}
