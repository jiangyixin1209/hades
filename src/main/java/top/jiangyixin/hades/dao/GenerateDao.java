package top.jiangyixin.hades.dao;

import java.util.List;
import java.util.Map;

/**
 * @author jiangyixin
 */
public interface GenerateDao {
	/**
	 * 查询表列表
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> queryTableList(Map<String, Object> param);

	/**
	 * 查询具体表信息
	 * @param tableName     表名
	 * @return              表信息
	 */
	Map<String, String> queryTable(String tableName);

	/**
	 * 查询某个表的所有字段信息
	 * @param tableName     表名
	 * @return              字段信息列表
	 */
	List<Map<String, String>> queryColumns(String tableName);

}
