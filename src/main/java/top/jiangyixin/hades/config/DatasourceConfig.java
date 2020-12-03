package top.jiangyixin.hades.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.jiangyixin.hades.common.HadesException;
import top.jiangyixin.hades.common.enums.DataSourceEnum;
import top.jiangyixin.hades.dao.GenerateDao;
import top.jiangyixin.hades.dao.MysqlGenerateDao;

import javax.annotation.Resource;

/**
 * @author jiangyixin
 */
@Configuration
public class DatasourceConfig {
	@Value("${hades.datasource}: mysql")
	private String datasource;
	@Resource
	private MysqlGenerateDao mysqlGenerateDao;

	@Bean
	public GenerateDao generateDao() {
		if (DataSourceEnum.MYSQL.getType().equals(datasource.toLowerCase())) {
			return mysqlGenerateDao;
		} else {
			throw new HadesException("不支持<" + datasource + ">数据源类型");
		}
	}
}
