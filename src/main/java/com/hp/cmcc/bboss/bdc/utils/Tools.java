package com.hp.cmcc.bboss.bdc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.cmcc.bboss.bdc.utils.PubData.TimeStrFmt;

public class Tools {
	static Logger L = LoggerFactory.getLogger(Tools.class);
	
	public String str2UpperCase(String s) {
		return s.toUpperCase();
	}
	
	public static boolean IsEmpty(String str) {
		return str == null ? true : str.length() == 0 ? true : false;
	}

	public static boolean IsBlank(String str) {
		return str == null ? true : str.length() == 0 ? true : str.trim().length() == 0 ? true : false;
	}
	public static boolean IsEmpty(Object obj) {
		return obj == null ? true : false;
	}
	
	public static long Str2Long(String time_str, TimeStrFmt fmt) {
		return Str2Time(time_str, fmt).atZone(PubData.LocTZ).toInstant().toEpochMilli();
	}
	
	public static String strToUpperCase(String s) {
		return Tools.IsBlank(s) == true ? null : s.toUpperCase();
	}
	
	public static LocalDateTime Str2Time(String time_str, TimeStrFmt fmt) {
		LocalDateTime rv_ = PubData.UNIX_EPOCH_DATE;
		DateTimeFormatter dtf = null;
		String time_str_ = time_str;
		switch (fmt) {
		case Fmt23:
			dtf = PubData.TMDF23;
			if (time_str.length() > 23)
				time_str_ = time_str.substring(0, 23);
			if (time_str_.length() > 10 && time_str_.charAt(10) != ' ')
				time_str_ = time_str_.substring(0, 9) + " " + time_str_.substring(11);
			break;
		case Fmt19:
			if (time_str.length() > 19)
				time_str_ = time_str.substring(0, 19);
			dtf = PubData.TMDF19;
			break;
		case Fmt18:
			dtf = PubData.TMDF18;
			if (time_str.length() > 18)
				time_str_ = time_str.substring(0, 18);
			break;
		case Fmt17:
			dtf = PubData.TMDF17;
			if (time_str.length() > 17)
				time_str_ = time_str.substring(0, 17);
			break;
		case Fmt16:
			dtf = PubData.TMDF16;
			if (time_str.length() > 16)
				time_str_ = time_str.substring(0, 16);
			break;
		case Fmt14:
			dtf = PubData.TMDF14;
			if (time_str.length() > 14)
				time_str_ = time_str.substring(0, 14);
			break;
		case Fmt12:
			dtf = PubData.TMDF12;
			if (time_str.length() > 12)
				time_str_ = time_str.substring(0, 12);
			break;
		case Fmt10:
			dtf = PubData.DTDF10;
			if (time_str.length() > 10)
				time_str_ = time_str.substring(0, 10);
			break;
		case Fmt8:
			dtf = PubData.DTDF8;
			if (time_str.length() > 8)
				time_str_ = time_str.substring(0, 8);
			break;
		case Fmt6:
			dtf = PubData.DTDF6;
			if (time_str.length() > 6)
				time_str_ = time_str.substring(0, 6);
			break;
		default:
			L.warn("ukn fmt [{}], regard as Fmt14", fmt);
			dtf = PubData.TMDF14;
			if (time_str.length() > 14)
				time_str_ = time_str.substring(0, 14);
			break;
		}
		try {
			LocalDateTime dt_ = LocalDateTime.parse(time_str_, dtf);// ((SimpleDateFormat) sdf_.clone()).parse(time_str_);
			rv_ = dt_;
		} catch (DateTimeParseException e) {
			L.warn(FmtArgs("parse [%s,%s] exception, regard as UNIX_EPOCH", time_str, fmt), e);
		}
		return rv_;
	}
	
	public static String Long2Str(long tm, TimeStrFmt fmt) {
		return Time2Str(LocalDateTime.ofInstant(Instant.ofEpochMilli(tm), PubData.LocTZ), fmt);
	}

	public static String Time2Str(LocalDateTime tm, TimeStrFmt fmt) {
		DateTimeFormatter dtf = null;
		switch (fmt) {
		case Fmt23:
			dtf = PubData.TMDF23;
			break;
		case Fmt19:
			dtf = PubData.TMDF19;
			break;
		case Fmt18:
			dtf = PubData.TMDF18;
			break;
		case Fmt17:
			dtf = PubData.TMDF17;
			break;
		case Fmt16:
			dtf = PubData.TMDF16;
			break;
		case Fmt14:
			dtf = PubData.TMDF14;
			break;
		case Fmt12:
			dtf = PubData.TMDF12;
			break;
		case Fmt10:
			dtf = PubData.DTDF10;
			break;
		case Fmt8:
			dtf = PubData.DTDF8;
			break;
		case Fmt6:
			dtf = PubData.DTDF6;
			break;
		default:
			L.warn("ukn fmt [{}], regard as Fmt14", fmt);
			dtf = PubData.TMDF14;
			break;
		}
		return dtf.format(tm);
	}
	
