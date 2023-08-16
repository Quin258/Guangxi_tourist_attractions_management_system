package com.ncvt.utils;

import java.util.List;

/**
 * 分页
 * @author sigur
 *
 */
public class Page {
	//记录sql中limit x,y中的x，默认从第1行即下标为0开始
	private int limitIndex = 0;
	private int pageIndex = 0;//第几页，当前页
	
	private int pageSize = 15;//每页大小
	//记录总的行数
	private int totalRow = 0 ;
	//记录总页数，一般根据总行数（totalRow）、分页大小来计算
	private int totalPage =0;
	//获取数据库数据
	private List rows;
	
	
	
	
	public Page(int pageIndex, int totalRow) {
		super();


		this.limitIndex = (pageIndex-1)*pageSize;//计算数据库表中的下标
		
		//计算总页数，每页显示5条记录
//		totalPage = totalRow%pageSize==0?totalRow%pageSize:totalRow/pageSize+1;
		this.totalPage = (totalRow+pageSize-1)/pageSize;//最大余数法
		
		if(this.limitIndex >= totalRow) {
			//如果达到最大的记录录，则直接最后一页
			limitIndex = (pageIndex-2)*pageIndex;
			this.pageIndex = totalPage;//在最后一页时，点下一页还是显示最后一页。总页数即最后一页
		}else if(limitIndex <= 0) {
			//如果是到第一页还往前点，则一直是0
			this.limitIndex = 0;
			this.pageIndex = 1;//给前端传输一个默认值，在第一页时，再怎么点上一页永远显示第一页
		
		}else {
			this.pageIndex = pageIndex;
		}
		
	}

	
	
	/**
	 * @return the limitIndex
	 */
	public int getLimitIndex() {
		return limitIndex;
	}



	/**
	 * @param limitIndex the limitIndex to set
	 */
	public void setLimitIndex(int limitIndex) {
		this.limitIndex = limitIndex;
	}



	/**
	 * @return the totalRow
	 */
	public int getTotalRow() {
		return totalRow;
	}
	/**
	 * @param totalRow the totalRow to set
	 */
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}
	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	/**
	 * @return the rows
	 */
	public List getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List rows) {
		this.rows = rows;
	}
	/**
	 * @return the pageIndex
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
}
