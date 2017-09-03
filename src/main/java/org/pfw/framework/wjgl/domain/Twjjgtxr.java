package org.pfw.framework.wjgl.domain;

import java.util.HashSet;
import java.util.Set;

import org.pfw.framework.domain.IdEntity;

public class Twjjgtxr extends IdEntity implements java.io.Serializable {

	private static final long serialVersionUID = 6179749951115048747L;
	
	private String txr;
	private String sjh;
	private String yxdz;
	private String dxsj;
	private String ipdz;
	private Twjfafl faflid;
	private Csrw csrw;
	private Integer tmzs;
	private Integer zqsl;
	private Integer cwsl;
	private Xuesheng xuesheng;
	private Set<Twjjg> wjjglist = new HashSet<Twjjg>();
	private String grade;
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Xuesheng getXuesheng() {
		return xuesheng;
	}
	public void setXuesheng(Xuesheng xuesheng) {
		this.xuesheng = xuesheng;
	}
	public Csrw getCsrw() {
		return csrw;
	}
	public void setCsrw(Csrw csrw) {
		this.csrw = csrw;
	}
	public Integer getTmzs() {
		return tmzs;
	}
	public void setTmzs(Integer tmzs) {
		this.tmzs = tmzs;
	}
	public Integer getZqsl() {
		return zqsl;
	}
	public void setZqsl(Integer zqsl) {
		this.zqsl = zqsl;
	}
	public Integer getCwsl() {
		return cwsl;
	}
	public void setCwsl(Integer cwsl) {
		this.cwsl = cwsl;
	}
	public Twjfafl getFaflid() {
		return faflid;
	}
	public void setFaflid(Twjfafl faflid) {
		this.faflid = faflid;
	}
	public String getTxr() {
		return txr;
	}
	public void setTxr(String txr) {
		this.txr = txr;
	}
	public String getSjh() {
		return sjh;
	}
	public void setSjh(String sjh) {
		this.sjh = sjh;
	}
	public String getYxdz() {
		return yxdz;
	}
	public void setYxdz(String yxdz) {
		this.yxdz = yxdz;
	}
	public String getDxsj() {
		return dxsj;
	}
	public void setDxsj(String dxsj) {
		this.dxsj = dxsj;
	}
	public Set<Twjjg> getWjjglist() {
		return wjjglist;
	}
	public void setWjjglist(Set<Twjjg> wjjglist) {
		this.wjjglist = wjjglist;
	}
	public String getIpdz() {
		return ipdz;
	}
	public void setIpdz(String ipdz) {
		this.ipdz = ipdz;
	}
	
}