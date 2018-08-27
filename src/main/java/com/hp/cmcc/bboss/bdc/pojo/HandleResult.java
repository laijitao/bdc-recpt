package com.hp.cmcc.bboss.bdc.pojo;

public class HandleResult {

	private String sqlStr;
	private boolean isErr = false;
	
	public HandleResult() {
		super();
	}

	public HandleResult(String sqlStr, boolean isErr) {
		super();
		this.sqlStr = sqlStr;
		this.isErr = isErr;
	}

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	public boolean isErr() {
		return isErr;
	}

	public void setErr(boolean isErr) {
		this.isErr = isErr;
	}
	
}
