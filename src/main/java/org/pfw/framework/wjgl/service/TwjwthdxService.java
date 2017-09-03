package org.pfw.framework.wjgl.service;

import java.util.List;
import org.pfw.framework.wjgl.domain.Twjwthdx;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;

public interface TwjwthdxService {
	Twjwthdx getById(String id);

	void deleteById(String id);

	Page findPage(Page page, List<PropertyFilter> filters);

	void save(Twjwthdx entity);
	void update(Twjwthdx entity);

	boolean exists(String attName,String value);

	void delete(List<String> checks);

	List<Twjwthdx> listByType(String type);
	
	List<Twjwthdx> findAll();
}
