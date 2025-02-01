package com.example.demo.log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogCreator {
	
	public static void error(String content) {
		log.error(content);
	}
	
	public static void info(String content) {
		log.info(content);
	}
}
