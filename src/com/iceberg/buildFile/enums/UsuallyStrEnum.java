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
public class UsuallyStrEnum implements Serializable {
    private String code;
    private String text;
    private static java.util.TreeMap map = new TreeMap();
    private static UsuallyStrEnum[] list = null;
 
    private UsuallyStrEnum(String code, String text) {
        this.code = code;
        this.text = text;
        map.put(this.code, this);
    }
 
    public static UsuallyStrEnum RESULT = new UsuallyStrEnum("1", "result");
    public static UsuallyStrEnum FILECONTENT = new UsuallyStrEnum("2", "fileContent");
    public static UsuallyStrEnum OPTIONTYPE = new UsuallyStrEnum("3", "optionType");
    public static UsuallyStrEnum ISSUCCESS = new UsuallyStrEnum("4", "isSuccess");

 
    public String getCode() {
        return code;
    }
 
    public String getText() {
        return text;
    }
 
    public static UsuallyStrEnum getType(String code) {
        return (UsuallyStrEnum) map.get(code);
    }
 
    public static UsuallyStrEnum[] getUsuallyString() {
        if (list == null) {
            int i = 0;
            Vector v = new Vector();
            java.util.Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                Object obj = it.next();
                UsuallyStrEnum type = (UsuallyStrEnum) map.get(obj);
                v.add(type);
            }
            list = new UsuallyStrEnum[v.size()];
            v.copyInto(list);
        }
        return list;
    }
 
    public int compareTo(Object obj) {
        UsuallyStrEnum type = (UsuallyStrEnum) obj;
        return this.getCode().compareTo(type.getCode());
    }
 
    public boolean equals(Object obj) {
        boolean ret = (obj != null) && (obj instanceof UsuallyStrEnum);
        if (ret) {
            UsuallyStrEnum type = (UsuallyStrEnum) obj;
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