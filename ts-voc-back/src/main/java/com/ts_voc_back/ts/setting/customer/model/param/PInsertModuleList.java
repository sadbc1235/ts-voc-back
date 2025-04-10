package com.ts_voc_back.ts.setting.customer.model.param;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PInsertModuleList {
	private List<Map<String, String>> addModuleList;
	private String compSeq;

	public List<Map<String, String>> getAddModuleList() {
		return addModuleList;
	}

	public void setAddModuleList(List<Map<String, String>> addModuleList) {
		this.addModuleList = addModuleList;
	}
	public String getCompSeq() {
		return compSeq;
	}

	public void setCompSeq(String compSeq) {
		this.compSeq = compSeq;
	}
}
