package org.pfw.framework.wjgl.domain;

import java.io.Serializable;

import org.pfw.framework.domain.IdEntity;
import org.pfw.framework.domain.security.User;

public class Banji extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1035895023042978106L;
	
	private String bjdm;
	private String bjmc;
	private String nj;
	private Jiaoshi bzr;
	private String bz;
	
	
	public Jiaoshi getBzr() {
		return bzr;
	}
	public void setBzr(Jiaoshi bzr) {
		this.bzr = bzr;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
}