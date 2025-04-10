package com.ts_voc_back.ts.setting.customer.model.param;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PUpdateModule {

	private List<Map<String, String>> updateModuleList;
	private String moduleName;
	private String moduleSeq;

	public List<Map<String, String>> getUpdateModuleList() {
		return updateModuleList;
	}
	public void setUpdateModuleList(List<Map<String, String>> updateModuleList) {
		this.updateModuleList = updateModuleList;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleSeq() {
		return moduleSeq;
	}
	public void setModuleSeq(String moduleSeq) {
		this.moduleSeq = moduleSeq;
	}
}
