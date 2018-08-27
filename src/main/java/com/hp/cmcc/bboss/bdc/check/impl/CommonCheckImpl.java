package com.hp.cmcc.bboss.bdc.check.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hp.cmcc.bboss.bdc.exception.CheckException;
import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.bdc.utils.Tools;

public class CommonCheckImpl implements com.hp.cmcc.bboss.bdc.check.CommonCheck {

	@Override
	public void uniqueCheck(JdbcTemplate jdbc,String field, BbdcTypeCdr cdr) throws CheckException  {
		String sql = cdr.getDataFiller().trim().replace("?", field);
		List<Map<String, Object>> list = jdbc.queryForList(sql);
		if(list.size() != 0) {
			throw new CheckException("1","违反主键唯一性");
		}
	}

	@Override
	public void fieldIFormatCheck(String field, BbdcTypeCdr cdr) throws CheckException {
		String regex = cdr.getValidateRegex().trim();
		if(Tools.IsBlank(regex)) {
			return;
		}else {
			if(!Tools.dataFormatCheck(field, regex)) {
				throw new CheckException("2","数据类型及格式与规范不符");
			}
		}
	}
	
	public void dateIFormatCheck(String field, BbdcTypeCdr cdr) throws CheckException {
		String regex = cdr.getValidateRegex().trim();
		if(!Tools.dateFormatCheck(field, regex)) {
			throw new CheckException("2","数据类型及格式与规范不符");
		}
	}

	@Override
	public void dataValueRangeCheck(String field, BbdcTypeCdr cdr) throws CheckException  {
		String data = cdr.getDataFiller().trim();
		if(Tools.IsBlank(data)) {
			return;
		}else {
			if(!Tools.isExistInArr(field,data.split(",",-1))) {
				throw new CheckException("3","数据取值无效");
			}
		}
	}

	@Override
	public void fieldLengthCheck(String field, BbdcTypeCdr cdr) throws CheckException {
		Long length = cdr.getDataLen();
		if(field.length() > length) {
			throw new CheckException("2","数据类型及格式与规范不符");
		}
	}

	@Override
	public void fieldNumCheck(String[] S, int checkNum) throws CheckException {
		if(S.length != checkNum) {
			throw new CheckException("4","记录字段数量不符合规范");
		} 
	}

}
