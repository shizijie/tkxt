package org.pfw.framework.wjgl.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.pfw.framework.domain.IdEntity;

public class Taoti extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1035895023042978106L;
	private String ttmc;
	private String dxtsl;
	private String pdtsl;
	private String remark;
	private Twjfafl fafl;
	private Set<Twjwt> tmlist = new HashSet<Twjwt>();
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTtmc() {
		return ttmc;
	}
	public void setTtmc(String ttmc) {
		this.ttmc = ttmc;
	}
	public String getDxtsl() {
		return dxtsl;
	}
	public void setDxtsl(String dxtsl) {
		this.dxtsl = dxtsl;
	}
	public String getPdtsl() {
		return pdtsl;
	}
	public void setPdtsl(String pdtsl) {
		this.pdtsl = pdtsl;
	}
	public Twjfafl getFafl() {
		return fafl;
	}
	public void setFafl(Twjfafl fafl) {
		this.fafl = fafl;
	}
	public Set<Twjwt> getTmlist() {
		return tmlist;
	}
	public void setTmlist(Set<Twjwt> tmlist) {
		this.tmlist = tmlist;
	}
}