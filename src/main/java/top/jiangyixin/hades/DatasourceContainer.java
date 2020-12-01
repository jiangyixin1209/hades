package top.jiangyixin.hades;

import org.apache.ibatis.session.SqlSessionFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author jiangyixin
 */
public class DatasourceContainer {

	private final ConcurrentMap<String, SqlSessionFactory> dbContainer = new ConcurrentHashMap<>();



}
