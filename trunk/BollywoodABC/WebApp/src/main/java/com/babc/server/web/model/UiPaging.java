package com.babc.server.web.model;

import com.babc.server.AppConstants;

public class UiPaging {
	
	private int previousPageNo;
	private int startRecCount;
	private int endRecCount;
	private int totalRecCount;
	private int nextPageNo;
	
	public UiPaging() {
	}

	public UiPaging(int previousPageNo, int startRecCount, int endRecCount,
			int totalRecCount, int nextPageNo) {
		this.previousPageNo = previousPageNo;
		this.startRecCount = startRecCount;
		this.endRecCount = endRecCount;
		this.totalRecCount = totalRecCount;
		this.nextPageNo = nextPageNo;
	}
	
	public void setStoryUiPaging(int currentPageNo, int noOfRecPerPg, int totalNoOfRecs){
		
		this.previousPageNo = currentPageNo - 1;
		int totalNoOfPages = totalNoOfRecs/noOfRecPerPg;
		this.totalRecCount = totalNoOfRecs;
		
		if (currentPageNo == 1){
			this.startRecCount = AppConstants.noOfStoriesOnFirstPage + 1;
			this.endRecCount = this.startRecCount + noOfRecPerPg;
			this.nextPageNo = currentPageNo + 1;
		}
		else if (currentPageNo == totalNoOfPages){
			this.startRecCount = ((currentPageNo-1)*noOfRecPerPg)+(AppConstants.noOfStoriesOnFirstPage + 1);
			this.endRecCount = this.startRecCount + noOfRecPerPg;
			this.nextPageNo = 0;
		}
		else{
			this.startRecCount = ((currentPageNo-1)*noOfRecPerPg)+(AppConstants.noOfStoriesOnFirstPage + 1);
			this.endRecCount = this.startRecCount + noOfRecPerPg;
			this.nextPageNo = currentPageNo + 1;
		}
	}

	public int getPreviousPageNo() {
		return previousPageNo;
	}

	public int getStartRecCount() {
		return startRecCount;
	}

	public int getEndRecCount() {
		return endRecCount;
	}

	public int getTotalRecCount() {
		return totalRecCount;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UiPaging [endRecCount=");
		builder.append(endRecCount);
		builder.append(", nextPageNo=");
		builder.append(nextPageNo);
		builder.append(", previousPageNo=");
		builder.append(previousPageNo);
		builder.append(", startRecCount=");
		builder.append(startRecCount);
		builder.append(", totalRecCount=");
		builder.append(totalRecCount);
		builder.append("]");
		return builder.toString();
	}
}
