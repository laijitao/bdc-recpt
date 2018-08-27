package com.hp.cmcc.bboss.bdc.pojo;

import java.io.Serializable;
import java.util.List;

public class ReturnResult implements Serializable{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	
	private static final long serialVersionUID = 1L;
	private List<String> results;
	private long errNum;
	
	public ReturnResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReturnResult(List<String> results, long errNum) {
		super();
		this.results = results;
		this.errNum = errNum;
	}
	
	public List<String> getResults() {
		return results;
	}
	
	public void setResults(List<String> results) {
		this.results = results;
	}
	
	public long getErrNum() {
		return errNum;
	}
	
	public void setErrNum(long errNum) {
		this.errNum = errNum;
	}
	
}
