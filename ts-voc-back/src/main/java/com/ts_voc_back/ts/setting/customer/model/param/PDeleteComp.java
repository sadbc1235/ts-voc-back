package com.ts_voc_back.ts.setting.customer.model.param;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PDeleteComp {
	private String compSeq;

	public String getCompSeq() {
		return compSeq;
	}
	public void setCompSeq(String compSeq) {
		this.compSeq = compSeq;
	}
}
