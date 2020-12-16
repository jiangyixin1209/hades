package top.jiangyixin.hades.entity;

/**
 * 列属性
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/16 下午1:55
 */
public class Column {
	/**
	 * 列名
	 */
	private String columnName;
	/**
	 * 列名类型
	 */
	private String dataType;
	/**
	 * 备注
	 */
	private String comment;
	/**
	 * 属性名称(第一个字母小写)，如：user_name => userName
	 */
	private String lowAttrName;
	/**
	 * 属性名称(第一个字母大写)，如：user_name => UserName
	 */
	private String upAttrName;
	/**
	 * 属性类型
	 */
	private String attrType;
	/**
	 * 额外信息，例如 auto_increment
	 */
	private String extra;
	
	public String getColumnName() {
		return columnName;
	}
	
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public String getDataType() {
		return dataType;
	}
	
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getLowAttrName() {
		return lowAttrName;
	}
	
	public void setLowAttrName(String lowAttrName) {
		this.lowAttrName = lowAttrName;
	}
	
	public String getUpAttrName() {
		return upAttrName;
	}
	
	public void setUpAttrName(String upAttrName) {
		this.upAttrName = upAttrName;
	}
	
	public String getAttrType() {
		return attrType;
	}
	
	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}
	
	public String getExtra() {
		return extra;
	}
	
	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	@Override
	public String toString() {
		return "Column{" +
				"columnName='" + columnName + '\'' +
				", dataType='" + dataType + '\'' +
				", comment='" + comment + '\'' +
				", lowAttrName='" + lowAttrName + '\'' +
				", upAttrName='" + upAttrName + '\'' +
				", attrType='" + attrType + '\'' +
				", extra='" + extra + '\'' +
				'}';
	}
}
