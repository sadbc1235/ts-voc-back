package com.ts_voc_back.ts.setting.customer.model.param;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PUpdateComp {
	private List<Map<String, String>> updateCompList;
	private String compName;
	private String compSeq;

	public List<Map<String, String>> getUpdateCompList() {
		return updateCompList;
	}
	public void setUpdateCompList(List<Map<String, String>> updateCompList) {
		this.updateCompList = updateCompList;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCompSeq() {
		return compSeq;
	}
	public void setCompSeq(String compSeq) {
		this.compSeq = compSeq;
	}
}
