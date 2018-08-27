package com.hp.cmcc.bboss.bdc.check.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hp.cmcc.bboss.bdc.check.CheckAndCreateSqlStr;
import com.hp.cmcc.bboss.bdc.check.CommonCheck;
import com.hp.cmcc.bboss.bdc.common.FieldHandle;
import com.hp.cmcc.bboss.bdc.common.RecordHandle;
import com.hp.cmcc.bboss.bdc.common.impl.FieldHandleImpl;
import com.hp.cmcc.bboss.bdc.common.impl.RecordHandleImpl;
import com.hp.cmcc.bboss.bdc.config.BdcBeanFactory;
import com.hp.cmcc.bboss.bdc.dao.BaseDao;
import com.hp.cmcc.bboss.bdc.dao.impl.BaseDaoImpl;
import com.hp.cmcc.bboss.bdc.exception.CheckException;
import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.bdc.utils.BaseUtil;

public class CheckAndCreateSqlStrImpl implements CheckAndCreateSqlStr {
	
	private static BaseDao baseDao = new BaseDaoImpl();
	private static CommonCheck commonCheck = new CommonCheckImpl();
	private static FieldHandle fieldHandle = new FieldHandleImpl();
	private static RecordHandle recordHandle = new RecordHandleImpl();
	private static JdbcTemplate jdbc = BdcBeanFactory.getBean("bdcTemplate", JdbcTemplate.class);

	@Override
	public String checkAndCreateSqlStr(String record, List<BbdcTypeCdr> list, String fileName, long lineNum, int checkNum) throws CheckException {
		String separator = list.get(0).getDataSeparator();
		String[] S = BaseUtil.StrToArr(record, separator);
		StringBuffer recordSql = new StringBuffer();
		boolean fieldNumMark = true;//字段数量或多或少
		boolean mark = true;//错误表示
		String bdcErrCode = "";
		String orderId = BaseUtil.getOrderId(list, S);
		
		try {
			commonCheck.fieldNumCheck(S, checkNum);
		} catch (CheckException e1) {
			e1.setRecSql("");
			fieldNumMark = false;
			mark = false;
			bdcErrCode = "F999";
		}
		
		String value = "";
		for(BbdcTypeCdr cdr : list) {
			if(fieldNumMark) {
				if(cdr.getFormerIdx() != -1) {
					if(!fieldNumMark) {
						value = null;
					}else {
						value = "'"+recordHandle.getValue(S, cdr)+"'";
					}
				}else {
					switch(cdr.getFieldName()) {
						case"FILE_NAME":
							value = fieldHandle.getValue(fileName,cdr);
							break;
						case"LINE_NUM":
							value = fieldHandle.getValue(lineNum+"",cdr);
							break;
						case"OPER_SERIAL_NBR":
							if(!fieldNumMark) {
								value = null;
							}else {
								try {
									value = baseDao.getOperSerialNbrBy(jdbc, cdr.getDataFiller(), orderId);
								}catch(CheckException e) {
									mark = false;
									bdcErrCode = "F998";
								}
							}
							break;
						case"BDC_ERR_CODE":
							if(mark) {
								value = cdr.getDataFiller();
							}else {
								value = bdcErrCode;
							}
							break;
						default:
							value = fieldHandle.getValue("",cdr);
					}
				}
			}else {
				value = null;
			}
			recordSql.append(value+",");
		}
		String sqlStr = recordSql.toString().substring(0,recordSql.toString().length()-1);
		
		if(!mark) {
			throw new CheckException(sqlStr,bdcErrCode,"");
		}
		return sqlStr;
	}

}
