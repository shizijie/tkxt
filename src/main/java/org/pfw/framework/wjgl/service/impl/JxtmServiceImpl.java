package org.pfw.framework.wjgl.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.dao.JxtmDao;
import org.pfw.framework.wjgl.domain.Jxtm;
import org.pfw.framework.wjgl.service.JxtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class JxtmServiceImpl implements JxtmService {

	@Autowired
	private JxtmDao entityDao;

	@Transactional(readOnly=true)
	public Jxtm getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<Jxtm> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	public void save(Jxtm entity)
	{
		entityDao.save(entity);
	}
	
	public void update(Jxtm entity)
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
	public List<Jxtm> findAll()
	{
		return entityDao.find(" from Jxtm r order by r.bjmc");
	}

	@Override
	public List<Jxtm> randomNum(String txid,String number) {
		Query qry=null;
		if(StringUtils.isNotBlank(txid)){
			qry=entityDao.getSession().createQuery(" from Jxtm r where r.type.id=? order by newid()");
			qry.setParameter(0, txid);
		}else{
			qry=entityDao.getSession().createQuery(" from Jxtm r order by newid()");
		}
		qry.setMaxResults(Integer.parseInt(number));
		return qry.list();
	}
	
	@Override
	public List<Jxtm> randomNum1(String txid,String number) {
		Query qry=null;
		if(StringUtils.isNotBlank(txid)){
			qry=entityDao.getSession().createQuery(" from Jxtm r where r.type.fparent.id=? order by newid()");
			qry.setParameter(0, txid);
		}else{
			qry=entityDao.getSession().createQuery(" from Jxtm r order by newid()");
		}
		qry.setMaxResults(Integer.parseInt(number));
		return qry.list();
	}

	@Override
	public List<Jxtm> findByTxAndZjid(String txid, String zjid) {
//		return entityDao.find(" from Jxtm r,Jxzj q where r.txid.id=? and r.id=q.tmlist.id ",txid);
		return entityDao.find(" from Jxzj q left join  Jxtm r where r.txid.id=? and r.id=q.tmlist.id ",txid);
	}

	@Override
	public List<Jxtm> getByTypeId(String string) {
		// TODO Auto-generated method stub
		Query qry= entityDao.getSession().createQuery(" from Jxtm a where a.type.id=?");
		qry.setParameter(0, string);
		return qry.list();
	}

	@Override
	public String countNum(String id) {
		if(StringUtils.isBlank(id)){
			return entityDao.getSession().createQuery(" select count(*) from Jxtm ").uniqueResult().toString();
		}else{
			return entityDao.getSession().createQuery(" select count(*) from Jxtm a where a.type.fparent.id=?").setString(0, id).uniqueResult().toString();
		}
	}
	
}
