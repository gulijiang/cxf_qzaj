package com.qzaj.entity;

public class Labelvalue {
	public String label;
	public String value;
	public Labelvalue(String value,String label){
		this.value=value;
		this.label=label;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
