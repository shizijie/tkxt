package org.pfw.framework.wjgl.service.impl;

import java.util.List;

import org.pfw.framework.service.security.UserService;
import org.pfw.framework.wjgl.dao.TwjyhctjDao;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.wjgl.domain.Twjyhctj;
import org.pfw.framework.domain.security.User;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.service.TwjwtService;
import org.pfw.framework.wjgl.service.TwjyhctjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TwjyhctjServiceImpl implements TwjyhctjService {

	@Autowired
	private TwjyhctjDao entityDao;
	@Autowired
	private UserService usrService;;
	@Autowired
	private TwjwtService wtService;

	@Transactional(readOnly=true)
	public Twjyhctj getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<Twjyhctj> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	public void save(Twjyhctj entity)
	{
		entityDao.save(entity);
	}
	
	public void update(Twjyhctj entity)
	{
		entityDao.update(entity);
	}

	public void deleteById(String id)
	{
		entityDao.delete(id);
	}
	
	public void delete(List<String> checks)
	{
		if(checks!=null&&checks.size()>0){
			for (String id : checks) {
				entityDao.delete(id);
			}
		}
	}

	@Override
	public void save(String loginName, String wtid) {
		User usr = usrService.getUserByLoginName(loginName);
		Twjwt wt = wtService.getById(wtid);
		Twjyhctj yhct = new Twjyhctj();
		yhct.setUser(usr);
		yhct.setCwwt(wt);
		save(yhct);
	}

	@Override
	public List<Twjyhctj> getCtbyusrname(String loginName) {
		
		return entityDao.getSession().createQuery("select r.cwwt from Twjyhctj r where r.user.loginName = '"+loginName+"'").list();
		
	}

	@Override
	public void delct(String id) {
		// TODO Auto-generated method stub
		entityDao.getSession().createQuery("delete from Twjyhctj r where r.cwwt.id= '"+id+"' ").executeUpdate();
	}
	
	/**
	 * 根据课程计算错题集数量
	 */
	@Override
	public String getNumber(Twjfafl tw) {
		// TODO Auto-generated method stub
		StringBuffer hql =new StringBuffer();
		hql.append(" select count(c) from Twjyhctj c where c.cwwt.faflid.fparent.id ='"+tw.getId()+"'");
		System.out.println(entityDao.findUnique(hql.toString()));
		return entityDao.findUnique(hql.toString())+"";
	}

	
}
