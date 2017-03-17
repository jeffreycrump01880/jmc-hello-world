package com.jmc.hello.dao;

import com.jmc.hello.model.Greeting;

public interface GreetingDao {
   
    public Greeting getGreetingById(int id);
    public int createGreeting(Greeting g);
    
}
