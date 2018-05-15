package com.lgsc.kunqu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "曲典图片关系")
@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt", "specialImageId", "specialId", "handler"})
public class SpecialImage {
	
	@ApiModelProperty(value = "曲典图片编号")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "special_image_id")
	private Long specialImageId;

	@ApiModelProperty(value = "曲典编号")
	@Digits(integer = 20, fraction = 0)
	@Column(name = "special_id")
	private Long specialId;

	@ApiModelProperty(value = "图片地址，最大长度：300。")
	@NotBlank(message = "图片地址不能为空！")
    @Length(min = 0, max = 300)
	@Column(name = "image_url")
	private String imageUrl;

	@ApiModelProperty(value = "曲典图片使用场景  01 列表 02 分类 03 详情专辑")
	@NotBlank(message = "曲典图片使用场景不能为空！")
    @Length(min = 0, max = 10)
	@Column(name = "image_scene")
	private String imageScene;
	
	/**
     * 创建人
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 最后修改人
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 最后修改时间
     */
    @ApiModelProperty(hidden = true)
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
