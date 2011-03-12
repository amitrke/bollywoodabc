package com.babc.server.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppUtils {
	
	private static String urlValidPattern = "[^a-zA-z0-9\\.\\s]";
	
	public static List<List<Long>> photoGalleryGrid(List<Long> values, int x){
		List<List<Long>> returnData = new ArrayList<List<Long>>();
		int counter=0;
		List<Long> row = new ArrayList<Long>();
		for(Long value: values){
			row.add(value);
			if (counter >= x){
				returnData.add(row);
				row = new ArrayList<Long>();
				counter = -1;
			}
			counter++;
		}
		if (row.size() > 0){
			returnData.add(row);
		}
		return returnData;
	}
	
	public static String urlFormat(String inputString){
		return inputString.replaceAll(urlValidPattern, "").replaceAll("\\b[\\w']{1,2}\\b", "").replaceAll("\\s{2,}", " ").trim().replaceAll(" ", "-");
	}
	
	/**
	 * Sets UI Pagination data to the map Object.
	 * 
	 * @param map
	 */
	public static void setUIPaginationData(Map<String, Object> map,
			int currentPageno, int noOfRecords, int noOfRecordsPerPage){
		
	}
	
	public static String getKind(Class clazz) {
		String name = clazz.getName();
		return name.substring(name.lastIndexOf('.') + 1);
	}
}
