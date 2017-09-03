package org.pfw.framework.wjgl.domain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.pfw.framework.domain.IdEntity;

/** 
 * @author  xl 
 * @date 创建时间：2017年7月4日 上午11:14:31 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class TopicContent extends IdEntity implements Serializable{
	private ContactWebTopic contactWebTopic;
	private String topicType;
	private String serialNumber;
	private String topicPicture;
	private String oldPicName;
	private String rightAnswers;
	private String isOrder;
	private String topicText;
	private Set<TopicOptions> setTopicOptions=new HashSet<TopicOptions>();
	private List<TopicOptions> listTopicOptions=new ArrayList<TopicOptions>();
	private FileContent file;
	private File document;
	private String documentFileName;
	private String remark;
	private String isHave;
	private String isHaveOld;
	
	public String getIsHaveOld() {
		return isHaveOld;
	}
	public void setIsHaveOld(String isHaveOld) {
		this.isHaveOld = isHaveOld;
	}
	public String getIsHave() {
		return isHave;
	}
	public void setIsHave(String isHave) {
		this.isHave = isHave;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOldPicName() {
		return oldPicName;
	}
	public void setOldPicName(String oldPicName) {
		this.oldPicName = oldPicName;
	}
	public File getDocument() {
		return document;
	}
	public void setDocument(File document) {
		this.document = document;
	}
	public String getDocumentFileName() {
		return documentFileName;
	}
	public void setDocumentFileName(String documentFileName) {
		this.documentFileName = documentFileName;
	}
	public FileContent getFile() {
		return file;
	}
	public void setFile(FileContent file) {
		this.file = file;
	}
	public String getTopicText() {
		return topicText;
	}
	public void setTopicText(String topicText) {
		this.topicText = topicText;
	}
	public List<TopicOptions> getListTopicOptions() {
		return listTopicOptions;
	}
	public void setListTopicOptions(List<TopicOptions> listTopicOptions) {
		this.listTopicOptions = listTopicOptions;
	}
	public String getIsOrder() {
		return isOrder;
	}
	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
	}
	public ContactWebTopic getContactWebTopic() {
		return contactWebTopic;
	}
	public void setContactWebTopic(ContactWebTopic contactWebTopic) {
		this.contactWebTopic = contactWebTopic;
	}
	public String getTopicType() {
		return topicType;
	}
	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getTopicPicture() {
		return topicPicture;
	}
	public void setTopicPicture(String topicPicture) {
		this.topicPicture = topicPicture;
	}
	public String getRightAnswers() {
		return rightAnswers;
	}
	public void setRightAnswers(String rightAnswers) {
		this.rightAnswers = rightAnswers;
	}
	public Set<TopicOptions> getSetTopicOptions() {
		return setTopicOptions;
	}
	public void setSetTopicOptions(Set<TopicOptions> setTopicOptions) {
		this.setTopicOptions = setTopicOptions;
	}
}
