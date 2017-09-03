package org.pfw.framework.wjgl.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.dao.BanjiDao;
import org.pfw.framework.wjgl.dao.TwjjgDao;
import org.pfw.framework.wjgl.domain.Banji;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjjg;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.wjgl.service.TwjjgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TwjjgServiceImpl implements TwjjgService {

	@Autowired
	private TwjjgDao wjjgdao;
	/*
	@Autowired
	private TaskItemDao taskitemDao;
	@Autowired
	private TaskBatchService taskbatchservice;
	@Autowired
	private TaskResultControlService taskresultcontrolservice;
	*/
	
	public void deleteById(String id) {
		wjjgdao.delete(id);
	}

	public Page findPage(Page page, List<PropertyFilter> filters) {
		return wjjgdao.findPage(page, filters);
	}
	
	public Twjjg getById(String id) {
		return wjjgdao.get(id);
	}

	public void save(Twjjg entity) {
		wjjgdao.save(entity);
	}
	
	public void save(List<Twjjg> entity)
	{
		for(Twjjg trs : entity)
		{
			save(trs);
		}
	}

	public void delete(List<String> checks) {
		if (checks != null && checks.size() > 0) {
			for (String id : checks) {
				wjjgdao.delete(id);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void update(Twjjg entity) {
		wjjgdao.update(entity);
	}
	
	
	@Override
	public List<Twjjg> getByTxrAndPc(String txrid, String pcid) {
		Query qu = wjjgdao.createQuery(" from Twjjg r where r.txrid.id = ? and r.pcid.id = ?");
		qu.setString(0, txrid);
		qu.setString(1, pcid);
		return qu.list();
	}
	
	/*
	public void deleteByPcUser(String pcid,String tbr)
	{
		Query qu = taskresultdao.createQuery(" from TaskResult r where r.taskbatch.id = ? and r.tbr = ?");
		qu.setString(0, pcid);
		qu.setString(1, tbr);
		List<TaskResult> tmp = qu.list();
		if(tmp != null && tmp.size() > 0)
		{
			for(TaskResult entity : tmp)
			{
				taskresultdao.delete(entity);	
			}
						
		}
	}
	
	public void deleteByPcUser(String pcid,String tbr,String systemid)
	{
		Query qu = taskresultdao.createQuery("  from TaskResult r where  r.taskitem.taskCatalog.parent.spbc.spst.id = ? and r.taskbatch.id = ? and r.tbr = ?");
		qu.setString(0, systemid);
		qu.setString(1, tbr);
		qu.setString(2, pcid);
		List<TaskResult> tmp = qu.list();
		if(tmp != null && tmp.size() > 0)
		{
			for(TaskResult entity : tmp)
			{
				taskresultdao.delete(entity);	
			}
						
		}		
	}	
	
	public void deleteByUsUtid(String utid,String loginname,String pcid)
	{
		Query qu = taskresultdao.createQuery(" delete TaskResult r where r.taskbatch.id = ? and r.tbr = ? and r.taskitem.id = ?");
		qu.setString(0, pcid);
		qu.setString(1, loginname);
		qu.setString(2, utid);
		qu.executeUpdate();		
	}
	
	public void deleteByTsId(TaskResult tmptr)
	{
		Query qu = taskresultdao.createQuery(" delete TaskResultAnswer r where r.taskresult.id = ?");
		qu.setString(0, tmptr.getId());
		qu.executeUpdate();			
	}


	@SuppressWarnings("unchecked")
	public List findAll() {
		List ls = taskresultdao.getSession().createQuery(
				"from TaskResult r order by r.id").list();
		return ls;
	}
	@SuppressWarnings("unchecked")
	public List<TaskResult> listByPcBm(String pcid,String bmid)
	{
		Query qu = taskresultdao.createQuery(" from TaskResult r where r.taskbatch.id = ? " +
		"and r.taskitem.department.id = ? ");
//		Query qu = taskresultdao.createQuery(" from TaskResult r where r.taskbatch.id = ? " +
//				"and r.taskitem.department.id = ? and r.shzt not in ('5')");
		qu.setString(0, pcid);
		qu.setString(1, bmid);
		return qu.list();
	}
	@SuppressWarnings("unchecked")
	public TaskResult getByPcAndXz(String pcid,String xzid)
	{
		Query qu = taskresultdao.createQuery(" from TaskResult r where r.taskbatch.id = ? and r.taskitem.id = ?");
		qu.setString(0, pcid);
		qu.setString(1, xzid);
		return (TaskResult)qu.list().get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskResult> getVerifyTask(String taskBatchID,User us)
	{
		String loginname = us.getLoginName();
		
		List<String> cc = taskresultcontrolservice.getDepByLoginname(loginname);
		String whereHql = "";
		String hql = "from TaskResult r where r.taskbatch.id = '"+taskBatchID+"' ";
		for(String str : cc)
			whereHql += "'"+str+"',";
		if(!whereHql.equals(""))
		{
			whereHql = whereHql.substring(0,whereHql.length()-1);
			hql += " and r.taskitem.department.id in ("+whereHql+")";
		}
			
		return taskresultdao.createQuery(hql).list();
	}
	
	
	@SuppressWarnings("unchecked")
	public HashMap<String, HashMap<String,String>> getVerifyStatusCoun(User us)
	{
		HashMap<String, HashMap<String,String>> returnHash = new HashMap();
		String loginname = us.getLoginName();
		//取得该用户可以审核的部门数据
		List<String> cc = taskresultcontrolservice.getDepByLoginname(loginname);
		
		String whereHql = "'',";
		for(String str : cc)
			whereHql += "'"+str+"',";
		whereHql = whereHql.substring(0,whereHql.length()-1);	
		
		List<TaskBatch> taskbatchlist = taskbatchservice.findAll();
		if(taskbatchlist != null && taskbatchlist.size() > 0)
		{
			for(TaskBatch tbc : taskbatchlist)
			{
				String tbcId = tbc.getId();
				
				Query qu = taskresultdao.createQuery(" select a.shzt,count(*) from TaskResult a join a.taskitem b "
													+"where a.taskbatch.id = ? and b.department.id in ("+whereHql+") group by a.shzt");
				qu.setString(0, tbcId);
				
				HashMap<String,String> tmpHash = new HashMap<String,String>();
				
				for(Iterator ita = qu.list().iterator();ita.hasNext();)
				{
					 Object[] p = (Object[])ita.next();
					 if(p[0] != null && !p[0].toString().equals(""))
					 {
						 
						 String shzt = p[0].toString();
						 String shztCou = p[1].toString();
						 tmpHash.put(shzt, shztCou);						 
					 }

				}
				
				returnHash.put(tbcId, tmpHash);
			}			
		}
		return returnHash;
	}
	
	*/
	
	public String getTaskResultScore(Twjjg tr) throws Exception
	{
		DecimalFormat df = new DecimalFormat("0.00");
		Twjwt ti = tr.getWtid();
		String wtlx = ti.getWtlx();
		String jfgzdm = ti.getJfgzdm();
		//List<Twjjgxq> tmptral = new ArrayList<Twjjgxq>(tr.getWjjgxqlist());
		String reScore = "0";
		
		//如果是单选题 默认根据答案的分值计得分
		if(wtlx != null && wtlx.equals("1"))
		{
			/*
			if(tmptral != null && tmptral.size() == 1)
			{
				if(jfgzdm != null && jfgzdm.equals("1"))
				{
					Twjjgxq tra = tmptral.get(0);
					reScore = tra.getTwjwthdxid().getDafs();
				}else{
					throw new Exception("任务计分规则错误："+ti.getWtmc()+"是单选题，没有按单选题规则计分");
				}

			}else{
				throw new Exception("用户答题数据错误，请进行数据纠正！");
			}
			*/
		}
		//如果是多选题 根据计分规则进行计算分值
		if(wtlx != null && wtlx.equals("2"))
		{
			/*
			if(tmptral != null && tmptral.size() > 0)
			{
				//如果是按答案的分值计分
				if(jfgzdm != null && jfgzdm.equals("1"))
				{
					float tmpScore = 0;
					for(Twjjgxq tra : tmptral)
					{
						tmpScore += new Float(tra.getTwjwthdxid().getDafs());
					}
					reScore = new Float(df.format(tmpScore)).toString();
					
				}else if(jfgzdm != null && jfgzdm.equals("2")){
					//如果jfgzdm 为 2 ,全答得分，漏选一个则得0分
					float tmpScore = 0;
					//细则答案情况
					ArrayList<Twjwthdx> tmpsia = new ArrayList(ti.getHdxlist());
					int yzqs = 0;//细则正确答案个数
					int uzqs = 0;//用户选中的正确答案个数
					for(Twjwthdx tia : tmpsia)
					{
						if(tia.getSfzqda().equals("1"))
							yzqs += 1;
					}
					for(Twjjgxq tra : tmptral)
					{
						System.out.println(tra.getId());
						if(tra.getTwjwthdxid().getSfzqda().equals("1"))
							uzqs += 1;
					}
					if(yzqs == 0)
						throw new Exception("多选题："+ti.getWtmc()+"答案数非法，请确认");
					//如果全对了
					if(yzqs == uzqs && uzqs == tmptral.size())
					{
						List<Twjwtjfgz> tmptisr = new ArrayList(ti.getJfgzlist());
						if(tmptisr != null && tmptisr.size() == 1)
						{
							reScore = tmptisr.get(0).getDwfs();
								
						}else{
							throw new Exception("多选题："+ti.getWtmc()+"计分规则数据错误，请确认");
						}
					}
				}else if(jfgzdm != null && jfgzdm.equals("3")){
					//如果jfgzdm 为 3 ,答对几个得几分情况
					float tmpScore = 0;
					//细则答案情况
					ArrayList<Twjwthdx> tmpsia = new ArrayList(ti.getHdxlist());
					int yzqs = 0;//细则正确答案个数
					int uzqs = 0;//用户选中的正确答案个数		
					for(Twjwthdx tia : tmpsia)
					{
						if(tia.getSfzqda().equals("1"))
							yzqs += 1;
					}
					for(Twjjgxq tra : tmptral)
					{
						if(tra.getTwjwthdxid().getSfzqda().equals("1"))
							uzqs += 1;
					}
					if(yzqs == 0)
						throw new Exception("多选题："+ti.getWtmc()+"答案数非法，请确认");
					//必须没有选中错误答案
					if(uzqs == tmptral.size())
					{
						List<Twjwtjfgz> tmptisr = new ArrayList(ti.getJfgzlist());
						for(Twjwtjfgz tisr : tmptisr)
						{
							int jfxx = new Integer(tisr.getJlxx());
							int jfsx = new Integer(tisr.getJlsx());
							if(uzqs >= jfxx && uzqs <= jfsx)
							{
								reScore = tisr.getDwfs();
								break;
							}
						}
					}
				}
			}else{
				throw new Exception("用户答题数据错误，请进行数据纠正！");
			}
			*/
		}
		
		////如果是填空题 根据计分规则进行计算分值
		if(wtlx != null && wtlx.equals("3"))
		{
			if(jfgzdm != null && jfgzdm.equals("4"))
			{
				/*
				if(tmptral != null && tmptral.size() == 1)
				{
					float tkda = new Float(tmptral.get(0).getTkda());
					List<Twjwtjfgz> tmptisr = new ArrayList(ti.getJfgzlist());
					if(tmptisr != null && tmptisr.size() == 1)
					{
						float dwfs = new Float(tmptisr.get(0).getDwfs());
						reScore = new Float(df.format(tkda*dwfs)).toString();
					}else{
						throw new Exception("填空题："+ti.getWtmc()+"答案数非法，请确认");
					}
				}else{
					throw new Exception("填空题："+ti.getWtmc()+"答案数非法，请确认");
				}
				*/
			}
		}	
		
		
		return reScore;
	}

	@Override
	public List getdttjls(String flidstr) {
		
		String hql = "select b.wtid.faflid.flmc,b.wtid.wtmc,COUNT(*),"
					+"(select COUNT(*) from Twjjg a where a.wtid.id=b.wtid.id and sfzq='1') as zqs, "
					+"(select COUNT(*) from Twjjg a where a.wtid.id=b.wtid.id and sfzq='0') as cws,b.wtid.id "
					+"from Twjjg b ";
		if(StringUtils.isNotEmpty(flidstr))
			hql += "where b.wtid.faflid.fparent.id in ("+flidstr+")";
		
		hql +="  group by b.wtid.id,b.wtid.wtmc,b.wtid.faflid.flmc order by b.wtid.faflid.flmc,b.wtid.id";
		Query qu = wjjgdao.createQuery(hql);
		
		List tmpls = qu.list();
		
		return tmpls;
	}

	@Override
	public List<Twjjg> getWtBytxrid(String txrid) {
		Query qu = wjjgdao.createQuery(" from Twjjg r where r.txrid.id = ? order by r.bz ");
		qu.setString(0, txrid);
		return qu.list();
	}
	
	@Override
	public List<Twjjg> getWjjglsBytxrid(String txrid) {
		Query qu = wjjgdao.createQuery("select r from Twjjg r where r.txrid.id = ? order by r.bz");
		qu.setString(0, txrid);
		return qu.list();
	}

	@Autowired
	private BanjiDao bjdao;
	
	@Override
	public List bjtjdj(String ksrq, String jsrq, String bj) {
		List retls = new ArrayList();
		
		String hql = " from Banji ";
		if(StringUtils.isNotEmpty(bj))
		{
			hql += " where id = '"+bj+"'";
		}
		hql += " order by bjmc";
		List<Banji> bjlist = bjdao.createQuery(hql).list();
		for(Banji bjtmp : bjlist)
		{
			String bjidtmp = bjtmp.getId();
			String bjmctmp = bjtmp.getBjmc();
			String tjxxtmp = bjmctmp+",";
			//人次
			List ls = wjjgdao.getSession().createSQLQuery("select count(*) from T_WJGL_WJJGTXR a,T_WJGL_XUESHENG b "
					+ " where a.TXR=b.XH and SUBSTRING(DXSJ,0,11)   >='"+ksrq+"' and SUBSTRING(DXSJ,0,11) <= '"+jsrq+"' and b.bjid='"+bjidtmp+"'").list();
			String rc = ls.get(0).toString();
			
			//最高分、最低分、平均分、统计人次
			ls = wjjgdao.getSession().createSQLQuery("select MAX(grade) from T_WJGL_WJJGTXR a,T_WJGL_XUESHENG b "
										+ " where a.TXR=b.XH and SUBSTRING(DXSJ,0,11)   >='"+ksrq+"' and SUBSTRING(DXSJ,0,11) <= '"+jsrq+"' and b.bjid='"+bjidtmp+"'").list();
			String zgf = "0";
			if(ls != null && ls.size() > 0)
			{
				if(ls.get(0) != null)
					zgf = ls.get(0).toString();
			}
			
			//最高分、最低分、平均分、统计人次
			ls = wjjgdao.getSession().createSQLQuery("select MIN(grade) from T_WJGL_WJJGTXR a,T_WJGL_XUESHENG b "
										+ " where a.TXR=b.XH and SUBSTRING(DXSJ,0,11)   >='"+ksrq+"' and SUBSTRING(DXSJ,0,11) <= '"+jsrq+"' and b.bjid='"+bjidtmp+"'").list();
			String zdf = "0";
			if(ls != null && ls.size() > 0)
			{
				if(ls.get(0) != null)
					zdf = ls.get(0).toString();
			}
			//最高分、最低分、平均分、统计人次
			ls = wjjgdao.getSession().createSQLQuery("select avg(cast(grade as decimal(5, 2))) from T_WJGL_WJJGTXR a,T_WJGL_XUESHENG b "
										+ " where a.TXR=b.XH and SUBSTRING(DXSJ,0,11)   >='"+ksrq+"' and SUBSTRING(DXSJ,0,11) <= '"+jsrq+"' and b.bjid='"+bjidtmp+"'").list();
			String pjf = "0";
			if(ls != null && ls.size() > 0)
			{
				if(ls.get(0) != null)
					pjf = ls.get(0).toString();
			}
			
			tjxxtmp += rc+","+zgf+","+zdf+","+pjf+",";
			//90-100分 180道题-200道题
			ls = wjjgdao.getSession().createSQLQuery("select count(txr) from T_WJGL_WJJGTXR a,T_WJGL_XUESHENG b "
					+ "where a.TXR=b.XH and SUBSTRING(DXSJ,0,11)   >='"+ksrq+"' and SUBSTRING(DXSJ,0,11) <= '"+jsrq+"' and b.bjid='"+bjidtmp+"' and grade >= '90' and grade <= '100'").list();
			String rcsl = String.valueOf(ls.get(0));
			tjxxtmp += rcsl+",";
			
			//80-90分 160道题-180道题
			ls = wjjgdao.getSession().createSQLQuery("select count(txr) from T_WJGL_WJJGTXR a,T_WJGL_XUESHENG b "
					+ "where a.TXR=b.XH and SUBSTRING(DXSJ,0,11)   >='"+ksrq+"' and SUBSTRING(DXSJ,0,11) <= '"+jsrq+"' and b.bjid='"+bjidtmp+"' and grade >= '80' and grade < '90'").list();
			rcsl = String.valueOf(ls.get(0));
			tjxxtmp += rcsl+",";
			
			//70-80分 140道题-160道题
			ls = wjjgdao.getSession().createSQLQuery("select count(txr) from T_WJGL_WJJGTXR a,T_WJGL_XUESHENG b "
					+ "where a.TXR=b.XH and SUBSTRING(DXSJ,0,11)   >='"+ksrq+"' and SUBSTRING(DXSJ,0,11) <= '"+jsrq+"' and b.bjid='"+bjidtmp+"' and grade >= '70' and grade < '80'").list();
			rcsl = String.valueOf(ls.get(0));
			tjxxtmp += rcsl+",";
			
			//60-70分 160道题-180道题
			ls = wjjgdao.getSession().createSQLQuery("select count(txr) from T_WJGL_WJJGTXR a,T_WJGL_XUESHENG b "
					+ "where a.TXR=b.XH and SUBSTRING(DXSJ,0,11)   >='"+ksrq+"' and SUBSTRING(DXSJ,0,11) <= '"+jsrq+"' and b.bjid='"+bjidtmp+"' and grade >= '60' and grade < '70'").list();
			rcsl = String.valueOf(ls.get(0));
			tjxxtmp += rcsl+",";
			
			//60以下 120道题
			ls = wjjgdao.getSession().createSQLQuery("select count(txr) from T_WJGL_WJJGTXR a,T_WJGL_XUESHENG b "
					+ "where a.TXR=b.XH and SUBSTRING(DXSJ,0,11)   >='"+ksrq+"' and SUBSTRING(DXSJ,0,11) <= '"+jsrq+"' and b.bjid='"+bjidtmp+"' and grade < '60'").list();
			rcsl = String.valueOf(ls.get(0));
			tjxxtmp += rcsl+",";
			
			if(!tjxxtmp.equals(""))
				tjxxtmp = tjxxtmp.substring(0,tjxxtmp.length()-1);
			
			retls.add(tjxxtmp);
		}
		return retls;
	}
	

}
