package com.hp.cmcc.bboss.bdc.check;

import java.util.List;

import com.hp.cmcc.bboss.bdc.exception.CheckException;
import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;

public interface CheckAndCreateSqlStr {

	public String checkAndCreateSqlStr(String record, List<BbdcTypeCdr> list, 
			String fileName, long lineNum, int checkNum) throws CheckException;
}
