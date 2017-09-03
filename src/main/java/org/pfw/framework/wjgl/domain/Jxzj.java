package org.pfw.framework.wjgl.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.pfw.framework.domain.IdEntity;

public class Jxzj extends IdEntity implements Serializable {
	
	private String zjmc;
	private String zjxx; 
	private String zjtype;   //0日常 1考试
	private String xn;      
	private String xq;
	private String exam;
	private Set<Jxtm> tmlist = new HashSet<Jxtm>();
	private List<Jxtm> list;
	private JxType type;
	
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getExam() {
		return exam;
	}
	public void setExam(String exam) {
		this.exam = exam;
	}
	
	public JxType getType() {
		return type;
	}
	public void setType(JxType type) {
		this.type = type;
	}
	public String getZjtype() {
		return zjtype;
	}
	public void setZjtype(String zjtype) {
		this.zjtype = zjtype;
	}
	public List<Jxtm> getList() {
		return list;
	}
	public void setList(List<Jxtm> list) {
		this.list = list;
	}
	public String getZjmc() {
		return zjmc;
	}
	public void setZjmc(String zjmc) {
		this.zjmc = zjmc;
	}
	public String getZjxx() {
		return zjxx;
	}
	public void setZjxx(String zjxx) {
		this.zjxx = zjxx;
	}
	public Set<Jxtm> getTmlist() {
		return tmlist;
	}
	public void setTmlist(Set<Jxtm> tmlist) {
		this.tmlist = tmlist;
	}
	
	
}
