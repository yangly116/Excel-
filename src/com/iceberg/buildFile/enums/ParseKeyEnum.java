package com.iceberg.buildFile.enums;

import java.io.Serializable;
import java.util.TreeMap;
import java.util.Vector;
 
/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * </p>
 *
 * @author 作者 杨文培
 * 
 * @since：2017年2月24日 下午1:50:48
 * 
 */
public class ParseKeyEnum implements Serializable {
    private String code;
    private String text;
    private static java.util.TreeMap map = new TreeMap();
    private static ParseKeyEnum[] list = null;
 
    private ParseKeyEnum(String code, String text) {
        this.code = code;
        this.text = text;
        map.put(this.code, this);
    }
 
    public static ParseKeyEnum ib_insert = new ParseKeyEnum("ib-insert", "ib-insert");
    public static ParseKeyEnum table = new ParseKeyEnum("table", "table");

 
    public String getCode() {
        return code;
    }
 
    public String getText() {
        return text;
    }
 
    public static ParseKeyEnum getType(String code) {
        return (ParseKeyEnum) map.get(code);
    }
 
    public static ParseKeyEnum[] getUsuallyString() {
        if (list == null) {
            int i = 0;
            Vector v = new Vector();
            java.util.Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                Object obj = it.next();
                ParseKeyEnum type = (ParseKeyEnum) map.get(obj);
                v.add(type);
            }
            list = new ParseKeyEnum[v.size()];
            v.copyInto(list);
        }
        return list;
    }
 
    public int compareTo(Object obj) {
        ParseKeyEnum type = (ParseKeyEnum) obj;
        return this.getCode().compareTo(type.getCode());
    }
 
    public boolean equals(Object obj) {
        boolean ret = (obj != null) && (obj instanceof ParseKeyEnum);
        if (ret) {
            ParseKeyEnum type = (ParseKeyEnum) obj;
            ret = type.getCode().equals(this.getCode());
        }
        return ret;
    }
 
    public int hashCode() {
        return this.code.hashCode();
    }
 
    public String toString() {
        return text + " {" + this.code + "}";
    }
}