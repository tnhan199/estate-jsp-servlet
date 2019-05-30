package com.trantrongnhan.Utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtils {
	public  static <T> T toModel(BufferedReader bufferedReader,T clazz) {
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			clazz=(T) objectMapper.readValue(bufferedReader, clazz.getClass());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (JsonMappingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return clazz;
	}
}
