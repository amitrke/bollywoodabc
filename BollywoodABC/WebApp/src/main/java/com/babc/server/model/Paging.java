package com.babc.server.model;

import com.babc.server.AppConstants;

/**
 * @author amit
 *
 */
public class Paging {

	private int limit = AppConstants.DATA_DEFAULT_LIMIT;
	private int offset = 0;
	private SortOrder sortOrder = SortOrder.desc;
	private String sortColumn = "id";

	public Paging() {
	}

	public Paging(int limit, int offset, SortOrder sortOrder) {
		this.limit = limit;
		this.offset = offset;
		this.sortOrder = sortOrder;
	}

	public Paging(int limit, int offset) {
		this.limit = limit;
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

}
