package org.pfw.framework.wjgl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.dao.TwjfaflDao;
import org.pfw.framework.wjgl.domain.ContactWebTopic;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.wjgl.service.TwjfaflService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TwjfaflServiceImpl implements TwjfaflService {
	@Autowired
	private TwjfaflDao twjfaflDao;
	public String jsonStr = "";
	
	public void deleteById(int id) {
		twjfaflDao.delete(id);
	}

	public boolean exists(String attName,String value) {
		List<Twjfafl> ls = twjfaflDao.findBy(attName, value);
		if(ls!=null&&ls.size()>0){
			return true;
		}
		return false;
	}

	public Page findPage(Page page, List<PropertyFilter> filters) {
		return twjfaflDao.findPage(page, filters);
	}

	public Twjfafl getById(int id) {
		return twjfaflDao.get(id);
	}

	public void save(Twjfafl entity) {
		twjfaflDao.save(entity);
	}

	public void delete(List<String> checks) {
		if(checks!=null&&checks.size()>0){
			for (String id : checks) {
				twjfaflDao.delete(Integer.valueOf(id));
			}
		}
	}

	public void update(Twjfafl entity) {
		twjfaflDao.update(entity);
	}

	public List<Twjfafl> listByType(String type) {
		List<Twjfafl> ls = twjfaflDao.findBy("type.id", type);
		return ls;
	}
	
	
	public List<Twjfafl> findAll() {
	//	List ls = twjfaflDao.getSession().createQuery("from Twjfafl r where r.sfyx='1' order by r.sxh,r.flmc").list();
		return 	twjfaflDao.find("from Twjfafl r where r.sfyx='1' and r.fparent is null order by r.sxh,r.flmc");
		
	}
	
	public List<Twjfafl> findFparent(){
		return twjfaflDao.find(" from Twjfafl r where r.fparent is null");
	}

	//机械制图bz==1
	public List<Twjfafl> findFparentByBz(){
		return twjfaflDao.find(" from Twjfafl r where r.fparent is null and r.bz='1'");
	}
	
	@Override
	public List<Twjfafl> findByFlmc(String flmc) {
		
		return twjfaflDao.findBy(" from Twjfafl r where r.flmc=?", flmc);
	}

	@Override
	public String geiallfl() {
		// TODO Auto-generated method stub
		List<Twjfafl> li=twjfaflDao.getSession().createQuery("from Twjfafl r where r.fparent is null ").list();
		this.jsonStr = "";
	    this.jsonStr += "[";
	    getChildrenStr(li);
	    this.jsonStr += "]";
	    return this.jsonStr;
	}
	
	 public void getChildrenStr(List<Twjfafl> scChildList)
	  {
	    for (Twjfafl scChildren : scChildList)
	    {
	      this.jsonStr += "{";
	      this.jsonStr = (this.jsonStr + "\"id\":\"" + scChildren.getId() + "\",\"text\":\"" + scChildren.getFlmc() + "\"");

	      if (scChildren.getChildren().size() > 0)
	      {
	        this.jsonStr += ",\"state\":\"closed\",\"children\":[";
	        getChildrenStr(new ArrayList(scChildren.getChildren()));
	        this.jsonStr += "]";
	      }
	      this.jsonStr += "},";
	    }

	    if (this.jsonStr.substring(this.jsonStr.length() - 1).equals(","))
	      this.jsonStr = this.jsonStr.substring(0, this.jsonStr.length() - 1);
	  }

	@Override
	public List<Twjfafl> findkc() {
		// TODO Auto-generated method stub
		return twjfaflDao.getSession().createQuery("from Twjfafl r where r.fparent is null ").list();
	}

	@Override
	public List<Twjfafl> getByParentid( int parentid) {
		String hql=" from Twjfafl r where r.fparent.id='"+parentid+"'";
		return twjfaflDao.getSession().createQuery(hql.toString()).list();
	}

	@Override
	public List<Twjfafl> getALLKnow(Twjfafl tw) {
		if(tw.getChildren().size()>0){
			StringBuffer hql=new StringBuffer();
			List<Twjfafl> list=new ArrayList<Twjfafl>(tw.getChildren());
			hql.append(" from Twjfafl a where a.fparent.id in (");
			hql.append(list.get(0).getId());
			for(int i=1;i<list.size();i++){
				hql.append(","+list.get(i).getId());
			}
			hql.append(") and a.jdtype ='3'");
			return twjfaflDao.getSession().createQuery(hql.toString()).list();
		}else{
			return null;
		}
		
	}

	@Override
	public Twjfafl getByName(String string,Twjwt wt) {
		// TODO Auto-generated method stub
		return twjfaflDao.findUnique("  from Twjfafl r where r.flmc=? and r.fparent.fparent.id=?", string,wt.getFaflid().getFparent().getId());
	}

	@Override
	public Twjfafl getByName1(String string, ContactWebTopic byId) {
		// TODO Auto-generated method stub
		return twjfaflDao.findUnique("  from Twjfafl r where r.flmc=? and r.fparent.fparent.id=?", string,byId.getTwjfafl().getFparent().getId());
	}


	
	
}
