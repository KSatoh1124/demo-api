package com.example.demo.utility;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import com.example.demo.log.LogCreator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Common {
	
	//ユーティリティクラスなのでインスタンスは持たない
	private Common() {
		throw new UnsupportedOperationException("This is utility class");
	}
	
	/**
	* String=>LocalDate
	* @param 日付文字列、フォーマット文字列
	* @return LocalDate型に変換した日付データ
	*/
	public static LocalDate stringToDate(String strDate,String formatPattern) {
		LocalDate formatDate;
		
		try {
			var DateTimeFormatPattern = DateTimeFormatter.ofPattern(formatPattern);
			formatDate = LocalDate.parse(strDate,DateTimeFormatPattern);
			return formatDate;
		} catch (Exception e) {
			LogCreator.error(e.getMessage());
			return null;
		}
	}
	
	/**
	* base64文字列=>JsonNode
	* @param base64文字列
	* @return JsonNode
	*/
	public static JsonNode base64ToJsonNode(String item) {
		var byteContents = Base64.getDecoder().decode(item);
		var stringSerchParams = new String(byteContents,StandardCharsets.UTF_8);
		
		var objectMapper = new ObjectMapper();
		try {
			var jsonNodes = objectMapper.readTree(stringSerchParams);
			return jsonNodes;
		} catch (Exception e) {
			LogCreator.error(e.getMessage());
			return null;
		}
	}
}
