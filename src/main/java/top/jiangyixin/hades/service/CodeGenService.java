package top.jiangyixin.hades.service;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import top.jiangyixin.hades.entity.Column;
import top.jiangyixin.hades.entity.Table;
import top.jiangyixin.hades.exception.HadesException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成 Service 类
 *
 * @author jiangyixin
 * @version 1.0
 * @date 2020/12/16 上午11:30
 */
public class CodeGenService {

	/**
	 * 加载生成所需的模板
	 * @author          jiangyixin
	 * @return          模板列表
	 */
	public static List<String> getTemplates() {
		List<String> templates = new ArrayList<>(10);
		templates.add("template/Entity.java.vm");
		templates.add("template/Dao.java.vm");
		templates.add("template/Dao.xml.vm");
		templates.add("template/Service.java.vm");
		templates.add("template/ServiceImpl.java.vm");
		templates.add("template/Controller.java.vm");
		return templates;
	}
	
	public static void generateCode(Map<String, String> tableInfo,
	                                List<Map<String, String>> columnsInfo,
	                                ZipOutputStream zipOutputStream) {
		Configuration configuration = getConfiguration();
		boolean hasBigDecimal = false;
		boolean hasList = false;
		
		Table table = new Table();
		table.setTableName(tableInfo.get("tableName"));
		table.setComment(tableInfo.get("tableComment"));
		String[] tablePrefixArray = configuration.getString("tablePrefix").split(",");
		String clazzName = tableToJava(table.getTableName(), tablePrefixArray);
		table.setUpClassName(clazzName);
		table.setLowClassName(StringUtils.uncapitalize(clazzName));
		List<Column> columns = new ArrayList<>();
		for (Map<String, String> columnInfo: columnsInfo) {
			Column column = new Column();
			column.setColumnName(columnInfo.get("columnName"));
			column.setDataType(columnInfo.get("dataType"));
			column.setComment(columnInfo.get("columnComment"));
			column.setExtra(columnInfo.get("extra"));
			// 列名转Java属性名
			column.setUpAttrName(columnToJava(column.getColumnName()));
			column.setLowAttrName(StringUtils.uncapitalize(column.getUpAttrName()));
			// 列数据类型转Java类型
			String attrType = configuration.getString(column.getDataType().toLowerCase(),
					columnToJava(column.getDataType()));
			column.setAttrType(attrType);
			
			if (!hasBigDecimal && "BigDecimal".equals(attrType)) {
				hasBigDecimal = true;
			}
			
			// 设置主键
			if ("PRI".equalsIgnoreCase(columnInfo.get("columnKey")) && table.getPk() == null) {
				table.setPk(column);
			}
			columns.add(column);
		}
		table.setColumns(columns);
		
		// 如果未设置主键则默认为第一个字段
		if (table.getPk() == null) {
			table.setPk(table.getColumns().get(0));
		}
		
	}
	
	public static Configuration getConfiguration() {
		return getConfiguration("hades.properties");
	}
	
	/**
	 * 获取配置信息
	 * @param propertiesName    配置文件名称
	 * @return                  Configuration
	 */
	public static Configuration getConfiguration(String propertiesName) {
		try{
			return new PropertiesConfiguration(propertiesName);
		} catch (ConfigurationException e) {
			throw new HadesException("获取配置文件失败", e);
		}
	}
	
	/**
	 * 列名转为Java属性名
	 *  sys_user -> Sys_User -> SysUser
	 * @param columnName        列名
	 * @return                  Java属性名
	 */
	public static String columnToJava(String columnName) {
		return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
	}
	
	/**
	 * 表名转Java类名
	 * @param tableName         类名
	 * @param tablePrefixArray  表前缀数组
	 * @return                  Java类名
	 */
	public static String tableToJava(String tableName, String[] tablePrefixArray) {
		if (tablePrefixArray != null && tablePrefixArray.length > 0) {
			for (String tablePrefix : tablePrefixArray) {
			    if (tableName.startsWith(tablePrefix)) {
			    	// 去除表前缀
			    	tableName = tableName.replaceFirst(tablePrefix, "");
			    }
			}
		}
		return columnToJava(tableName);
	}
	
	public static v

	  public static void main(String[] args) {
	     System.out.println(columnToJava("sys_user"));
	  }
}
