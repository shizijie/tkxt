package org.pfw.framework.wjgl.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Twjwt implements java.io.Serializable {

	private Twjfafl faflid;
	private String wtlx;
	private String wtmc;
	private String wtmcFileName;
	private String wtfssx;
	private String sfyx;
	private String jfgzdm;
	private String bz;
	private int sxh;
	private Set<Twjwthdx> hdxlist = new HashSet<Twjwthdx>();
	public int id;
	private String nycd;
	private String knowledgePoint;
	private String name;
	private String ttbz="1";
	private String isorder;
	private String remark="1";
	private String first="1";
	private List<Twjwthdx> hdx=new ArrayList<Twjwthdx>();
	private String imgHeight;
	private String imgWidth;
	
	public String getImgHeight() {
		return imgHeight;
	}
	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}
	public String getImgWidth() {
		return imgWidth;
	}
	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}
	public List<Twjwthdx> getHdx() {
		return hdx;
	}
	public void setHdx(List<Twjwthdx> hdx) {
		this.hdx = hdx;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsorder() {
		return isorder;
	}
	public void setIsorder(String isorder) {
		this.isorder = isorder;
	}
	public String getTtbz() {
		return ttbz;
	}
	public void setTtbz(String ttbz) {
		this.ttbz = ttbz;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKnowledgePoint() {
		return knowledgePoint;
	}
	public void setKnowledgePoint(String knowledgePoint) {
		this.knowledgePoint = knowledgePoint;
	}
	public String getNycd() {
		return nycd;
	}
	public void setNycd(String nycd) {
		this.nycd = nycd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Twjfafl getFaflid() {
		return faflid;
	}
	public void setFaflid(Twjfafl faflid) {
		this.faflid = faflid;
	}
	public String getWtlx() {
		return wtlx;
	}
	public void setWtlx(String wtlx) {
		this.wtlx = wtlx;
	}
	public String getWtmc() {
		return wtmc;
	}
	public void setWtmc(String wtmc) {
		this.wtmc = wtmc;
	}
	public String getWtmcFileName() {
		return wtmcFileName;
	}
	public void setWtmcFileName(String wtmcFileName) {
		this.wtmcFileName = wtmcFileName;
	}
	public String getWtfssx() {
		return wtfssx;
	}
	public void setWtfssx(String wtfssx) {
		this.wtfssx = wtfssx;
	}
	public String getSfyx() {
		return sfyx;
	}
	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
	}
	public String getJfgzdm() {
		return jfgzdm;
	}
	public void setJfgzdm(String jfgzdm) {
		this.jfgzdm = jfgzdm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Set<Twjwthdx> getHdxlist() {
		return hdxlist;
	}
	public void setHdxlist(Set<Twjwthdx> hdxlist) {
		this.hdxlist = hdxlist;
	}
	public int getSxh() {
		return sxh;
	}
	public void setSxh(int sxh) {
		this.sxh = sxh;
	}
	
	
}