package com.jdsolutions.restful_web.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SomeBean {
	
	private int id;
	private String name;
	
	@JsonIgnore
	private String passwd;
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
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public SomeBean(int id, String name, String passwd) {
		super();
		this.id = id;
		this.name = name;
		this.passwd = passwd;
	}
	

}
