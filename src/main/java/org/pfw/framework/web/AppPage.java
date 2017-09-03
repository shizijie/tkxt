package org.pfw.framework.web;

import org.pfw.framework.modules.orm.Page;

public class AppPage extends Page {
	
	public void setPageNo(int pageNo) {

		if (pageNo < 1)
			this.pageNo = 1;
		else
			this.pageNo = pageNo;
		
	}
	
	public void setPageSize(final int pageSize) 
	{
		this.pageSize = pageSize;
	}
	
}
