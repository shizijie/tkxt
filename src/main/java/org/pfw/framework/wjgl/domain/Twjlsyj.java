package org.pfw.framework.wjgl.domain;

import org.pfw.framework.domain.IdEntity;

public class Twjlsyj extends IdEntity implements java.io.Serializable {
	private String zh;
	private String mm;
	private String xm;
	private String bz;
	
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getZh() {
		return zh;
	}
	public void setZh(String zh) {
		this.zh = zh;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
