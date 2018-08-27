package com.hp.cmcc.bboss.bdc.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the BBDC_TYPE_CDR database table.
 * 
 */
public class BbdcTypeCdr implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String dataAlign;    //对其方式
	private String dataFiller;   //数据填充
	private Long dataLen;        //数据长度
	private String dataPattern;  //
	private String dataReplace;  //缺省填充
	private String dataSeparator;//分隔符
	private String dataType;     //数据类型
	private Date effDate;        //生效时间:2018/6/1
	private Date expDate;        //失效时间
	private Long fieldIdx;       //字段索引
	private Long fieldLen;       //字段长度
	private String fieldName;    //字段名
	private String fieldType;    //字段类型（CDR，CVS，enum）
	private String fieldValue;   //字段取值的来源（来自数据库查询还是系统配置）
	private Long formerIdx;      //记录处理前字段所处位置
	private Long hinderIdx;      //记录处理后字段所处位置
	private String valName;      //业务名称
	private String valType;      //字段所属部分（文件名，文件头，文件体，文件尾）
	private String validateRegex;//校验正则表达式



	public BbdcTypeCdr(long id, String valName, String valType, String fieldName, String fieldType, String fieldValue,
			Long fieldIdx, Long fieldLen, String dataType, Long dataLen, String dataPattern, String dataSeparator,
			String dataAlign, String dataFiller, String dataReplace, String validateRegex,
			Long formerIdx, Long hinderIdx) {
		super();
		this.id = id;
		this.dataAlign = dataAlign;
		this.dataFiller = dataFiller;
		this.dataLen = dataLen;
		this.dataPattern = dataPattern;
		this.dataReplace = dataReplace;
		this.dataSeparator = dataSeparator;
		this.dataType = dataType;
		this.fieldIdx = fieldIdx;
		this.fieldLen = fieldLen;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.fieldValue = fieldValue;
		this.formerIdx = formerIdx;
		this.hinderIdx = hinderIdx;
		this.valName = valName;
		this.valType = valType;
		this.validateRegex = validateRegex;
	}

	public BbdcTypeCdr() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDataAlign() {
		return this.dataAlign;
	}

	public void setDataAlign(String dataAlign) {
		this.dataAlign = dataAlign;
	}

	public String getDataFiller() {
		return this.dataFiller;
	}

	public void setDataFiller(String dataFiller) {
		this.dataFiller = dataFiller;
	}

	public Long getDataLen() {
		return this.dataLen;
	}

	public void setDataLen(Long dataLen) {
		this.dataLen = dataLen;
	}

	public String getDataPattern() {
		return this.dataPattern;
	}

	public void setDataPattern(String dataPattern) {
		this.dataPattern = dataPattern;
	}

	public String getDataReplace() {
		return this.dataReplace;
	}

	public void setDataReplace(String dataReplace) {
		this.dataReplace = dataReplace;
	}

	public String getDataSeparator() {
		return this.dataSeparator;
	}

	public void setDataSeparator(String dataSeparator) {
		this.dataSeparator = dataSeparator;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Date getEffDate() {
		return this.effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Long getFieldIdx() {
		return this.fieldIdx;
	}

	public void setFieldIdx(Long fieldIdx) {
		this.fieldIdx = fieldIdx;
	}

	public Long getFieldLen() {
		return this.fieldLen;
	}

	public void setFieldLen(Long fieldLen) {
		this.fieldLen = fieldLen;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldValue() {
		return this.fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public Long getFormerIdx() {
		return this.formerIdx;
	}

	public void setFormerIdx(Long formerIdx) {
		this.formerIdx = formerIdx;
	}

	public Long getHinderIdx() {
		return this.hinderIdx;
	}

	public void setHinderIdx(Long hinderIdx) {
		this.hinderIdx = hinderIdx;
	}

	public String getValName() {
		return this.valName;
	}

	public void setValName(String valName) {
		this.valName = valName;
	}

	public String getValType() {
		return this.valType;
	}

	public void setValType(String valType) {
		this.valType = valType;
	}

	public String getValidateRegex() {
		return this.validateRegex;
	}

	public void setValidateRegex(String validateRegex) {
		this.validateRegex = validateRegex;
	}

	@Override
	public String toString() {
		return "BbdcTypeCdr [id=" + id + ", dataAlign=" + dataAlign + ", dataFiller=" + dataFiller + ", dataLen="
				+ dataLen + ", dataPattern=" + dataPattern + ", dataReplace=" + dataReplace + ", dataSeparator="
				+ dataSeparator + ", dataType=" + dataType + ", effDate=" + effDate + ", expDate=" + expDate
				+ ", fieldIdx=" + fieldIdx + ", fieldLen=" + fieldLen + ", fieldName=" + fieldName + ", fieldType="
				+ fieldType + ", fieldValue=" + fieldValue + ", formerIdx=" + formerIdx + ", hinderIdx=" + hinderIdx
				+ ", valName=" + valName + ", valType=" + valType + ", validateRegex=" + validateRegex + "]";
	}

}