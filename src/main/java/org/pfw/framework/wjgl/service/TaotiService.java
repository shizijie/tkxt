package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.wjgl.domain.Taoti;
import org.pfw.framework.xtgl.domain.Dict;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;



public interface TaotiService {

	Taoti getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Taoti> find(List<PropertyFilter> filters);
	
	void save(Taoti entity);
	
	void update(Taoti entity);

	void deleteById(String id);
	
	void delete(List<String> checks);

	List<Taoti> findAll();

	List<Taoti> getttlist(String flid);
	
	Page findJxPage(Page page, List<String> slist) ;

	Taoti getByName(String mc);


}
