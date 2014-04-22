package com.my.mybatis.plugins;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceTable {
		
	public static String replace(String sql,String suffix){
		String newSql = sql;
		
		String regx1 = "\\s+from\\s+(\\w+)\\s*";
		Pattern pattern1 = Pattern.compile(regx1, Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(sql);
		while(matcher1.find()){
			String replaceStr = matcher1.group(1);
			newSql = newSql.replace(replaceStr, replaceStr+suffix);		
		}
		
		String regx2 = "\\s+(left|inner|outer|)\\s+join\\s+(\\w+)\\s+";
		Pattern pattern2 = Pattern.compile(regx2, Pattern.CASE_INSENSITIVE);
		Matcher matcher2 = pattern2.matcher(sql);
		while(matcher2.find()){
			String replaceStr = matcher2.group(2);
			newSql = newSql.replace(replaceStr, replaceStr+suffix);		
		}
        
		return newSql;
	}
	
	
	public static void main(String[] args){
		String s1 = ReplaceTable.replace("select * from user where name='12'","_100");
		String s2 = ReplaceTable.replace("select * from User left join Address on User.id = Address.id","_100");
		System.out.println(s1);
		System.out.println(s2);
	}
}
