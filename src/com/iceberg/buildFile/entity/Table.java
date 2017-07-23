package com.iceberg.buildFile.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 杨文培
 *
 */
public class Table {
	/**表名*/
	private String tableName;
	/**表描述*/
	private String tableComment;
	/**主键*/
	private String pk;
	/**外键*/
	private Set<String> fks;
	/**序列*/
	private String seq;
	/**字段*/
	private List<String> fields;
	/**字段对象集合*/
	private Map<String, Field> fieldMap;
	/**操作类型*/
	private String opType;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public Set<String> getFks() {
		return fks;
	}
	public void setFks(Set<String> fks) {
		this.fks = fks;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	public String getTableComment() {
		return tableComment;
	}
	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	public Map<String, Field> getFieldMap() {
		return fieldMap;
	}
	public void setFieldMap(Map<String, Field> fieldMap) {
		this.fieldMap = fieldMap;
	}
	
	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	@Override
	public String toString() {
		return "Table [tableName=" + tableName + ", tableComment=" + tableComment + ", pk=" + pk + ", fks=" + fks
				+ ", seq=" + seq + ", fields=" + fields + ", fieldMap=" + fieldMap + "]";
	}
	
}