	/**
	 * @param src:源路径
	 * @param dst：目标路径
	 * @return 返回复制文件操作是否成功，成功后删除源文件
	 */
	public static boolean CopyAFile(String src, String dst) {
		boolean rc_ = true;
		File dst_file_ = new File(dst);
		String dst_tmp_ = dst_file_.getParent() + "/." + dst_file_.getName();
		try {
			FileInputStream fis_ = new FileInputStream(src);
			FileOutputStream fos_ = new FileOutputStream(dst_tmp_);
			FileChannel ic_ = fis_.getChannel();
			FileChannel oc_ = fos_.getChannel();
			ByteBuffer buf_ = ByteBuffer.allocateDirect(2048);
			int r = 0;
			while (true) {
				buf_.clear();
				r = ic_.read(buf_);
				if (r == -1) {
					break;
				}
				buf_.flip();
				oc_.write(buf_);
			}
			fis_.close();
			fos_.close();
			File hide_ = new File(dst_tmp_);
			if (dst_file_.exists()) {
				dst_file_.delete();
			}
			rc_ = hide_.renameTo(dst_file_);
			if (rc_ == false) {
				L.error("rename({},{}) failed", hide_.getPath(), dst_file_.getPath());
			}
		} catch (Exception e) {
			L.error(FmtArgs("cp [%s,%s] exception", src, dst), e);
			rc_ = false;
		}
		return rc_;
	}
	
	/**
	 * @param src:源路径
	 * @param dst：目标路径
	 * @return 返回移动文件操作是否成功，成功后删除源文件
	 */
	public static boolean MoveAFile(String src, String dst) {
		boolean rc_ = true;
		File src_file_ = new File(src);
		File dst_file_ = new File(dst);
		try {
			if (src_file_.renameTo(dst_file_))
				return true;
			L.info("rename({},{}) false, try copy then delete", src, dst);
			if (!CopyAFile(src, dst)) {
				L.warn("call CopyAFile({},{}) error", src, dst);
				return false;
			}
			if (src_file_.delete()) {
				return true;
			} else {
				L.warn("delete ({}) error after copy to ({})", src, dst);
				return false;
			}
		} catch (Exception e) {
			L.error(FmtArgs("MoveAFile(%s,%s) exception", src, dst), e);
			rc_ = false;
		}
		return rc_;
	}
	
	public static boolean Gzip(String gzip_path, File file, boolean is_compress) {
		String gzip_cmd_ = null;
		if (is_compress) {
			if (file.getName().endsWith(".gz")) {
				L.info("{} already has .gz suffix, can not compress again", file.getAbsolutePath());
				return true;
			}
			gzip_cmd_ = String.format("%s -f -9 %s", gzip_path, file.getAbsolutePath());
		} else {
			if (!file.getName().endsWith(".gz")) {
				L.warn("{} has no .gz suffix, can not be un-compressed", file.getAbsolutePath());
				return false;
			}
			gzip_cmd_ = String.format("%s -f -d %s", gzip_path, file.getAbsolutePath());
		}

		List<String> cmd_ = Tools.AssembleCmd(gzip_cmd_);
		ProcessBuilder pb_ = new ProcessBuilder(cmd_);
		pb_.inheritIO();

		try {
			Process proc_ = pb_.start();
			int exit_code_ = proc_.waitFor();
			if (exit_code_ == 0) {
				L.trace("invoke [{}] with exit_code 0, ok", gzip_cmd_);
			} else {
				L.warn("invoke [{}] returns {}", gzip_cmd_, exit_code_);
				return false;
			}
		} catch (Exception e) {
			L.warn("invoke {} exception", gzip_cmd_, e);
			return false;
		}

		return true;
	}
	
	public static List<String> AssembleCmd(String... command) {
		List<String> cmd_ = new ArrayList<String>();
		String os_nm_ = System.getProperty("os.name");
		if (os_nm_ != null && (os_nm_.startsWith("Windows"))) {
			cmd_.add("cmd");
			cmd_.add("/c");
		} else {
			cmd_.add("/bin/sh");
			cmd_.add("-c");
		}
		cmd_.addAll(Arrays.asList(command));
		return cmd_;
	}
	
	public static String FmtArgs(String fmt, Object... args) {
		String info = null;
		try {
			info = String.format(fmt, args);
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append(fmt);
			sb.append(" [fmt error:");
			sb.append(e.getMessage());
			sb.append("] [");
			for (int i = 0; i < args.length; ++i) {
				if (args[i] == null) {
					sb.append("null");
				} else {
					sb.append(args[i].toString());
				}
				if (i < args.length - 1) {
					sb.append(";");
				}
			}
			sb.append("]");
			info = sb.substring(0);
		}
		return info;
	}
	
