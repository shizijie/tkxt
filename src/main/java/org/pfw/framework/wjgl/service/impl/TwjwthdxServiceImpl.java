package org.pfw.framework.wjgl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.pfw.framework.wjgl.dao.TwjwthdxDao;
import org.pfw.framework.wjgl.dao.TwjwtDao;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.wjgl.domain.Twjwthdx;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.service.TwjfaflService;
import org.pfw.framework.wjgl.service.TwjwthdxService;
import org.pfw.framework.wjgl.service.TwjwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TwjwthdxServiceImpl implements TwjwthdxService {
	@Autowired
	private TwjwthdxDao supervisionItemAnswerDao;
	public void deleteById(String id) {
		supervisionItemAnswerDao.delete(Integer.parseInt(id));
	}

	public boolean exists(String attName,String value) {
		List<Twjwthdx> ls = supervisionItemAnswerDao.findBy(attName, value);
		if(ls!=null&&ls.size()>0){
			return true;
		}
		return false;
	}

	public Page findPage(Page page, List<PropertyFilter> filters) {
		return supervisionItemAnswerDao.findPage(page, filters);
	}

	public Twjwthdx getById(String id) {
		return supervisionItemAnswerDao.get(Integer.parseInt(id));
	}

	public void save(Twjwthdx entity) {
		supervisionItemAnswerDao.save(entity);
	}

	public void delete(List<String> checks) {
		if(checks!=null&&checks.size()>0){
			for (String id : checks) {
				supervisionItemAnswerDao.delete(Integer.parseInt(id));
			}
		}
	}

	public void update(Twjwthdx entity) {
		supervisionItemAnswerDao.update(entity);
	}

	public List<Twjwthdx> listByType(String type) {
		List<Twjwthdx> ls = supervisionItemAnswerDao.findBy("type.id", type);
		return ls;
	}
	
	@SuppressWarnings("unchecked")
	public List findAll() {
		List ls = supervisionItemAnswerDao.getSession().createQuery("from SupervisionItemAnswer r where r.parent is null order by r.lbdm,r.id").list();
		return ls;
	}
	

}
