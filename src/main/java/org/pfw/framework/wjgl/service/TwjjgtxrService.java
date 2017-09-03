package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.domain.security.User;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.domain.Csrw;
import org.pfw.framework.wjgl.domain.Twjjgtxr;

public interface TwjjgtxrService {
	
	Twjjgtxr getById(String id);

	void deleteById(String id);
	
	void deleteByCSRWid(String rwid,String name);

	Page findPage(Page page, List<PropertyFilter> filters);

	void save(Twjjgtxr entity);
	
	void update(Twjjgtxr entity);

	void delete(List<String> checks);
	
	String getZdfByTxr(Twjjgtxr entity);
	
	List<Twjjgtxr> getByLoginName(String loginName);

	Twjjgtxr getRecord(User usr, Csrw csrw);

}
