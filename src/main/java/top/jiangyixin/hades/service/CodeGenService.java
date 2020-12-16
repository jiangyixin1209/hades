package top.jiangyixin.hades.service;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import top.jiangyixin.hades.common.TemplateConstant;
import top.jiangyixin.hades.entity.Column;
import top.jiangyixin.hades.entity.Table;
import top.jiangyixin.hades.exception.HadesException;
import top.jiangyixin.hades.util.DateUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
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
	
	/**
	 * 生成代码
	 * @param tableInfo         表信息
	 * @param columnsInfo       列信息
	 * @param zipOutputStream   压缩输出流
	 */
	public static void generateCode(Map<String, String> tableInfo,
	                                List<Map<String, String>> columnsInfo,
	                                ZipOutputStream zipOutputStream) {
		Configuration configuration = getConfiguration();
		Table table = loadSingleTable(configuration, tableInfo, columnsInfo);
		initVelocity();
		VelocityContext context = loadTemplateContext(configuration, table);
		
		for (String template : getTemplates()) {
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF_8");
			tpl.merge(context, sw);
			try{
				String filename = getFilename(
						template, table.getUpClassName(),
						configuration.getString("package"),
						configuration.getString("moduleName"));
				zipOutputStream.putNextEntry(new ZipEntry(filename));
			} catch (IOException e) {
				throw new HadesException("渲染模板失败，表: " + table.getTableName(), e);
			}
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
	
	/**
	 * 初试化velocity，设置velocity资源加载器
	 */
	public static void initVelocity() {
		Properties properties = new Properties();
		properties.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(properties);
	}
	
	public static String getFilename(String template, String className, String packageName, String moduleName) {
		String packagePath = "main" + File.separator + "java" + File.separator;
		if (StringUtils.isNotBlank(packageName)) {
			packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
		}
		if (template.contains(TemplateConstant.ENTITY_TEMPLATE)) {
			return packagePath + "entity" + File.separator + className + "Entity.java";
		}
		if (template.contains(TemplateConstant.DAO_TEMPLATE)) {
			return packagePath + "dao" + File.separator + className + "DAO.java";
		}
		if (template.contains(TemplateConstant.SERVICE_TEMPLATE)) {
			return packagePath + "service" + File.separator + className + "Service.java";
		}
		if (template.contains(TemplateConstant.SERVICE_IMPL_TEMPLATE)) {
			return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
		}
		if (template.contains(TemplateConstant.CONTROLLER_TEMPLATE)) {
			return packagePath + "controller" + File.separator + className + "Controller.java";
		}
		if (template.contains(TemplateConstant.DAO_XML_TEMPLATE)) {
			return "main" + File.separator + "resources" + File.separator + "mapper" +
					File.separator + moduleName + File.separator + className + "DAO.xml";
		}
		return null;
	}
	
	/**
	 * 加载单表对象
	 * @param configuration     配置
	 * @param tableInfo         表信息
	 * @param columnsInfo       列信息
	 * @return                  Table
	 */
	public static Table loadSingleTable(Configuration configuration,
	                                    Map<String, String> tableInfo,
	                                    List<Map<String, String>> columnsInfo) {
		boolean hasBigDecimal = false;
		Table table = new Table();
		table.setTableName(tableInfo.get("tableName"));
		table.setComment(tableInfo.get("tableComment"));
		String[] tablePrefixArray = configuration.getString("tablePrefix").split(",");
		String className = tableToJava(table.getTableName(), tablePrefixArray);
		table.setUpClassName(className);
		table.setLowClassName(StringUtils.uncapitalize(className));
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
		return table;
	}
	
	/**
	 * 封装模板上下文
	 * @param configuration     配置对象
	 * @param table             表对象
	 * @return                  VelocityContext
	 */
	public static VelocityContext loadTemplateContext(Configuration configuration, Table table) {
		Map<String, Object> templateData = new HashMap<>();
		templateData.put("tableName", table.getTableName());
		templateData.put("comment", table.getComment());
		templateData.put("pk", table.getPk());
		templateData.put("lowClassName", table.getLowClassName());
		templateData.put("upClassName", table.getUpClassName());
		templateData.put("datetime", DateUtils.format(new Date(), DateUtils.DATETIME_PATTERN));
		templateData.put("packageName", configuration.getString("package"));
		templateData.put("moduleName", configuration.getString("module"));
		templateData.put("author", configuration.getString("author"));
		templateData.put("email", configuration.getString("email"));
		return new VelocityContext(templateData);
	}
	
}
