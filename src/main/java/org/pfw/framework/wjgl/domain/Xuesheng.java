package org.pfw.framework.wjgl.domain;

import java.io.Serializable;

import org.pfw.framework.domain.IdEntity;

public class Xuesheng extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1035895023042978106L;
	
	private String xh;
	private String xm;
	private String xb;
	private Banji bjid;
	private String bz;
	
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public Banji getBjid() {
		return bjid;
	}
	public void setBjid(Banji bjid) {
		this.bjid = bjid;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}