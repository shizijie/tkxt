package org.pfw.framework.wjgl.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.pfw.framework.domain.IdEntity;

/** 
 * @author  xl 
 * @date 创建时间：2017年7月4日 上午11:08:34 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class ContactWebTopic extends IdEntity implements Serializable {
	private String topicName;
	private Twjfafl twjfafl;
	private String creationTime;
	private String textField;
	private String complexity;
	private String knowledgePoint;
	private String status;
	private String topicType="综合题";
	private Set<TopicContent> setTopicContent=new HashSet<TopicContent>();
	private List<TopicContent> listTopicContent=new ArrayList<TopicContent>();
	public String getTopicType() {
		return topicType;
	}
	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}
	public List<TopicContent> getListTopicContent() {
		return listTopicContent;
	}
	public void setListTopicContent(List<TopicContent> listTopicContent) {
		this.listTopicContent = listTopicContent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComplexity() {
		return complexity;
	}
	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}
	public String getKnowledgePoint() {
		return knowledgePoint;
	}
	public void setKnowledgePoint(String knowledgePoint) {
		this.knowledgePoint = knowledgePoint;
	}
	public Twjfafl getTwjfafl() {
		return twjfafl;
	}
	public void setTwjfafl(Twjfafl twjfafl) {
		this.twjfafl = twjfafl;
	}
	public String getTextField() {
		return textField;
	}
	public void setTextField(String textField) {
		this.textField = textField;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public Set<TopicContent> getSetTopicContent() {
		return setTopicContent;
	}
	public void setSetTopicContent(Set<TopicContent> setTopicContent) {
		this.setTopicContent = setTopicContent;
	}
}
