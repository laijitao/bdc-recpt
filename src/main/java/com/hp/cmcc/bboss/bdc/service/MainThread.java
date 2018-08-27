package com.hp.cmcc.bboss.bdc.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hp.cmcc.bboss.bdc.handle.HandleThread;
import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.bdc.pojo.HandleResult;
import com.hp.cmcc.bboss.bdc.pojo.ReturnResult;
import com.hp.cmcc.bboss.bdc.utils.BaseUtil;
import com.hp.cmcc.bboss.bdc.utils.Tools;

@Service
public class MainThread {
	private static Logger L = LoggerFactory.getLogger(MainThread.class);

	public ReturnResult handle(List<String> fileBody, List<BbdcTypeCdr> rule, String fileName) {
		ReturnResult returnResult = new ReturnResult();
		List<Future<HandleResult>> resultList = new LinkedList<Future<HandleResult>>();
		List<String> list = new LinkedList<>();
		int size = fileBody.size();
		long errNum = 0;
		long lineNum = 1;
		
		int checkNum = BaseUtil.getCheckNum(rule);
		BaseUtil.sort(rule);
		
		int corePoolSize = Runtime.getRuntime().availableProcessors();
	    ExecutorService executor  = Executors.newFixedThreadPool(corePoolSize);
	    for(int start = 0;start < size;start += corePoolSize) {
	    	int i = 0;
	    	while(start+i < size && i < corePoolSize){
	    		String record = fileBody.get(start+i);
	    		if(Tools.IsBlank(record)) {
	    			lineNum++;
	    			i++;
	    			continue;
	    		}
	    		HandleThread handleThread = new HandleThread(record, rule, fileName, lineNum++,checkNum);
	    		resultList.add(executor.submit(handleThread));
	    		i++;
	    	}
	    }
		
	    Iterator<Future<HandleResult>> iterator = resultList.iterator();
	    try {
			while(iterator.hasNext()) {
				HandleResult handleResult = iterator.next().get();
				list.add(handleResult.getSqlStr());
				if(handleResult.isErr()) {
					errNum++;
				}
			}
	    }catch (InterruptedException | ExecutionException  e) {
			L.error("threads exception!",e);
		}
	    returnResult.setResults(list);
	    returnResult.setErrNum(errNum);
	    
		return returnResult;
	}

}
