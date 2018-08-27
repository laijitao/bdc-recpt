package com.hp.cmcc.bboss.bdc.common.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hp.cmcc.bboss.bdc.common.FieldHandle;
import com.hp.cmcc.bboss.bdc.config.BdcBeanFactory;
import com.hp.cmcc.bboss.bdc.dao.BaseDao;
import com.hp.cmcc.bboss.bdc.dao.impl.BaseDaoImpl;
import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.bdc.utils.Tools;

public class FieldHandleImpl implements FieldHandle {
	private static BaseDao baseDao = new BaseDaoImpl();
	private static JdbcTemplate jdbc = BdcBeanFactory.getBean("bdcCompareTemplate", JdbcTemplate.class);
	

	@Override
	public String getValue(String value,BbdcTypeCdr cdr) {
		if(cdr.getFormerIdx() == -1) {
			switch(cdr.getFieldValue()) {
				case"DATABASE":
					value = getValueWithDatabase(cdr);
					break;
				case"IGNORE":
					value = getSqlStr(value,cdr);
					break;
				case"REQUEST":
					value = setValue(value,cdr);
					break;
				default :
					break;
			}
		}
		return Tools.IsBlank(value) ? null : value;
	}

	@Override
	public String getValueFromDatabase(String field, BbdcTypeCdr cdr) {
		String sql = cdr.getDataFiller();
		String result = baseDao.execuSql(sql, jdbc)+"";
		return result;
	}

	@Override
	public String getSqlStr(String value, BbdcTypeCdr cdr) {
		switch(cdr.getDataType()) {
			case"STRING":
				break;
			case"FIXED":
				value = "'"+cdr.getDataFiller()+"'";
				break;
			default :
				break;
		}
		return Tools.IsBlank(value) ? null : value;
	}

	@Override
	public String getValueWithDatabase(BbdcTypeCdr cdr) {
		switch(cdr.getDataType()) {
			case"DATE":
				return cdr.getDataFiller();
			case"FIXED":
				return "'"+cdr.getDataFiller()+"'";
			case"SQL":
				return "'"+getValueFromDatabase("",cdr)+"'";
			default:
				return null;
		}
	}

	@Override
	public String setValue(String value, BbdcTypeCdr cdr) {
		switch(cdr.getDataType()) {
			case"STRING":
				return "'"+value+"'";
			case "NUMBER":
				return value;
		}
		return value;
	}

}
