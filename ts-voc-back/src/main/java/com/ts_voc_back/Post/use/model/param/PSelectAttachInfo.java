package com.ts_voc_back.Post.use.model.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PSelectAttachInfo {
	private String postAttachSeq;

	public String getPostAttachSeq() {
		return postAttachSeq;
	}

	public void setPostAttachSeq(String postAttachSeq) {
		this.postAttachSeq = postAttachSeq;
	}
}
