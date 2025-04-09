package com.ts_voc_back.common.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RSelectCompList {
	private String compSeq;
	private String compName;

	public String getCompSeq() {
		return compSeq;
	}
	public void setCompSeq(String compSeq) {
		this.compSeq = compSeq;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
}
