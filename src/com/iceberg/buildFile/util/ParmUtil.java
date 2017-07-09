package com.iceberg.buildFile.util;

import java.util.ArrayList;
import java.util.List;

/** 
 * 
 * @author 作者：杨文培  E-mail:1164180794@qq.com 
 * @version 创建时间：Jul 6, 2017 9:59:51 PM  
 */
public class ParmUtil {
	/**
	 * @parmm node 需要替换的节点字符串
	 * @parmm parm 节点中需要替换的参数
	 * @parmm targ 将targ替换parm(${parm})
	 * @return
	 */
	public static String replaceparm(String node,String targ,String parm){
		String result = new String();
		String regex = "\\$\\{"+parm+"\\}";
		if(targ == null){
			System.out.println("targ为null");
			targ = "";
		}
		result = node.replaceAll(regex, targ);
		return result;
	}
	/**
	 * @Description:
	 * @param node
	 * @param ltargs
	 * @param lparms
	 * @return
	 * @author 作者 杨文培
	 * @since：Jul 8, 2017 6:34:22 PM
	 */
	public static String replaceparm(String node,List<String> ltargs,List<String> lparms){
		String result = node;
		if(ltargs.size() == lparms.size()){
			for(int i=0;i<ltargs.size();i++){
				String targ = ltargs.get(i);
				String parm = lparms.get(i);
				result = replaceparm(result, targ, parm);
			}
		}
		return result;
	}
	/**
	 * @param str [split id="field"]
	 * @return
	 */
	public static String getIdContent(String node,String attrName ,String value){
		String result = new String();
		String regex = "^(id)[\\s]{*}=\\{"+value+"\\}$";
		if(attrName == null){
			System.out.println("attrName为null");
			attrName = "";
		}
		//result = node.replaceAll(regex, targ);
		return result;
	}
	public static String getParm(String parm){
		return "\\$\\{"+parm+"\\}";
	}
	public static String getParms(List<String> lParms){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < lParms.size(); i++) {
			builder.append(getParm(lParms.get(i))+"|");
		}
		String resultStr = builder.toString();
		resultStr = resultStr.substring(0, resultStr.length()-1);
		return resultStr;
	}
	public static List<String> getRegexs(List<String> lParms){
		List<String> lRegexs = new ArrayList<>();
		for (int i = 0; i < lParms.size(); i++) {
			lRegexs.add(getParm(lParms.get(i)));
		}
		return lRegexs;
	}
}
