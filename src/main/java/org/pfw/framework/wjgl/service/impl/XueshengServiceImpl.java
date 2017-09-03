package org.pfw.framework.wjgl.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.pfw.framework.wjgl.dao.XueshengDao;
import org.pfw.framework.wjgl.domain.Xuesheng;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.service.XueshengService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class XueshengServiceImpl implements XueshengService {

	@Autowired
	private XueshengDao entityDao;

	@Transactional(readOnly=true)
	public Xuesheng getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<Xuesheng> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	public void save(Xuesheng entity)
	{
		entityDao.save(entity);
	}
	
	public void update(Xuesheng entity)
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
	public List<Xuesheng> findAll() {
		List tmpls = entityDao.find(" from Xuesheng a order by a.xm");
		return tmpls;
	}

	@Override
	public List<Xuesheng> findxsnotinbj(String bjid) {
		List tmpls = entityDao.find(" from Xuesheng a where a.bjid.id <> ? order by a.xm",bjid);
		return tmpls;
	}

	@Override
	public List<Xuesheng> findxsinbj(String bjid) {
		List tmpls = entityDao.find(" from Xuesheng a where a.bjid.id = ? order by a.xm",bjid);
		return tmpls;
	}
	
	@Override
	public Xuesheng getByXh(String xh) {
		if(StringUtils.isNotEmpty(xh))
		{
			Xuesheng xstmp = entityDao.findUniqueBy("xh", xh);
			return xstmp;
		}else{
			return null;
		}
		
	}

	@Override
	public Xuesheng getByXm(String currentUserName) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotEmpty(currentUserName))
		{
			Xuesheng xstmp = entityDao.findUniqueBy("xm", currentUserName);
			return xstmp;
		}else{
			return null;
		}
	}

	@Override
	public List<Xuesheng> getByIdStr(String selbjstr) {
		// TODO Auto-generated method stub
		return entityDao.getSession().createQuery(" from Xuesheng a where a.bjid.id in ('"+selbjstr.replaceAll(",", "','")+"')").list();
	}

	
}
