package org.pfw.framework.wjgl.domain;

import java.io.Serializable;

import org.pfw.framework.domain.IdEntity;

public class Jxtm extends IdEntity implements Serializable {
	
	private Jxtx txid;
	private String tmms;
	private String tmFileName;
	private String dan;
	private String shuang;
	private String dantu;
	private String shuangtu;
	private JxType type;
	private String width;
	private String height;
	private String danwidth;
	private String danheight;
	private String shuangwidth;
	private String shuangheight;
	
	
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getDanwidth() {
		return danwidth;
	}
	public void setDanwidth(String danwidth) {
		this.danwidth = danwidth;
	}
	public String getDanheight() {
		return danheight;
	}
	public void setDanheight(String danheight) {
		this.danheight = danheight;
	}
	public String getShuangwidth() {
		return shuangwidth;
	}
	public void setShuangwidth(String shuangwidth) {
		this.shuangwidth = shuangwidth;
	}
	public String getShuangheight() {
		return shuangheight;
	}
	public void setShuangheight(String shuangheight) {
		this.shuangheight = shuangheight;
	}
	public JxType getType() {
		return type;
	}
	public void setType(JxType type) {
		this.type = type;
	}
	public String getDan() {
		return dan;
	}
	public void setDan(String dan) {
		this.dan = dan;
	}
	public String getShuang() {
		return shuang;
	}
	public void setShuang(String shuang) {
		this.shuang = shuang;
	}
	public String getDantu() {
		return dantu;
	}
	public void setDantu(String dantu) {
		this.dantu = dantu;
	}
	public String getShuangtu() {
		return shuangtu;
	}
	public void setShuangtu(String shuangtu) {
		this.shuangtu = shuangtu;
	}
	public String getTmms() {
		return tmms;
	}
	public void setTmms(String tmms) {
		this.tmms = tmms;
	}
	public String getTmFileName() {
		return tmFileName;
	}
	public void setTmFileName(String tmFileName) {
		this.tmFileName = tmFileName;
	}
	public Jxtx getTxid() {
		return txid;
	}
	public void setTxid(Jxtx txid) {
		this.txid = txid;
	}
	
}
