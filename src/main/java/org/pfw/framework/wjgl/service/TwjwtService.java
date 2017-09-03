package org.pfw.framework.wjgl.service;

import java.util.List;
import java.util.Map;












import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.xtgl.domain.Dict;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;

public interface TwjwtService {
	Twjwt getById(String id);

	void deleteById(String id);

	Page findPage(Page page, List<PropertyFilter> filters);

	void save(Twjwt entity);
	
	void update(Twjwt entity);

	boolean exists(String attName,String value);

	void delete(List<String> checks) throws Exception;

	List<Twjwt> listByFaid(String faid);
	
	List<Twjwt> findAll();
	
	Map getJfgzMap();
	
	List<Twjwt> getRandwt(Twjfafl fl);
	
	List<Twjwt> getRandwt(int faflid,int dxtsl,int pdtsl);
	
	List<Twjwt> getRandwtForTaoti(Twjfafl fl,int dxtsl,int pdtsl);
	
	String getSytlForTaoti(int faflid,String lx,Twjfafl fl);//lx表示题型传参,1表示单选、2表示判断
	
	String getRandwtId(int wts);
	
	List<Twjwt> getWtByfl(String flid);
	
	List tmfltj();

	Object getdx(int id);

	Object getpd(int id);

	Page findlist(Page page, List<String> slist);
	
	/**
	 * 根据知识点名称计算总数
	 * @author  xl 
	 * @date 创建时间：2017年4月18日 上午11:16:45 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	Object countKnow(Twjfafl tw,String lx,String cs);
	
	/**
	 * 按比例抽取题目
	 * @author  xl 
	 * @date 创建时间：2017年5月10日 上午11:01:42 
	 * @Description 
	 * @parameter fl 课程 , lx 题型 , ndfb 难度分布/知识点分布 , tms 题目总数 , taoti 有参为套题抽取 ,无参为测试抽取   
	 * @return
	 */
	List<Twjwt> randomTi(Twjfafl fl,Dict lx, String ndfb, String tms,String taoti);
	
	/**
	 * 计算课程下的题量
	 * @author  xl 
	 * @date 创建时间：2017年4月24日 下午2:20:59 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	Object countCourse(Twjfafl tw);

	List<Object[]> countNum(String id);
	
	/**
	 * 计算章节题量
	 * @author  xl 
	 * @date 创建时间：2017年5月9日 上午9:55:25 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	List<Object[]> countDict(Twjfafl wtfl);
	
	/**
	 * 计算无知识点的题目数量
	 */
	Object countKnow1(Twjfafl fl,String lx,String cs);
}
