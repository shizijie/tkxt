package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjyhctj;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;



public interface TwjyhctjService {

	Twjyhctj getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Twjyhctj> find(List<PropertyFilter> filters);
	
	List<Twjyhctj> getCtbyusrname(String loginName);
	
	void save(Twjyhctj entity);
	
	void save(String loginName,String wtid);
	
	void update(Twjyhctj entity);

	void deleteById(String id);
	
	void delete(List<String> checks);

	void delct(String id);

	String getNumber(Twjfafl tw);

	
}
