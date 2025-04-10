package com.ts_voc_back.ts.setting.customer.model.param;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PInsertCompList {
	private List<Map<String, String>> addCompList;

	public List<Map<String, String>> getAddCompList() {
		return addCompList;
	}

	public void setAddCompList(List<Map<String, String>> addCompList) {
		this.addCompList = addCompList;
	}
}
