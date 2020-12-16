package top.jiangyixin.hades.entity;

import java.util.List;

/**
 * 表信息
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/16 下午2:04
 */
public class Table {
	/**
	 * 表名称
	 */
	private String tableName;
	/**
	 * 备注
	 */
	private String comment;
	/**
	 * 主键
	 */
	private Column pk;
	/**
	 * 列名称
	 */
	private List<Column> columns;
	/**
	 * 类名(第一个字母大写)，如：sys_user => SysUser
	 */
	private String upClassName;
	/**
	 * 类名(第一个字母小写)，如：sys_user => sysUser
	 */
	private String lowClassName;
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Column getPk() {
		return pk;
	}
	
	public void setPk(Column pk) {
		this.pk = pk;
	}
	
	public List<Column> getColumns() {
		return columns;
	}
	
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	public String getUpClassName() {
		return upClassName;
	}
	
	public void setUpClassName(String upClassName) {
		this.upClassName = upClassName;
	}
	
	public String getLowClassName() {
		return lowClassName;
	}
	
	public void setLowClassName(String lowClassName) {
		this.lowClassName = lowClassName;
	}
	
	@Override
	public String toString() {
		return "Table{" +
				"tableName='" + tableName + '\'' +
				", comment='" + comment + '\'' +
				", pk=" + pk +
				", columns=" + columns +
				", upClassName='" + upClassName + '\'' +
				", lowClassName='" + lowClassName + '\'' +
				'}';
	}
}
