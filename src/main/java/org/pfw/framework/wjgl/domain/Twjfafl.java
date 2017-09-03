package org.pfw.framework.wjgl.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Twjfafl implements Serializable {

	private static final long serialVersionUID = 1035895023042978106L;
	
	private String sxh;//顺序号
	private String flmc;//课程（分类名称）
	private String sfyx;//是否app端显示
	private String bz;//备注
	private Set<Twjwt> wtlist = new HashSet<Twjwt>();//问题集合这是啥？知识点么
	//写一下：
	public int id;
	private Twjfafl fparent;
	private String xssx;
	private String jdtype;
	private String tiliang;
	private Set<Twjfafl> children;
	
	public String getTiliang() {
		return tiliang;
	}
	public void setTiliang(String tiliang) {
		this.tiliang = tiliang;
	}
	
	public Twjfafl getFparent() {
		return fparent;
	}
	public void setFparent(Twjfafl fparent) {
		this.fparent = fparent;
	}
	public String getXssx() {
		return xssx;
	}
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	public String getJdtype() {
		return jdtype;
	}
	public void setJdtype(String jdtype) {
		this.jdtype = jdtype;
	}
	public Set<Twjfafl> getChildren() {
		return children;
	}
	public void setChildren(Set<Twjfafl> children) {
		this.children = children;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Set<Twjwt> getWtlist() {
		return wtlist;
	}
	public void setWtlist(Set<Twjwt> wtlist) {
		this.wtlist = wtlist;
	}
	public String getSxh() {
		return sxh;
	}
	public void setSxh(String sxh) {
		this.sxh = sxh;
	}
	public String getFlmc() {
		return flmc;
	}
	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}
	public String getSfyx() {
		return sfyx;
	}
	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}

}