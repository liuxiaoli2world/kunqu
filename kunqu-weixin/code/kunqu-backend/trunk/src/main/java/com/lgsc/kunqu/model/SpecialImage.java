package com.lgsc.kunqu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

@ApiModel(value = "曲典图片关系")
@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt", "handler"})
public class SpecialImage {
	
	@Id
	@ApiParam(value = "曲典图片编号")
	@Column(name = "special_image_id")
	private Long specialImageId;

	@ApiParam(value = "曲典编号")
	@Column(name = "special_id")
	private Long specialId;

	@ApiParam(value = "图片地址")
	@Column(name = "image_url")
	private String imageUrl;

	@ApiParam(value = "曲典图片使用场景  01 列表 02 分类 03 详情专辑")
	@Column(name = "image_scene")
	private String imageScene;
	/**
     * 创建人
     */
    @Column(name = "created_by")
    private String createdBy;
	
	/**
	 * 创建时间
	 */
	@Column(name = "created_at")
	private Date createdAt;

	/**
	 * 最后修改人
	 */
	@Column(name = "updated_by")
	private String updatedBy;

	/**
	 * 最后修改时间
	 */
	@Column(name = "updated_at")
	private Date updatedAt;
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getSpecialImageId() {
		return specialImageId;
	}

	public void setSpecialImageId(Long specialImageId) {
		this.specialImageId = specialImageId;
	}

	public Long getSpecialId() {
		return specialId;
	}

	public void setSpecialId(Long specialId) {
		this.specialId = specialId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageScene() {
		return imageScene;
	}

	public void setImageScene(String imageScene) {
		this.imageScene = imageScene;
	}
    
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	

}
