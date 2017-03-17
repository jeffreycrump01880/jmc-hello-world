package com.jmc.hello.model;

import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

abstract class AbstractModelObject {

    public String toString() {
        
        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            mapper.writeValue(sw,this);
            return sw.toString();
        }
        catch (Exception e) {
            return null;
        }
    }
}
