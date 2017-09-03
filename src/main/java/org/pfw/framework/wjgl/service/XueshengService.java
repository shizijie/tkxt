package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.wjgl.domain.Xuesheng;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;



public interface XueshengService {

	Xuesheng getById(String id);
	
	Xuesheng getByXh(String xh);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Xuesheng> find(List<PropertyFilter> filters);
	
	void save(Xuesheng entity);
	
	void update(Xuesheng entity);

	void deleteById(String id);
	
	void delete(List<String> checks);

	List<Xuesheng> findAll();
	
	List<Xuesheng> findxsnotinbj(String bjid);
	
	List<Xuesheng> findxsinbj(String bjid);

	Xuesheng getByXm(String currentUserName);

	List<Xuesheng> getByIdStr(String selbjstr);


	
}
