package org.pfw.framework.wjgl.domain;

import java.util.HashSet;
import java.util.Set;

import org.pfw.framework.domain.IdEntity;



public class Twjjg extends IdEntity implements java.io.Serializable,Cloneable {

	private static final long serialVersionUID = 6179749951115048747L;
	private Twjwt wtid;
	private Twjjgtxr txrid;
	private String wtms;
	private String yhhd;
	private String sfzq;
	private String df;
	private String df2;
	private ContactWebTopic contactWebTopic;
	public ContactWebTopic getContactWebTopic() {
		return contactWebTopic;
	}
	public void setContactWebTopic(ContactWebTopic contactWebTopic) {
		this.contactWebTopic = contactWebTopic;
	}
	private int bz;
	
	public int getBz() {
		return bz;
	}
	public void setBz(int bz) {
		this.bz = bz;
	}
	public String getWtms() {
		return wtms;
	}
	public void setWtms(String wtms) {
		this.wtms = wtms;
	}
	public String getYhhd() {
		return yhhd;
	}
	public void setYhhd(String yhhd) {
		this.yhhd = yhhd;
	}
	public String getSfzq() {
		return sfzq;
	}
	public void setSfzq(String sfzq) {
		this.sfzq = sfzq;
	}
	public Twjwt getWtid() {
		return wtid;
	}
	public void setWtid(Twjwt wtid) {
		this.wtid = wtid;
	}
	public Twjjgtxr getTxrid() {
		return txrid;
	}
	public void setTxrid(Twjjgtxr txrid) {
		this.txrid = txrid;
	}
	public String getDf() {
		return df;
	}
	public void setDf(String df) {
		this.df = df;
	}
	public String getDf2() {
		return df2;
	}
	public void setDf2(String df2) {
		this.df2 = df2;
	}
	
}