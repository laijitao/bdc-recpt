package com.hp.cmcc.bboss.bdc.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hp.cmcc.bboss.bdc.dao.BaseDao;
import com.hp.cmcc.bboss.bdc.exception.CheckException;
import com.hp.cmcc.bboss.bdc.utils.Tools;

public class BaseDaoImpl implements BaseDao {
	private static Logger L = LoggerFactory.getLogger(BaseDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public <T> T execuSql(String sql, JdbcTemplate jdbc) {
		Object result = null;
		try {
			result =  jdbc.queryForObject(sql, Object.class);
		} catch (Exception e) {
			L.error("query exception:",e);
		}
		return (T) result;
	}

	@Override
	public String getOperSerialNbrBy(JdbcTemplate jdbc,String sql, String orderId) throws CheckException{
		sql = Tools.replaceAll("?", sql, orderId);
		String osn = jdbc.queryForObject(sql, String.class);
		if(Tools.IsBlank(osn)) {
			throw new CheckException("F999", "Oper_Serial_Nbr not find!");
		}else {
			return osn;
		}
	}

}
