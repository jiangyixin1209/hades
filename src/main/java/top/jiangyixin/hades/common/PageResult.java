package top.jiangyixin.hades.common;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/17 下午4:21
 */
public class PageResult implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 总记录条数
	 */
	private int totalCount;
	/**
	 * 每页记录数
	 */
	private int pageSize;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 当前页数
	 */
	private int currentPage;
	/**
	 * 列表数据
	 */
	private List<?>list;
	
	public PageResult(List<?> list, int totalCount, int currentPage, int pageSize) {
		this.list = list;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public List<?> getList() {
		return list;
	}
	
	public void setList(List<?> list) {
		this.list = list;
	}
	
	
}
