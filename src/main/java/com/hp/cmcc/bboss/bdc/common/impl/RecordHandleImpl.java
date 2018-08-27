package com.hp.cmcc.bboss.bdc.common.impl;

import com.hp.cmcc.bboss.bdc.common.RecordHandle;
import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;

public class RecordHandleImpl implements RecordHandle {

	@Override
	public String getValue(String[] S, BbdcTypeCdr cdr) {
		int index = cdr.getFormerIdx().intValue();
		return index < S.length && index >= 0 ? S[index].trim() : "";
	}

}
