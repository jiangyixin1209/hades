package top.jiangyixin.hades.dao;

import java.util.List;
import java.util.Map;

/**
 * @author jiangyixin
 */
public interface GenerateDao {
	List<Map<String, Object>> queryTableList(Map<String, Object> param);
	Map<String, String> queryTable(String tableName);
	List<Map<String, String>> queryColumnsByTable(String tableName);

}
