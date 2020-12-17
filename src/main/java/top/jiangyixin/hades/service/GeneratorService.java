package top.jiangyixin.hades.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jiangyixin.hades.common.PageResult;
import top.jiangyixin.hades.common.Query;
import top.jiangyixin.hades.dao.GenerateDao;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @author 代码生成Service
 */
@Service
public class GeneratorService {
	private final GenerateDao generateDao;
	
	@Autowired
	public GeneratorService(GenerateDao generateDao) {
		this.generateDao = generateDao;
	}
	
	public PageResult queryTableList(Query query) {
		Page<?> page = PageHelper.startPage(query.getPage(), query.getLimit());
		List<Map<String, Object>> tableList = generateDao.queryTableList(query);
		int total = (int) page.getTotal();
		return new PageResult(tableList, total, query.getPage(), query.getLimit());
	}
	
	public Map<String, String> queryTable(String tableName) {
		return generateDao.queryTable(tableName);
	}
	
	public List<Map<String, String>> queryColumns(String tableName) {
		return generateDao.queryColumns(tableName);
	}
	
	/**
	 * 生成代码
	 * @param tableNames    表名列表
	 * @return              代码压缩包文件字节数组
	 */
	public byte[] generateCode(String[] tableNames) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
	    for (String tableName : tableNames) {
	       Map<String, String> tableInfo = queryTable(tableName);
	       List<Map<String, String>> columnsInfo = queryColumns(tableName);
	       CodeGenService.generateCode(tableInfo, columnsInfo, zipOutputStream);
 	    }
		IOUtils.closeQuietly(zipOutputStream);
	    return byteArrayOutputStream.toByteArray();
	}
}
