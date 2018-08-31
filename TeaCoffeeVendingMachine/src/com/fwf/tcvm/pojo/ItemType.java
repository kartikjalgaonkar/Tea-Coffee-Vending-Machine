package com.fwf.tcvm.pojo;

public enum ItemType {
	
	
	TEA("tea"),
	BLACK_TEA("black tea"),
	COFFE("coffee"),
	BLACK_COFFEE("black coffee");
	
	private String type;
	
	private ItemType(String type){
		this.type=type;
	}
	
	public String getType(){
		return this.type;
	}
}
