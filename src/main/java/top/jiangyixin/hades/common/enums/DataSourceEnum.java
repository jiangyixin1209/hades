package top.jiangyixin.hades.common.enums;

/**
 * @author jiangyixin
 */

public enum DataSourceEnum {
	/**
	 * MySQL 数据库
	 */
	MYSQL("mysql");
	private String type;

	DataSourceEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
