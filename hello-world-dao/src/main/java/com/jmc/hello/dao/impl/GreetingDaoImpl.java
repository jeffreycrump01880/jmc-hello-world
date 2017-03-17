package com.jmc.hello.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jmc.hello.dao.GreetingDao;
import com.jmc.hello.dao.mapper.GreetingMapper;
import com.jmc.hello.model.Greeting;

@Repository
public class GreetingDaoImpl extends AbstractDaoImpl implements GreetingDao {

    @Autowired
    public GreetingDaoImpl(@Qualifier("mysqlJdbcTemplate")  NamedParameterJdbcTemplate jdbcTemplate, 
                           @Qualifier("daoSql") Properties sqls) {
       super(jdbcTemplate, sqls);
    }
    
	public Greeting getGreetingById(int id) {
	    String sql = getSql("getGreetingById");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        return queryForObject(sql, parameters, new GreetingMapper());
	}	

	public int createGreeting(Greeting g) {
	    String sql = getSql("createGreeting");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", g.getId());
        parameters.put("value", g.getValue());
        return update(sql, parameters);
	}
}
