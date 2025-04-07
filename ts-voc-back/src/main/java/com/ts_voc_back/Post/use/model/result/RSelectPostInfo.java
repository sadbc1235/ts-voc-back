package com.ts_voc_back.Post.use.model.result;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RSelectPostInfo {
	private String userSeq;
	private String userName;
	private String moduleSeq;
	private String moduleName;
	private String snNo;
	private String title;
	private String state;
	private String content;
	private String createDt;
	private List<RSelectPostAttachList> attachList;
	private boolean isMyPost;

	public String getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getModuleSeq() {
		return moduleSeq;
	}
	public void setModuleSeq(String moduleSeq) {
		this.moduleSeq = moduleSeq;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getSnNo() {
		return snNo;
	}
	public void setSnNo(String snNo) {
		this.snNo = snNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	public List<RSelectPostAttachList> getAttachList() {
		return attachList;
	}
	public void setAttachList(List<RSelectPostAttachList> attachList) {
		this.attachList = attachList;
	}
	public boolean isMyPost() {
		return isMyPost;
	}
	public void setIsMyPost(boolean isMyPost) {
		this.isMyPost = isMyPost;
	}
}
