package org.pfw.framework.wjgl.domain;

import java.io.Serializable;

import org.pfw.framework.domain.IdEntity;

public class Jxtx extends IdEntity implements Serializable {
	
	private String txmc;

	public String getTxmc() {
		return txmc;
	}

	public void setTxmc(String txmc) {
		this.txmc = txmc;
	}
	
}
