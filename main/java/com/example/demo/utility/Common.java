package com.example.demo.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.demo.log.LogCreator;

public final class Common {
	
	//ユーティリティクラスなのでインスタンスは持たない
	private Common() {
		throw new UnsupportedOperationException("This is utility class");
	}
	
	/**
	* String=>LocalDate
	* @param 日付文字列、フォーマット文字れ烈
	* @return LocalDate型に変換した日付データ
	*/
	public static LocalDate stringToDate(String strDate,String formatPattern) {
		LocalDate formatDate;
		
		try {
			var DateTimeFormatPattern = DateTimeFormatter.ofPattern(formatPattern);
			formatDate = LocalDate.parse(strDate,DateTimeFormatPattern);
			return formatDate;
		} catch (IllegalArgumentException  e) {
			LogCreator.error(e.getMessage());			
			formatDate = LocalDate.parse(strDate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			return null;
		}
	}
}
