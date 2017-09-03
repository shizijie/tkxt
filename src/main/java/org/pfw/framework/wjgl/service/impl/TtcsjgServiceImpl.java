package org.pfw.framework.wjgl.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.pfw.framework.wjgl.dao.BanjiDao;
import org.pfw.framework.wjgl.dao.TtcsjgDao;
import org.pfw.framework.wjgl.domain.Ttcsjg;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.service.BanjiService;
import org.pfw.framework.wjgl.service.TtcsjgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TtcsjgServiceImpl implements TtcsjgService {

	@Autowired
	private TtcsjgDao entityDao;

	@Transactional(readOnly=true)
	public Ttcsjg getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<Ttcsjg> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	public void save(Ttcsjg entity)
	{
		entityDao.save(entity);
	}
	
	public void update(Ttcsjg entity)
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
	public List<Ttcsjg> findAll() {
		return entityDao.find(" from Ttcsjg r order by r.zttime");
	}

	@Override
	public Ttcsjg findbyxh(String currentUserName,String ttid) {
		// TODO Auto-generated method stub
		return entityDao.findUnique(" from Ttcsjg r where r.ztr.xh=? and r.ztkm.id=?",currentUserName,ttid);
	}

	@Override
	public Page findstu(Page page, String bj,String name) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		hql.append("select distinct r.ztr from Ttcsjg r where 1=1 ");
		if(StringUtils.isNotEmpty(bj)){
			hql.append(" and r.ztr.bjid.id ='"+bj+"'");
		}
		if(StringUtils.isNotEmpty(name)){
			hql.append(" and r.ztr.xm like '%"+name+"%'");
		}
		return entityDao.findPage(page, hql.toString());
	}

	@Override
	public List<Ttcsjg> findbyid(String id) {
		// TODO Auto-generated method stub
		return entityDao.find(" from Ttcsjg r order by r.zttime where r.ztr.id=?",id);
	}

	@Override
	public List<Ttcsjg> getAllby(String bj, String kcid, String taoti) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		hql.append("from Ttcsjg r  where 1=1");
		if(StringUtils.isNotEmpty(bj)){
			hql.append(" and r.ztr.bjid.id ='"+bj+"'");
		}
		if(StringUtils.isNotEmpty(kcid)){
			hql.append(" and r.ztkm.fafl.id ='"+kcid+"'");
		}
		if(StringUtils.isNotEmpty(taoti)){
			hql.append(" and r.ztkm.id ='"+taoti+"'");
		}
		return entityDao.find(hql.toString());
	}


	
}
