package com.lgsc.kunqu.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
@JsonIgnoreProperties(value = {"handler"})
public class SpecialWeiChat {
	/**
	 * 曲典专辑编号
	 */
	@Id
	@Column(name = "special_id")
	@ApiModelProperty("曲典专辑编号")
	private Long specialId;
	/**
	 * 曲典专辑名称
	 */
	@ApiModelProperty("曲典专辑名称")
	@Column(name = "special_name")
	private String specialName;
	/**
	 * 曲典专辑介绍
	 */
	@ApiModelProperty("曲典专辑介绍")
	@Column(name = "special_desc")
	private String specialDesc;
	/**
	 * 微信播放封面
	 */
	@ApiModelProperty("微信播放封面")
	private String imageUrl;
	/**
	 * 曲典列表
	 */
	@Transient
	private List<Opera> operaList;

	public Long getSpecialId() {
		return specialId;
	}
	public void setSpecialId(Long specialId) {
		this.specialId = specialId;
	}
	public String getSpecialName() {
		return specialName;
	}
	public void setSpecialName(String specialName) {
		this.specialName = specialName;
	}
	public String getSpecialDesc() {
		return specialDesc;
	}
	public void setSpecialDesc(String specialDesc) {
		this.specialDesc = specialDesc;
	}
	public List<Opera> getOperaList() {
		return operaList;
	}
	public void setOperaList(List<Opera> operaList) {
		this.operaList = operaList;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
		
	
}
