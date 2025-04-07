package com.ts_voc_back.Post.report.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RSelectPostList {
    private String postSeq;
    private String compSeq;
    private String userSeq;
    private String userName;
    private String moduleSeq;
    private String moduleName;
    private String snNo;
    private String title;
    private String state;
    private String createDt;

    public String getPostSeq() {
        return postSeq;
    }
    public void setPostSeq(String postSeq) {
        this.postSeq = postSeq;
    }
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
	public String getCreateDt() {
        return createDt;
    }
    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }
}
