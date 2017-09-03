package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.wjgl.domain.JxType;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;



public interface JxTypeService {

	JxType getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<JxType> find(List<PropertyFilter> filters);
	
	void save(JxType entity);
	
	void update(JxType entity);

	void deleteById(String id);
	
	void delete(List<String> checks);

	List<JxType> findAll();

	List<JxType> getParent();

	List<JxType> getListById(String type);
	
	/**
	 * 获取所有3节点
	 * @author  xl 
	 * @date 创建时间：2017年4月24日 下午2:37:02 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	List<JxType> getAllThree();
}
