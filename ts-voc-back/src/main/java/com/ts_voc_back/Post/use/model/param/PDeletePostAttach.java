package com.ts_voc_back.Post.use.model.param;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PDeletePostAttach {
	private String postSeq;
	private List<String> postAttachSeqList;

	public String getPostSeq() {
		return postSeq;
	}
	public void setPostSeq(String postSeq) {
		this.postSeq = postSeq;
	}
	public List<String> getPostAttachSeqList() {
		return postAttachSeqList;
	}
	public void setPostAttachSeqList(List<String> postAttachSeqList) {
		this.postAttachSeqList = postAttachSeqList;
	}
}
