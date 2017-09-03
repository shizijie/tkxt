package org.pfw.framework.wjgl.service;

import java.util.List;
import java.util.Map;

import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjjg;
import org.pfw.framework.wjgl.domain.Twjwt;

public interface TwjjgService {
	
	Twjjg getById(String id);
	
	List<Twjjg> getByTxrAndPc(String txrid,String pcid);
	
	void deleteById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	void save(Twjjg entity);
	
	void save(List<Twjjg> entity);

	void update(Twjjg entity);

	void delete(List<String> checks);
	
	String getTaskResultScore(Twjjg entity) throws Exception;

	List getdttjls(String flidstr);
	
	List<Twjjg> getWtBytxrid(String txrid);
	
	List<Twjjg> getWjjglsBytxrid(String txrid);
	
	List bjtjdj(String ksrq,String jsrq,String bj);

}
