package org.pfw.framework.wjgl.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.pfw.framework.wjgl.domain.Csrw;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.xtgl.domain.Dict;
import org.pfw.framework.domain.security.User;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;




public interface CsrwService {
	
	/**
	 * 不含综合题HQL 1
	 */
	public static String tmfbhql  = "select wtlx,count(*) from Twjwt r where r.faflid.id in (?) group by  wtlx";
	public static String ndfbhql  = "select nycd,count(*) from Twjwt r where r.faflid.id in (?) group by  nycd";
	public static String zsdfbhql = "select r.faflid.flmc,count(*) from Twjwt r where r.faflid.id in (?) and r.faflid.jdtype = '3' group by  r.faflid.flmc";
	public static String taoTiLxhql  = "select wtlx,count(*) from Twjwt r where r.faflid.id in (?) and r.id not in (select distinct a.id from Twjwt a,Taoti t  where a in elements(t.tmlist)) group by  wtlx";
	public static String taoTinysql  = "select nycd,count(*) from Twjwt r where r.faflid.id in (?) and r.id not in (select distinct a.id from Twjwt a,Taoti t  where a in elements(t.tmlist)) group by  nycd";
	public static String coursenysql  = "select nycd,count(*) from Twjwt r where r.faflid.id in (?) and r.id not in (select distinct a.id from Twjwt a,Taoti t  where a in elements(t.tmlist)) and r.wtlx='|' group by  nycd";
	
	/**
	 * 含综合题SQL 2
	 */
	public static String tmfbhql1="SELECT a.WTLX ,COUNT(1) FROM T_WJGL_WJWT a where a.FAFLID in (?) group by a.WTLX  union all select b.TOPIC_TYPE,COUNT(1) from T_WJGL_CONTACT_WEB_TOPIC b where b.TWJFAFL in (?) group by b.TOPIC_TYPE";
	public static String ndfbhql1="select c.nycd ,sum(c.num) from (SELECT a.nycd ,COUNT(1) num FROM T_WJGL_WJWT a where a.FAFLID in (?) group by a.nycd union all select b.[COMPLEXITY],COUNT(1)num from T_WJGL_CONTACT_WEB_TOPIC b where b.TWJFAFL in (?) group by b.[COMPLEXITY])c group by c.nycd";
	public static String zsdfbhql1=" select sum(c.num) from(select COUNT(1) num from T_WJGL_WJWT a where a.faflid in(?) and a.knowledgePoint like '%|%' union all select COUNT(1) num from T_WJGL_CONTACT_WEB_TOPIC b where b.TWJFAFL in(?) and b.KNOWLEDGE_POINT like '%|%')c";
	/**
	 * 获取难易知识点SQL
	 */
	public static String getNySql="select c.nycd ,sum(c.num) from (SELECT a.nycd ,COUNT(1) num FROM T_WJGL_WJWT a where a.FAFLID in (?) and a.WTLX='|' group by a.nycd union all select b.[COMPLEXITY],COUNT(1)num from T_WJGL_CONTACT_WEB_TOPIC b where b.TWJFAFL in (?) group by b.[COMPLEXITY])c group by c.nycd";
	
	
	Csrw getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Csrw> find(List<PropertyFilter> filters);
	
	void save(Csrw entity);
	
	void update(Csrw entity);

	void deleteById(String id);
	
	void delete(List<String> checks);

	List<Csrw> findAll();
	
	String sjzj(Dict tmlx,String rwid,String tms) throws Exception;
	
	String sjndzj(Dict tmlx,String rwid,String tms,String ndfb) throws Exception;
	
	String sjzsdzj(Dict tmlx,String rwid,String tms,String zsdfb) throws Exception;
	
	List getFb(Twjfafl fafl,String hql,int type) throws Exception;
	
	String sjndzsdzj(Dict tmlx,String rwid,String tms,String ndfb,String zsdfb) throws Exception;
	
	/**
	 * 统计知识点
	 * @author  xl 
	 * @date 创建时间：2017年4月18日 上午10:49:05 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	List countKnowledgePoint(List<Twjfafl> allKnow);
	
	void build(String dict,String rwid,String num,String nandu,String zsd);
	
	/**
	 * 根据课程，类型获取同一类型问题难易分布
	 * @author  xl 
	 * @date 创建时间：2017年4月21日 下午1:29:34 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	List lxFb(Twjfafl fl, String coursenysql2,String lx) throws Exception;
	
	String getImageStr(String imgFile,String type) throws Exception;
	
	String getWidth(String address,String type) throws IOException;
	
	String getHeight(String address,String type) throws IOException;

	void test() throws HibernateException, SQLException;

	Object countKnow(Twjfafl fafl, Twjfafl know, String sql, int type);

	List getNy(Twjfafl fafl, String tx,String str);

	
	
}
