package com.hp.cmcc.bboss.bdc.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hp.cmcc.bboss.bdc.exception.CheckException;

public interface BaseDao {

	<T> T  execuSql(String sql,JdbcTemplate jdbc);
	
	String getOperSerialNbrBy(JdbcTemplate jdbc,String sql,String orderId) throws CheckException;
}
