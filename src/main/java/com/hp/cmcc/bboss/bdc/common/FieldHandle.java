package com.hp.cmcc.bboss.bdc.common;

import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;

public interface FieldHandle {

	String getValue(String value,BbdcTypeCdr cdr);
	
	String getValueFromDatabase(String field,BbdcTypeCdr cdr);
	
	String getValueWithDatabase(BbdcTypeCdr cdr);
	
	String getSqlStr(String value, BbdcTypeCdr cdr);
	
	String setValue(String value, BbdcTypeCdr cdr);
}
