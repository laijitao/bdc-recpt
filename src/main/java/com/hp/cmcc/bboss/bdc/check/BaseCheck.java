package com.hp.cmcc.bboss.bdc.check;

import com.hp.cmcc.bboss.bdc.exception.CheckException;
import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;

public interface BaseCheck {

	void fieldCheck(String field,BbdcTypeCdr cdr) throws CheckException;
}
