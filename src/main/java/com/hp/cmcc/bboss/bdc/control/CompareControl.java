package com.hp.cmcc.bboss.bdc.control;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.bdc.pojo.RequestParamter;
import com.hp.cmcc.bboss.bdc.pojo.ReturnResult;
import com.hp.cmcc.bboss.bdc.service.MainThread;

@RestController
public class CompareControl {
	@Autowired
	MainThread threads;
	private static Logger L = LoggerFactory.getLogger(CompareControl.class);

	@RequestMapping(value = "/ljt/test")
	public ReturnResult test() {
		List<String> fileBody = new LinkedList<>();
		fileBody.add("G20180820165211870000000000000;A;20180812000000;090408;37846929                ;000;1;20180817;20991231;1;010101001;378768846929                                      ;1;ALL");   
		List<BbdcTypeCdr> rule = new LinkedList<>(); 
		String fileName = "file_name"; 
		
//		CorpSmsMain corpSmsMain = new CorpSmsMainImpl();
//		return corpSmsMain.handle(fileBody, rule, fileName, tranId);
		
//		Threads threads = new ThreadsImpl();
		return threads.handle(fileBody, rule, fileName);
	}
	
	@RequestMapping(value = "/bdc/corpSMS/compare", method = RequestMethod.POST,consumes = "application/json")
	public ReturnResult threadHandle(@RequestBody RequestParamter requestParamter) {
		List<String> fileBody = requestParamter.getFileBody();
		List<BbdcTypeCdr> rule = requestParamter.getRule();
		String fileName = requestParamter.getFileName();
		L.info("file name:"+fileName+",record count :"+fileBody.size());
		return threads.handle(fileBody, rule, fileName);
	}
}
