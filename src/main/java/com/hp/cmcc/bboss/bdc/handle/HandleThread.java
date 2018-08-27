package com.hp.cmcc.bboss.bdc.handle;

import java.util.List;
import java.util.concurrent.Callable;

import com.hp.cmcc.bboss.bdc.check.CheckAndCreateSqlStr;
import com.hp.cmcc.bboss.bdc.check.impl.CheckAndCreateSqlStrImpl;
import com.hp.cmcc.bboss.bdc.exception.CheckException;
import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.bdc.pojo.HandleResult;

public class HandleThread implements Callable<HandleResult>{
	private static CheckAndCreateSqlStr checkAndCreateSqlStr = new CheckAndCreateSqlStrImpl();
	private String record;
	List<BbdcTypeCdr> list;
	private String fileName;
	private long lineNum;
	private int checkNum;

	@Override
	public HandleResult call() throws Exception {
		HandleResult result = new HandleResult();
		try {
			String s = checkAndCreateSqlStr.checkAndCreateSqlStr(record, list, fileName, lineNum, checkNum);
			result.setSqlStr(s);
		}catch(CheckException e) {
			result.setSqlStr(e.getRecSql());
			result.setErr(true);
		}
		return result;
	}

	public HandleThread() {
		super();
	}

	public HandleThread(String record, List<BbdcTypeCdr> list, String fileName,
			long lineNum, int checkNum) {
		super();
		this.record = record;
		this.list = list;
		this.fileName = fileName;
		this.lineNum = lineNum;
		this.checkNum = checkNum;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public List<BbdcTypeCdr> getDoneRecs() {
		return list;
	}

	public void setDoneRecs(List<BbdcTypeCdr> list) {
		this.list = list;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getLineNum() {
		return lineNum;
	}

	public void setLineNum(long lineNum) {
		this.lineNum = lineNum;
	}

	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}
	
}
