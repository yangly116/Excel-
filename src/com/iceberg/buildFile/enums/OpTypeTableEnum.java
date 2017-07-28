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
public class OpTypeTableEnum implements Serializable {
    private String code;
    private String text;
    private static java.util.TreeMap map = new TreeMap();
    private static OpTypeTableEnum[] list = null;
 
    private OpTypeTableEnum(String code, String text) {
        this.code = code;
        this.text = text;
        map.put(this.code, this);
    }
 
    public static OpTypeTableEnum NEW_TABLE_1 = new OpTypeTableEnum("新建表", "1");
    public static OpTypeTableEnum NEW_FIELD_2 = new OpTypeTableEnum("新建字段", "2");
    public static OpTypeTableEnum FDLK_3 = new OpTypeTableEnum("数据平台type", "3");

 
    public String getCode() {
        return code;
    }
 
    public String getText() {
        return text;
    }
 
    public static OpTypeTableEnum getType(String code) {
        return (OpTypeTableEnum) map.get(code);
    }
 
    public static OpTypeTableEnum[] getUsuallyString() {
        if (list == null) {
            int i = 0;
            Vector v = new Vector();
            java.util.Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                Object obj = it.next();
                OpTypeTableEnum type = (OpTypeTableEnum) map.get(obj);
                v.add(type);
            }
            list = new OpTypeTableEnum[v.size()];
            v.copyInto(list);
        }
        return list;
    }
 
    public int compareTo(Object obj) {
        OpTypeTableEnum type = (OpTypeTableEnum) obj;
        return this.getCode().compareTo(type.getCode());
    }
 
    public boolean equals(Object obj) {
        boolean ret = (obj != null) && (obj instanceof OpTypeTableEnum);
        if (ret) {
            OpTypeTableEnum type = (OpTypeTableEnum) obj;
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