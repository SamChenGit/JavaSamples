package com.myCommunity.ui.util;


public class LenLimitFunction {
	
	public static String lenLimit(String content, Integer maxLen){
		if(content.length()<=maxLen){
			return content;
		}else{
			return content.substring(0,maxLen)+"...";
		}
	}
}
