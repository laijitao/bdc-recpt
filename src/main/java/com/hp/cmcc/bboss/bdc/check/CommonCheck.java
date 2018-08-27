package com.hp.cmcc.bboss.bdc.check;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hp.cmcc.bboss.bdc.exception.CheckException;
import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;

public interface CommonCheck {

	void uniqueCheck(JdbcTemplate jdbc,String fieldName,BbdcTypeCdr cdr) throws CheckException ;

	void fieldIFormatCheck(String field,BbdcTypeCdr cdr) throws CheckException ;
	
	void dataValueRangeCheck(String field,BbdcTypeCdr cdr) throws CheckException ;
	
	void fieldLengthCheck(String field,BbdcTypeCdr cdr) throws CheckException ;
	
	void dateIFormatCheck(String field, BbdcTypeCdr cdr) throws CheckException;
	
	void fieldNumCheck(String[] S,int checkNum) throws CheckException;
}
