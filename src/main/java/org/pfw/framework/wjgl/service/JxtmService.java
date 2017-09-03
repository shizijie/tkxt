package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.domain.Jxtm;



public interface JxtmService {

	Jxtm getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Jxtm> find(List<PropertyFilter> filters);
	
	void save(Jxtm entity);
	
	void update(Jxtm entity);

	void deleteById(String id);
	
	void delete(List<String> checks);

	List<Jxtm> findAll();
	
	List<Jxtm> randomNum(String txid,String number);
	
	List<Jxtm> randomNum1(String txid,String number);
	
	List<Jxtm> findByTxAndZjid(String txid,String zjid);

	List<Jxtm> getByTypeId(String string);

	String countNum(String id);
	
}