	public static void CloseJdbc(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				L.warn("close ResultSet exception", e);
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				L.warn("close Statement exception", e);
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				L.warn("close Connection exception", e);
			}
		}
	}
	/**
	 * @param fmt 日期格式
	 * @return 获取系统当前时间字符串
	 */
	public static String getNowTime(String fmt){
		
		long now = System.currentTimeMillis();
		SimpleDateFormat sd = new SimpleDateFormat(fmt);
		return sd.format(new Date(now));
	}
	/**
	 * @param d1
	 * @param d2
	 * @return 比较两个时间字符串的大小
	 */
	public static int dateStrCompare(String d1,String d2){
		return d1.compareTo(d2);
	}
	/**
	 * @param str
	 * @param s
	 * @return str中是否有s子串
	 */
	public static boolean containsStr(String str,String s){
		if(str != null && !"".equals(str) && str.indexOf(s) >= 0){
			return true;
		}
		return false;
	}
	/**
	 * @param time
	 * @param pat
	 * @return time是否符合日期格式pat
	 */
	public static boolean corTimeFmt(String time,Pattern pat){
		Matcher matcher = pat.matcher(time);
		return matcher.find();
	}
	/**
	 * @param date 
	 * @param n
	 * @return date后n天的日期
	 */
	public static java.util.Date getNextNDay(java.util.Date date,int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +n);//+1今天的时间加一天
        date = calendar.getTime();
        return date;
    }
	/**
	 * @param d1
	 * @param d2
	 * @return d1是否早于或等于d2
	 */
	public static boolean compareTimeEqual(java.util.Date d1,java.util.Date d2){
		if(d1 == null || d2 == null || d1.after(d2)){
			return false;
		}
		return true;
	}
	/**
	 * @param d1
	 * @param d2
	 * @return d1是否早于d2
	 */
	public static boolean compareTime(java.util.Date d1,java.util.Date d2){
		if(d1 != null && d2 != null && d1.before(d2)){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param d 时间字符串
	 * @param fmt 日期格式
	 * @return 日期
	 * @throws ParseException 
	 */
	public static java.util.Date strToDate(String d,String fmt) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		sdf.setLenient(false);
		java.util.Date date = null;
		try {
			date = sdf.parse(d);
		} catch (ParseException e) {
			throw new ParseException("parse error, pls check!", 0);
		}
		return date;
	}
	
	public static boolean dateFormatCheck(String d,String fmt) {
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		sdf.setLenient(false);
		try {
			sdf.parse(d);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	public static boolean compareDateStrEqual(String d1,String d2,String fmt){
		try {
			return compareTimeEqual(strToDate(d1,fmt),strToDate(d2,fmt));
		} catch (ParseException e) {
			return false;
		}
	}
	
	public static boolean dateStrLtAndEqal(String d1,String d2,String fmt){
		try {//d1小于等于d2
			return compareTime(strToDate(d1,fmt),strToDate(d2,fmt))
					||compareDateStrEqual(d1,d2,fmt);
		} catch (ParseException e) {
			return false;
		}
	}
	
	public static boolean dateStrGtAndEqal(String d1,String d2,String fmt){
		try {//d1大于等于d2
			return !compareTime(strToDate(d1,fmt),strToDate(d2,fmt))
					||compareDateStrEqual(d1,d2,fmt);
		} catch (ParseException e) {
			return false;
		}
	}
	
	public static String[] strToArr(String record){
		return record.split(";",-1);
	}
	
	public static String[] getArrFromStr(String record,String rex){
		return record.split(rex,-1);
	}
	
	public static <T> List<T> mapToList(Map<String,T> map) {
		List<T> list = new LinkedList<>();
		for(Entry<String,T> entry : map.entrySet()) {
			list.add((T)entry.getValue());
		}
		return list;
	}

	public static boolean provCodeExist(String provCode,String allProvCode) {
		String[] pcs = allProvCode.split(",",-1);
		for(String s : pcs) {
			if(!provCode.equals(s)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isExistInArr(Object obj,Object[] arr) {//仅仅比较值
		for(Object o : arr) {
			if(o.equals(obj)) return true;
		}
		return false;
	}
	
	public static boolean isExistInArr(String str,String... arr) {
		for(String o : arr) {
			if(o.equals(str)) return true;
		}
		return false;
	}
	
	public static boolean dataFormatCheck(String str, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	
	public static String replaceAll(String regex,String str,String... S) {
		for(String s : S) {
			str = str.replaceFirst(regex, s);
		}
		return str;
	}
	
}
