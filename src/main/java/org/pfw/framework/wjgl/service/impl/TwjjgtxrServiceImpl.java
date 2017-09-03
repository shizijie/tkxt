package org.pfw.framework.wjgl.service.impl;

import java.util.List;

import org.pfw.framework.domain.security.User;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.dao.TwjjgtxrDao;
import org.pfw.framework.wjgl.domain.Csrw;
import org.pfw.framework.wjgl.domain.Twjjgtxr;
import org.pfw.framework.wjgl.service.TwjjgService;
import org.pfw.framework.wjgl.service.TwjjgtxrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TwjjgtxrServiceImpl implements TwjjgtxrService {
	@Autowired
	private TwjjgtxrDao entitydao;
	@Autowired
	private TwjjgService wjjgservice;
	
	public void deleteById(String id) {
		entitydao.delete(id);
	}

	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entitydao.findPage(page, filters);
	}

	public void save(Twjjgtxr entity) {
		entitydao.save(entity);
	}

	public void delete(List<String> checks) {
		if(checks!=null&&checks.size()>0){
			for (String tmpid : checks) {
				entitydao.delete(tmpid);
			}
		}
	}

	public void update(Twjjgtxr entity) {
		entitydao.update(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List findAll() {
		List ls = entitydao.getSession().createQuery("from Twjjgtxr").list();
		return ls;
	}

	@Override
	public Twjjgtxr getById(String id) {
		return entitydao.get(id);
	}

	@Override
	public String getZdfByTxr(Twjjgtxr entity) {
		
		List ls = entitydao.getSession().createQuery("select sum((case when a.df2 is null then cast(a.df as int) else cast(a.df2 as int) end)) "
													+"from Twjjg a where a.txrid.id = '"+entity.getId()+"'").list();
		
		return ls.get(0).toString();
	}

	@Override
	public void deleteByCSRWid(String rwid,String name) {
		Twjjgtxr tmpent = entitydao.findUnique("from Twjjgtxr a where a.csrw.id='"+rwid+"' and a.txr='"+name+"'");
		if(tmpent != null)
			entitydao.delete(tmpent);
	}

	@Override
	public List<Twjjgtxr> getByLoginName(String loginName) {
		List<Twjjgtxr> tmplist = entitydao.getSession().createQuery("from Twjjgtxr t where t.txr = '"+loginName+"' and t.csrw.id is not null").list();
		return tmplist;
	}

	@Override
	public Twjjgtxr getRecord(User usr, Csrw csrw) {
		StringBuffer hql=new StringBuffer();
		hql.append("from Twjjgtxr a where a.txr='"+usr.getLoginName()+"' and a.csrw.id ='"+csrw.getId()+"'");
		return entitydao.findUnique(hql.toString());
	}

}
