package com.hp.cmcc.bboss.bdc.utils;

import java.util.Comparator;
import java.util.List;

import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;

public class BaseUtil {

	public static String[] StrToArr(String str,String separator) {
		return str.split(separator,-1);
	}
	
	public static String getTaskSql(String sql,String regex,String... values) {
		return Tools.replaceAll(regex, sql, values);
	}
	
	public static int getCheckNum(List<BbdcTypeCdr> list) {
		int checkNum = 0;
		for(BbdcTypeCdr cdr : list) {
			if(cdr.getFormerIdx() != -1) {
				checkNum++;
			}
		}
		return checkNum;
		}

	public static void sort(List<BbdcTypeCdr> rule) {
		rule.sort(new Comparator<BbdcTypeCdr>() {
			@Override
			public int compare(BbdcTypeCdr o1, BbdcTypeCdr o2) {
				return o1.getHinderIdx().intValue()-o2.getHinderIdx().intValue();
			}
		});
	}
	
	public static String getOrderId(List<BbdcTypeCdr> rule,String[] S) {
		for(BbdcTypeCdr cdr : rule) {
			if("ORDER_ID".equals(cdr.getFieldName())) {
				return S[cdr.getFormerIdx().intValue()];
			}
		}
		return null;
	}
	
}
