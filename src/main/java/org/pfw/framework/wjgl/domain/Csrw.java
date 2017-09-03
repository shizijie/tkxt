package org.pfw.framework.wjgl.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.pfw.framework.domain.IdEntity;

public class Csrw extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1035895023042978106L;
	private String rwmc;
	private String kssj;
	private String jssj;
	private String kssc;
	private String ksbj;
	private String ksbjmcs;
	private String zjxx;
	private Twjfafl fafl;
	private String score;
	private String jxInfo;
	private String jxScore;
	private String topicInfo="0";
	private String topicScore="0";
	public String getTopicInfo() {
		return topicInfo;
	}
	public void setTopicInfo(String topicInfo) {
		this.topicInfo = topicInfo;
	}
	public String getTopicScore() {
		return topicScore;
	}
	public void setTopicScore(String topicScore) {
		this.topicScore = topicScore;
	}
	private Set<Twjwt> tmlist = new HashSet<Twjwt>();
	private List<Twjwt> list=new ArrayList<Twjwt>();
	private Set<Jxtm> setJxtm=new HashSet<Jxtm>();
	private Set<ContactWebTopic> setContactWebTopic=new HashSet<ContactWebTopic>();
	public Set<ContactWebTopic> getSetContactWebTopic() {
		return setContactWebTopic;
	}
	public void setSetContactWebTopic(Set<ContactWebTopic> setContactWebTopic) {
		this.setContactWebTopic = setContactWebTopic;
	}
	private String testStu;
	public String getTestStu() {
		return testStu;
	}
	public void setTestStu(String testStu) {
		this.testStu = testStu;
	}
	public String getJxScore() {
		return jxScore;
	}
	public void setJxScore(String jxScore) {
		this.jxScore = jxScore;
	}
	public String getJxInfo() {
		return jxInfo;
	}
	public void setJxInfo(String jxInfo) {
		this.jxInfo = jxInfo;
	}
	public Set<Jxtm> getSetJxtm() {
		return setJxtm;
	}
	public void setSetJxtm(Set<Jxtm> setJxtm) {
		this.setJxtm = setJxtm;
	}
	public List<Twjwt> getList() {
		return list;
	}
	public void setList(List<Twjwt> list) {
		this.list = list;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	public Twjfafl getFafl() {
		return fafl;
	}
	public void setFafl(Twjfafl fafl) {
		this.fafl = fafl;
	}
	public String getKsbjmcs() {
		return ksbjmcs;
	}
	public void setKsbjmcs(String ksbjmcs) {
		this.ksbjmcs = ksbjmcs;
	}
	public String getRwmc() {
		return rwmc;
	}
	public void setRwmc(String rwmc) {
		this.rwmc = rwmc;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getKssc() {
		return kssc;
	}
	public void setKssc(String kssc) {
		this.kssc = kssc;
	}
	public String getKsbj() {
		return ksbj;
	}
	public void setKsbj(String ksbj) {
		this.ksbj = ksbj;
	}
	public String getZjxx() {
		return zjxx;
	}
	public void setZjxx(String zjxx) {
		this.zjxx = zjxx;
	}
	public Set<Twjwt> getTmlist() {
		return tmlist;
	}
	public void setTmlist(Set<Twjwt> tmlist) {
		this.tmlist = tmlist;
	}
	
}