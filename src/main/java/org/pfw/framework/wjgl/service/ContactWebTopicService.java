package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.wjgl.domain.ContactWebTopic;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.xtgl.domain.Dict;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;



public interface ContactWebTopicService {

	ContactWebTopic getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<ContactWebTopic> find(List<PropertyFilter> filters);
	
	void save(ContactWebTopic entity);
	
	void update(ContactWebTopic entity);

	void deleteById(String id);
	
	void delete(List<String> checks);

	List<ContactWebTopic> findAll();

	void update1(ContactWebTopic entity, String substring, String substring2);

	List<ContactWebTopic> randomTi(Twjfafl fl, String ndfb,
			String tms);
}
