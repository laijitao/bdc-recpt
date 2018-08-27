package com.hp.cmcc.bboss.bdc.exception;

public class CheckException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String recSql;
	private String errCode;
	private String errMsg;
	
	public CheckException() {
		super();
	}

	public CheckException(String errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	public CheckException(String recSql,String errCode, String errMsg) {
		super();
		this.recSql = recSql;
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRecSql() {
		return recSql;
	}

	public void setRecSql(String recSql) {
		this.recSql = recSql;
	}
	
	public String toString() {
		return  "{recSql:"+recSql+",errCode:" +errCode+"," +"errMsg:"+ errMsg +"}";
	}
}
