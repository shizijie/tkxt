package org.pfw.framework.wjgl.domain;

import java.io.Serializable;

import org.pfw.framework.domain.IdEntity;
import org.pfw.framework.domain.security.User;

public class Ttcsjg extends IdEntity implements Serializable{
	private Xuesheng ztr;        //做题人
	private String ztjg;	 //做题结果
	private Taoti ztkm;     //做题科目
	private String zttime;   //做题时间
	private String ztzql;    //做题正确量
	private String ztcwl;    //做题错误量
	private String ztzl;     //做题总量
	private String bz1;
	private String bz2;
	public String getBz1() {
		return bz1;
	}
	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}
	public String getBz2() {
		return bz2;
	}
	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}
	public String getZtcwl() {
		return ztcwl;
	}
	public void setZtcwl(String ztcwl) {
		this.ztcwl = ztcwl;
	}
	public String getZtzl() {
		return ztzl;
	}
	public void setZtzl(String ztzl) {
		this.ztzl = ztzl;
	}
	public Xuesheng getZtr() {
		return ztr;
	}
	public void setZtr(Xuesheng ztr) {
		this.ztr = ztr;
	}
	public String getZtjg() {
		return ztjg;
	}
	public void setZtjg(String ztjg) {
		this.ztjg = ztjg;
	}
	public Taoti getZtkm() {
		return ztkm;
	}
	public void setZtkm(Taoti ztkm) {
		this.ztkm = ztkm;
	}
	public String getZttime() {
		return zttime;
	}
	public void setZttime(String zttime) {
		this.zttime = zttime;
	}
	public String getZtzql() {
		return ztzql;
	}
	public void setZtzql(String ztzql) {
		this.ztzql = ztzql;
	}
	
}
