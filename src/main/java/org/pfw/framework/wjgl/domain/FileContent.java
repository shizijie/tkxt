package org.pfw.framework.wjgl.domain;

import java.io.File;

/** 
 * @author  xl 
 * @date 创建时间：2017年7月5日 下午3:17:50 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class FileContent {
	private File document;
	private String documentFileName;
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
}
