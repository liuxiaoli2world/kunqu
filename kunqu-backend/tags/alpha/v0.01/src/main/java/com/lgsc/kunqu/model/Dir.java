package com.lgsc.kunqu.model;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "目录")
@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt"})
public class Dir {
    /**
     * 目录编号
     */
    @Id
    @Column(name = "dir_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "目录编号")
    private Long dirId;

    /**
     * 父目录编号
     */
    @Column(name = "parent_id")
    @ApiModelProperty(value = "父目录编号", required = true)
    private Long parentId;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    /**
     * 是否拥有叶子节点 0 否 1 是
     */
    @Column(name = "has_leaf")
    @ApiModelProperty(value = "是否拥有叶子节点 0 否 1 是", required = true)
    private Integer hasLeaf;

    /**
     * 内容类型 overview 概述 thesis 典论
     */
    @Column(name = "content_type")
    @ApiModelProperty(value = "内容类型 overview 概述 thesis 典论", required = false)
    private String contentType;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    @ApiModelProperty(hidden = true)
    private String createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    @ApiModelProperty(hidden = true)
    private Date createdAt;

    /**
     * 最后修改人
     */
    @Column(name = "updated_by")
    @ApiModelProperty(hidden = true)
    private String updatedBy;

    /**
     * 最后修改时间
     */
    @Column(name = "updated_at")
    @ApiModelProperty(hidden = true)
    private Date updatedAt;

    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址", required = false)
    @Column(name = "image_url")
    private String imageUrl;
    
    /**
     * 获取图片地址
     */
    public String getImageUrl() {
		return imageUrl;
	}

    /**
     * 设置图片地址
     */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
     * 获取目录编号
     *
     * @return dir_id - 目录编号
     */
    public Long getDirId() {
        return dirId;
    }

    /**
     * 设置目录编号
     *
     * @param dirId 目录编号
     */
    public void setDirId(Long dirId) {
        this.dirId = dirId;
    }

    /**
     * 获取父目录编号
     *
     * @return parent_id - 父目录编号
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父目录编号
     *
     * @param parentId 父目录编号
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取是否拥有叶子节点 0 否 1 是
     *
     * @return has_leaf - 是否拥有叶子节点 0 否 1 是
     */
    public Integer getHasLeaf() {
        return hasLeaf;
    }

    /**
     * 设置是否拥有叶子节点 0 否 1 是
     *
     * @param hasLeaf 是否拥有叶子节点 0 否 1 是
     */
    public void setHasLeaf(Integer hasLeaf) {
        this.hasLeaf = hasLeaf;
    }

    /**
     * 获取内容类型 overview 概述 thesis 典论
     *
     * @return content_type - 内容类型 overview 概述 thesis 典论
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * 设置内容类型 overview 概述 thesis 典论
     *
     * @param contentType 内容类型 overview 概述 thesis 典论
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取创建时间
     *
     * @return created_at - 创建时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置创建时间
     *
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取最后修改人
     *
     * @return updated_by - 最后修改人
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置最后修改人
     *
     * @param updatedBy 最后修改人
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取最后修改时间
     *
     * @return updated_at - 最后修改时间
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置最后修改时间
     *
     * @param updatedAt 最后修改时间
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}