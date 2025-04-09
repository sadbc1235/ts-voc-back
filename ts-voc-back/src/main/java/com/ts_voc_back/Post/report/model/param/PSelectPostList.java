package com.ts_voc_back.Post.report.model.param;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PSelectPostList {
	private String compSeq;
	private String userSeq;
	private String moduleSeq;
	private String searchStr;
	private String type;

	public String getCompSeq() {
		return compSeq;
	}
	public void setCompSeq(String compSeq) {
		this.compSeq = compSeq;
	}
	public String getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}
	public String getModuleSeq() {
		return moduleSeq;
	}
	public void setModuleSeq(String moduleSeq) {
		this.moduleSeq = moduleSeq;
	}
	public String getSearchStr() {
		return searchStr;
	}
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
