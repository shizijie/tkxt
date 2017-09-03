package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.wjgl.domain.Twjlsyj;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;



public interface TwjlsyjService {

	Twjlsyj getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Twjlsyj> find(List<PropertyFilter> filters);
	
	void save(Twjlsyj entity);
	
	void update(Twjlsyj entity);

	void deleteById(String id);
	
	void delete(List<String> checks);

	
}
