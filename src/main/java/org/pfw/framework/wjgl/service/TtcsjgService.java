package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.wjgl.domain.Ttcsjg;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;



public interface TtcsjgService {

	Ttcsjg getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Ttcsjg> find(List<PropertyFilter> filters);
	
	void save(Ttcsjg entity);
	
	void update(Ttcsjg entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	List<Ttcsjg> findAll();

	Ttcsjg findbyxh(String currentUserName,String ttid);

	Page findstu(Page page, String bj, String name);

	List<Ttcsjg> findbyid(String id);

	List<Ttcsjg> getAllby(String bj, String kcid, String taoti);

	
}
