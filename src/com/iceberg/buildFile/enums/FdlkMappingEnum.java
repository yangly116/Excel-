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
public class FdlkMappingEnum implements Serializable {
    private String code;
    private String text;
    private static java.util.TreeMap map = new TreeMap();
    private static FdlkMappingEnum[] list = null;
 
    private FdlkMappingEnum(String code, String text) {
        this.code = code;
        this.text = text;
        map.put(this.code, this);
    }
 
    public static FdlkMappingEnum INTEGER = new FdlkMappingEnum("INTEGER", "I");
    public static FdlkMappingEnum NVARCHAR2 = new FdlkMappingEnum("NVARCHAR2", "C");
    public static FdlkMappingEnum VARCHAR2 = new FdlkMappingEnum("VARCHAR2", "C");
    public static FdlkMappingEnum NUMBER = new FdlkMappingEnum("NUMBER", "N");
    public static FdlkMappingEnum DATE = new FdlkMappingEnum("DATE", "D");

 
    public String getCode() {
        return code;
    }
 
    public String getText() {
        return text;
    }
 
    public static FdlkMappingEnum getType(String code) {
        return (FdlkMappingEnum) map.get(code);
    }
 
    public static FdlkMappingEnum[] getUsuallyString() {
        if (list == null) {
            int i = 0;
            Vector v = new Vector();
            java.util.Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                Object obj = it.next();
                FdlkMappingEnum type = (FdlkMappingEnum) map.get(obj);
                v.add(type);
            }
            list = new FdlkMappingEnum[v.size()];
            v.copyInto(list);
        }
        return list;
    }
 
    public int compareTo(Object obj) {
        FdlkMappingEnum type = (FdlkMappingEnum) obj;
        return this.getCode().compareTo(type.getCode());
    }
 
    public boolean equals(Object obj) {
        boolean ret = (obj != null) && (obj instanceof FdlkMappingEnum);
        if (ret) {
            FdlkMappingEnum type = (FdlkMappingEnum) obj;
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