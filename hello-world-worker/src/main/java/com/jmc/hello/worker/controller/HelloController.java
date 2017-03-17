package com.jmc.hello.worker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jmc.hello.dao.GreetingDao;
import com.jmc.hello.model.Greeting;

@RestController
public class HelloController {
    
    @Autowired
    private GreetingDao greetingDao;
  
    @RequestMapping(value="/greeting", method = RequestMethod.POST)  
    public ResponseEntity<Integer> createGreeting(@RequestBody Greeting payload) {
        int ct = greetingDao.createGreeting(payload);
        return new ResponseEntity<Integer>((ct == 0) ? HttpStatus.CONFLICT : HttpStatus.OK);
    }
    
    
}