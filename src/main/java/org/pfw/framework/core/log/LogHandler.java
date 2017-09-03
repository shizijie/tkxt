package org.pfw.framework.core.log;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.proxy.HibernateProxy;
import org.pfw.framework.util.PFWSecurityUtils;

@Aspect
public class LogHandler {
	
	/**
	 * 对所有update操作进行日志记录
	 * @param invocation
	 */
	@After("execution (* org.pfw.framework..*.*Dao.update*(..))")
	public void AfterLogUpdate(JoinPoint  invocation)
	{
		
		//记录日志
		
		try 
		{
			Object entity = (Object)invocation.getArgs()[0];
			String className = invocation.getTarget().getClass().getSimpleName().toString();
			
			//如果是hql或者sql情况
			if(entity instanceof String)
			{
				String tmpStr = "";
				Object[] obj = invocation.getArgs();
				for(Object tmpobj : obj)
				{
					if(tmpobj instanceof String)
						tmpStr += tmpobj.toString() + ",";
					
					if(tmpobj instanceof Object[])
					{
						Object[] ttt = (Object[])tmpobj;
						for(int i = 0; i < ttt.length; i++)
						{
							tmpStr += (String)ttt[i] + ",";
						}
							
					}
					if(tmpobj instanceof List)
					{
						List ttt = (List)tmpobj;
						for(int i = 0; i < ttt.size(); i++)
						{
							tmpStr += (String)ttt.get(i) + ",";
						}
					}
					
				}
				
				Log.log().info("用户：" + PFWSecurityUtils.getCurrentUserName() + " (IP:"+PFWSecurityUtils.getIpAddr()+")" + " 更新操作  " + className + " 操作记录参数：" + tmpStr);
				
			}else
			{
				String tmpId = "";
				if(entity instanceof HibernateProxy)
				{
					Object  realEntity= ((HibernateProxy)entity).getHibernateLazyInitializer().getImplementation();
					tmpId = realEntity.getClass().getField("id").get(realEntity).toString();
				}else{
					tmpId = entity.getClass().getField("id").get(entity).toString();
				}
				Log.log().info("用户：" + PFWSecurityUtils.getCurrentUserName() + " (IP:"+PFWSecurityUtils.getIpAddr()+")" + " 更新操作  " + className + " 操作记录ID：" + tmpId);			
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.log().info("记录更新日志出错：" + e);
		}
	}
	
	/**
	 * 对所有save操作进行日志记录
	 * @param invocation
	 */	
	
	@After("execution (* org.pfw.framework..*.*Dao.save*(..))")
	public void AfterLogSave(JoinPoint invocation)
	{
		
		//记录日志
		
		try 
		{
			Object entity = (Object)invocation.getArgs()[0];
			String className = invocation.getTarget().getClass().getSimpleName().toString();
			
			//如果是hql或者sql情况
			if(entity instanceof String)
			{
				String tmpStr = "";
				Object[] obj = invocation.getArgs();
				for(Object tmpobj : obj)
				{
					if(tmpobj instanceof String)
						tmpStr += tmpobj.toString() + ",";
					
					if(tmpobj instanceof Object[])
					{
						Object[] ttt = (Object[])tmpobj;
						for(int i = 0; i < ttt.length; i++)
						{
							tmpStr += (String)ttt[i] + ",";
						}
							
					}
					if(tmpobj instanceof List)
					{
						List ttt = (List)tmpobj;
						for(int i = 0; i < ttt.size(); i++)
						{
							tmpStr += (String)ttt.get(i) + ",";
						}
					}
					
				}
				Log.log().info("用户：" + PFWSecurityUtils.getCurrentUserName() + " (IP:"+PFWSecurityUtils.getIpAddr()+")" + " 保存操作  " + className + " 操作记录参数：" + tmpStr);
				
			}else
			{
				String tmpId = "";
				if(entity instanceof HibernateProxy)
				{
					Object  realEntity= ((HibernateProxy)entity).getHibernateLazyInitializer().getImplementation();
					tmpId = realEntity.getClass().getField("id").get(realEntity).toString();
				}else{
					tmpId = entity.getClass().getField("id").get(entity).toString();
				}
				Log.log().info("用户：" + PFWSecurityUtils.getCurrentUserName() + " (IP:"+PFWSecurityUtils.getIpAddr()+")" + " 保存操作  " + className + " 操作记录ID：" + tmpId);			
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.log().info("记录保存日志出错：" + e);
		}		
	}	
	
	
	/**
	 * 对所有delete操作进行日志记录
	 * 单条记录删除
	 * @param invocation
	 */	
	@SuppressWarnings("unchecked")
	@After("execution (* org.pfw.framework..*.*Dao.delete*(..))")
	public void AfterLogDelete(JoinPoint invocation)
	{
		
		//记录日志
		
		try 
		{
			Object entity = (Object)invocation.getArgs()[0];
			String className = invocation.getTarget().getClass().getSimpleName().toString();
			
			//如果是hql或者sql情况，主键为int类型时
			if(entity instanceof String || entity instanceof Integer)
			{
				String tmpStr = "";
				Object[] obj = invocation.getArgs();
				for(Object tmpobj : obj)
				{
					if(tmpobj instanceof String || tmpobj instanceof Integer)
						tmpStr += tmpobj.toString() + ",";
					
					if(tmpobj instanceof Object[])
					{
						Object[] ttt = (Object[])tmpobj;
						for(int i = 0; i < ttt.length; i++)
						{
							tmpStr += (String)ttt[i] + ",";
						}
							
					}
					if(tmpobj instanceof List)
					{
						List ttt = (List)tmpobj;
						for(int i = 0; i < ttt.size(); i++)
						{
							tmpStr += (String)ttt.get(i) + ",";
						}
					}
					
				}
				
				Log.log().info("用户：" + PFWSecurityUtils.getCurrentUserName() + " (IP:"+PFWSecurityUtils.getIpAddr()+")" + " 删除操作  " + className + " 操作记录参数：" + tmpStr);
				
			}else if(entity instanceof List){
				String tmpid = "";
				List<String> batchIdtmp = (List<String>)entity;
				for(String idtmp : batchIdtmp)
				{
					tmpid += idtmp + ",";
				}	
				
				Log.log().info("用户：" + PFWSecurityUtils.getCurrentUserName() + " (IP:"+PFWSecurityUtils.getIpAddr()+")" + " 批量删除操作  " + className + " 操作记录ID：" + tmpid);
			}
			else
			{
				String tmpId = "";
				if(entity instanceof HibernateProxy)
				{
					Object  realEntity= ((HibernateProxy)entity).getHibernateLazyInitializer().getImplementation();
					tmpId = realEntity.getClass().getField("id").get(realEntity).toString();
				}else{
					tmpId = entity.getClass().getField("id").get(entity).toString();
				}
				Log.log().info("用户：" + PFWSecurityUtils.getCurrentUserName() + " (IP:"+PFWSecurityUtils.getIpAddr()+")" + " 删除操作  " + className + " 操作记录ID：" + tmpId);			
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.log().info("记录删除日志出错：" + e);
		}	
		
	}	
	
/*	
 	//@Around ("execution (* org.pfw.framework.service.impl.security.RoleServiceImpl.*delete*(..))")
    public Object invoke(ProceedingJoinPoint invocation) throws Throwable {   
		
		Object[] obj = invocation.getArgs();
		
		List<String> ll = (List<String>)invocation.getArgs()[0];
		
		if(ll!=null&&ll.size()>0){
			for(String str:ll){
				if(str!=null){
					List list=tBbcjRwfjbService.getRwfjb("roleid", str, MatchType.EQ);
					if(list!=null&&list.size()>0){
						Struts2Utils.renderText("角色已被使用，不能删除");
						Struts2Utils.getResponse().setStatus(500);
						throw new ConstraintViolationException("角色已被使用，不能删除", null, null);
					}
				}
			}
		}

        return invocation.proceed(); 
        
    }	
	
	public void afterLog() 
	{  
		System.out.println("-------------afterLog---------------");
	} 
*/	
	
}
