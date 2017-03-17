package com.jmc.hello.model;

public class Greeting extends AbstractModelObject {

    private int id;
    private String value;
    
	public Greeting() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }	
	
	
}