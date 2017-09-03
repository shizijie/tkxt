package org.pfw.framework.wjgl.domain;

import java.io.File;
import java.io.Serializable;

import org.pfw.framework.domain.IdEntity;

/** 
 * @author  xl 
 * @date 创建时间：2017年7月4日 上午11:23:02 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class TopicOptions extends IdEntity implements Serializable{
	private TopicContent topicContent;
	private String serialNumber;
	private String optionContent;
	private String optionPicture;
	private String oldPicName;
	private File document;
	private String documentFileName;
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
	public TopicContent getTopicContent() {
		return topicContent;
	}
	public void setTopicContent(TopicContent topicContent) {
		this.topicContent = topicContent;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getOptionContent() {
		return optionContent;
	}
	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}
	public String getOptionPicture() {
		return optionPicture;
	}
	public void setOptionPicture(String optionPicture) {
		this.optionPicture = optionPicture;
	}
}
