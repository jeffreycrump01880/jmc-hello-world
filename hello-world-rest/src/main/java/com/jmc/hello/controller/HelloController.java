package com.jmc.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jmc.hello.dao.GreetingDao;
import com.jmc.hello.model.Greeting;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class HelloController {
    
    @Autowired
    private GreetingDao greetingDao;
    
    @RequestMapping(value="/greeting", method = RequestMethod.GET)
    @ApiOperation(value = "greeting")
    public Greeting getGreeting(@ApiParam(name="token") @RequestParam(value="token", required = false) String token,
                                @ApiParam(name="id") @RequestParam(value="id", required = false) Integer id) {
        
        if (id == null) {
            id = 1;
        }
        
        Greeting g = greetingDao.getGreetingById(id);
        String msg;
        
        if (g == null) {
            id = 0;
            msg = "Requested greeting not found";
        }
        else {
            msg = g.getValue();
        }
        
        // Append the personalization if any:
        if (token != null) {
            msg += ", " + token;
        }
        msg += "!";
            
        Greeting retval = new Greeting();
        retval.setId(id);
        retval.setValue(msg);
        return retval;

    }
  
    @RequestMapping(value="/greeting", method = RequestMethod.POST)  
    @ApiOperation(value = "greeting")
    public ResponseEntity<Integer> createGreeting(@ApiParam(name="payload") @RequestBody Greeting payload) {
        int ct = greetingDao.createGreeting(payload);
        return new ResponseEntity<Integer>((ct == 0) ? HttpStatus.CONFLICT : HttpStatus.OK);
    }
    
    
}