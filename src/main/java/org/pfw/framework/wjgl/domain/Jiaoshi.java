package org.pfw.framework.wjgl.domain;

import java.io.Serializable;

import org.pfw.framework.domain.IdEntity;

public class Jiaoshi extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1035895023042978106L;
	
	private String gh;
	private String xm;
	private String xb;
	private String bz;
	
	public String getGh() {
		return gh;
	}
	public void setGh(String gh) {
		this.gh = gh;
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
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}