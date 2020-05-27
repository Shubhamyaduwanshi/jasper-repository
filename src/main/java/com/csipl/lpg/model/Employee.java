package com.csipl.lpg.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String oraganization;
	private String designation;
	private int salary;
	private String reportTo;
	private String Branch;
	
	
	
	public Employee() {
		
	}
	
	
	public Employee(int id, String name, String oraganization, String designation, int salary, String reportTo,
			String branch) {
		this.id = id;
		this.name = name;
		this.oraganization = oraganization;
		this.designation = designation;
		this.salary = salary;
		this.reportTo = reportTo;
		Branch = branch;
	}



	public String getReportTo() {
		return reportTo;
	}



	public void setReportTo(String reportTo) {
		this.reportTo = reportTo;
	}



	public String getBranch() {
		return Branch;
	}



	public void setBranch(String branch) {
		Branch = branch;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOraganization() {
		return oraganization;
	}
	public void setOraganization(String oraganization) {
		this.oraganization = oraganization;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
    
}
