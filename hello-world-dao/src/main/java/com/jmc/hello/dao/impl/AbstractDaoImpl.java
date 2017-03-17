package com.jmc.hello.dao.impl;

import java.util.Map;
import java.util.Properties;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class AbstractDaoImpl {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private Properties sqls;
    
    protected AbstractDaoImpl(NamedParameterJdbcTemplate jdbcTemplate, Properties sqls) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqls = sqls;
    }
    
    protected String getSql(String name) {
        String queryStr = sqls.getProperty(name);
        if (queryStr == null || "".equals(queryStr.trim())) {
            throw new IllegalArgumentException("Query string does not exist for: " + name);
        }
        return queryStr.trim();
    }

    protected <T> T queryForObject(String sql, Map<String, Object> args, RowMapper<T> rm) {
        try {
            return jdbcTemplate.queryForObject(sql, args, rm);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    protected int update(String sql, Map<String,Object> params)  {
        try {
            return jdbcTemplate.update(sql, params);
        }
        catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
}
