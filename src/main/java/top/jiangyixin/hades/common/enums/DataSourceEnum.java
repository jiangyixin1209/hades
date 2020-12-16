package top.jiangyixin.hades.common.enums;

/**
 * 数据源Enum
 * @author jiangyixin
 */
public enum DataSourceEnum {
	/**
	 * MySQL 数据库
	 */
	MYSQL("mysql");
	private final String type;

	DataSourceEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
