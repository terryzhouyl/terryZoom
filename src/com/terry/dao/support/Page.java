package com.terry.dao.support;


import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *  用来在分页查询，其作用是将当前查询的某一页的信息，
 *  如上一页、下一页、总共多少页等信息存储下来，并提供友好的接口给用户
 *  另外，也提供了转载用户序列化对象的把手：userObject
 * </pre>
 * 
 * @param <T>
 */
public class Page<T> implements Serializable {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 3258689914320533045L;

	/**
	 * 当前页的起始点
	 */
	private int offset;

	/**
	 * 设定的页面数目
	 */
	private int pageSize;

	/**
	 * 取得的实际数目
	 */
	private int realSize;

	/**
	 * 该查询总共可返回的数目
	 */
	private int total;
	
	/**
	 * 总页数
	 */
	private int totalPage;

	/**
	 * 数据列表
	 */

	private List<T> rows;

	public Page() {
		this.offset = 0;
		this.pageSize = 10;
	}

	/**
	 * @param offset
	 * @param pageSize
	 */
	public Page(int offset, int pageSize) {
		this.offset = offset;
		this.pageSize = pageSize;
	}

	/**
	 * @param offset
	 *            偏移量
	 * @param pageSize
	 *            每页大小
	 * @param total
	 *            总共大小
	 * @param rows
	 *            取得的当页的对象
	 */
	public Page(int offset, int pageSize, int total, List<T> rows) {

		this.offset = offset;
		this.pageSize = pageSize;
		this.total = total;
		this.rows = rows;
		this.realSize = rows == null ? 0 : rows.size();		
	}

	/**
	 * @return int
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return int
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return int
	 */
	public int getRealSize() {
		int currentPageSize = pageSize;
		if (currentPageSize == -1) {
			currentPageSize = total;
		}
		if ((offset + currentPageSize) > total) {
			realSize = total - offset;
		} else {
			realSize = currentPageSize; 
		}
		return realSize;
	}

	/**
	 * @return int
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the rows
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	/**
	 * @param realSize
	 *            the realSize to set
	 */
	public void setRealSize(int realSize) {
		this.realSize = realSize;
	}

	/**
	 * @return int
	 */
	public int nextOffset() {
		int nextOffset = offset + pageSize;
		return nextOffset;
	}

	/**
	 * @return int
	 */
	public int prevOffset() {
		int prevOffset = offset - pageSize;
		return prevOffset;
	}

	/**
	 * 
	 * @param totalPage
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	/**
	 * @return int 返回总页数
	 */
	public int getTotalPage() {
		//int totalPage;
		if (total % pageSize == 0) {
			totalPage = total / pageSize;
		} else {
			totalPage = (total / pageSize) + 1;
		}
		return totalPage;
	}

	/**
	 * @return int
	 */
	public int getCurrentPage() {
		return pageSize <= 0 ? 1 : (offset / pageSize) + 1;
	}
}
