package com.lgsc.kunqu.common.vo;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lgsc.kunqu.model.Drama;

@JsonIgnoreProperties(value = {"handler"})
public class DramaAboutDto {

	/**
	 * 是否为关联剧典
	 */
	@Transient
	private String isAbout;
	
	/**
	 * 剧典
	 */
	@Transient
	private Drama drama;

	public String getIsAbout() {
		return isAbout;
	}

	public void setIsAbout(String isAbout) {
		this.isAbout = isAbout;
	}

	public Drama getDrama() {
		return drama;
	}

	public void setDrama(Drama drama) {
		this.drama = drama;
	}

}
