package com.hp.cmcc.bboss.bdc.pojo;

import java.io.Serializable;
import java.util.List;

public class RequestParamter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> fileBody;
	private List<BbdcTypeCdr> rule;
	private String fileName;
	private String tranId;
	
	public RequestParamter() {
		super();
	}

	public RequestParamter(List<String> fileBody, List<BbdcTypeCdr> rule, String fileName,String tranId) {
		super();
		this.fileBody = fileBody;
		this.rule = rule;
		this.fileName = fileName;
		this.tranId = tranId;
	}

	public List<String> getFileBody() {
		return fileBody;
	}

	public void setFileBody(List<String> fileBody) {
		this.fileBody = fileBody;
	}

	public List<BbdcTypeCdr> getRule() {
		return rule;
	}

	public void setRule(List<BbdcTypeCdr> rule) {
		this.rule = rule;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTranId() {
		return tranId;
	}

	public void setTranId(String tranId) {
		this.tranId = tranId;
	}
	
}
