package com.lgsc.kunqu.dto;

import com.lgsc.kunqu.model.Ceremony;

public class CeremonyDTO extends Ceremony {
	
	private Integer dramaCount;
	private Integer specialCount;
	private Integer articleCount;
	
	
	public Integer getDramaCount() {
		return dramaCount;
	}
	public void setDramaCount(Integer dramaCount) {
		this.dramaCount = dramaCount;
	}
	public Integer getSpecialCount() {
		return specialCount;
	}
	public void setSpecialCount(Integer specialCount) {
		this.specialCount = specialCount;
	}
	public Integer getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}
	
	

}
