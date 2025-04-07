package com.ts_voc_back.Post.use.model.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PSelectPostInfo {
	private String compSeq;
	private String postSeq;

	public String getCompSeq() {
		return compSeq;
	}

	public void setCompSeq(String compSeq) {
		this.compSeq = compSeq;
	}
	public String getPostSeq() {
		return postSeq;
	}

	public void setPostSeq(String postSeq) {
		this.postSeq = postSeq;
	}
}
