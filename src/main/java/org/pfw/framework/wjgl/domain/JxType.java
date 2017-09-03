package org.pfw.framework.wjgl.domain;

import java.io.Serializable;
import java.util.Set;

import org.pfw.framework.domain.IdEntity;

/** 
 * @author  xl 
 * @date 创建时间：2017年4月10日 下午1:00:34 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class JxType  extends IdEntity implements Serializable{
	private String typeName;
	private String remark;
	private JxType fparent;
	private Set<JxType> children;
	private String jdtype;
	public String getJdtype() {
		return jdtype;
	}
	public void setJdtype(String jdtype) {
		this.jdtype = jdtype;
	}
	public Set<JxType> getChildren() {
		return children;
	}
	public void setChildren(Set<JxType> children) {
		this.children = children;
	}
	public JxType getFparent() {
		return fparent;
	}
	public void setFparent(JxType fparent) {
		this.fparent = fparent;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
