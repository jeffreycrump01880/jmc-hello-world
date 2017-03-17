package com.jmc.hello.dao.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.jmc.hello.model.Greeting;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GreetingMapper implements RowMapper<Greeting> {

	@Override
    public Greeting mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        if(resultSet == null) {
            return null;
        }

		Greeting g = new Greeting();
		g.setValue(resultSet.getString("value"));
		g.setId(resultSet.getInt("id"));
		
		return g;
	}

}
