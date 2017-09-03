package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.domain.ContactWebTopic;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjwt;

public interface TwjfaflService {
	Twjfafl getById(int id);
	
	void deleteById(int id);

	Page findPage(Page page, List<PropertyFilter> filters);

	void save(Twjfafl entity);
	void update(Twjfafl entity);

	boolean exists(String attName,String value);

	void delete(List<String> checks);

	List<Twjfafl> listByType(String type);
	
	List<Twjfafl> findAll();
	
	List<Twjfafl> findFparent();
	
	public List<Twjfafl> findFparentByBz();
	
	List<Twjfafl> findByFlmc(String flmc);

	String geiallfl();

	List<Twjfafl> findkc();
	
	List<Twjfafl> getByParentid( int parentid);
	
	/**
	 * 根据课程获取所有知识点
	 * @author  xl 
	 * @date 创建时间：2017年4月14日 下午5:49:28 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	List<Twjfafl> getALLKnow(Twjfafl tw);

	Twjfafl getByName(String string,Twjwt wt);

	Twjfafl getByName1(String string, ContactWebTopic byId);
	
	
	
}
