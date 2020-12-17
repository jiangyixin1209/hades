package top.jiangyixin.hades.common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/17 下午4:12
 */
public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 当前页码
	 */
	private int page;
	/**
	 * 每页条数
	 */
	private int limit;
	public Query(Map<String, Object> params) {
		this.putAll(params);
		this.page = Integer.parseInt(params.get("page").toString());
		this.limit = Integer.parseInt(params.get("limit").toString());
		this.put("offset", (page - 1) * limit);
		this.put("page", page);
		this.put("limit", limit);
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getLimit() {
		return limit;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	@Override
	public String toString() {
		return "PageQuery{" +
				"page=" + page +
				", limit=" + limit +
				'}';
	}
}
