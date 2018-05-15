package com.lgsc.kunqu.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "剧典")
@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt", "handler"})
public class Drama {
    /**
     * 剧典编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drama_id")
    private Long dramaId;

    /**
     * 剧典名称
     */
    @ApiModelProperty(value = "剧典名称，最大长度：50。")
    @NotBlank(message = "剧典名称不能为空！")
    @Length(min = 0, max = 50)
    @Column(name = "drama_name")
    private String dramaName;

    /**
     * 剧典简介
     */
    @ApiModelProperty(value = "剧典简介，最大长度：500。")
    @Length(min = 0, max = 500)
    @Column(name = "drama_desc")
    private String dramaDesc;

    /**
     * 最热剧典 0 否 1 是
     */
    @ApiModelProperty(value = "最热剧典 0 否 1 是")
    @Digits(integer = 1, fraction = 0)
    @Column(name = "is_hottest")
    private Integer isHottest;

    /**
     * 剧典列表页剧典推荐 0 否 1 是
     */
    @ApiModelProperty(value = "剧典列表页剧典推荐 0 否 1 是")
    @Digits(integer = 1, fraction = 0)
    @Column(name = "is_list_recommend")
    private Integer isListRecommend;

    /**
     * 首页剧典推荐 0 否 1 是
     */
    @ApiModelProperty(value = "首页剧典推荐 0 否 1 是")
    @Digits(integer = 1, fraction = 0)
    @Column(name = "is_index_recommend")
    private Integer isIndexRecommend;

    /**
     * 折子戏 0 否 1 是
     */
    @ApiModelProperty(value = "折子戏 0 否 1 是")
    @Digits(integer = 1, fraction = 0)
    @Column(name = "is_highlight")
    private Integer isHighlight;

    /**
     * 大典专题 0 否 1 是
     */
    @ApiModelProperty(value = "大典专题 0 否 1 是")
    @Digits(integer = 1, fraction = 0)
    @Column(name = "is_subject")
    private Integer isSubject;

    /**
     * 播放量
     */
    @ApiModelProperty(value = "播放量")
    @Digits(integer = 11, fraction = 0)
    @Column(name = "paly_amount")
    private Integer palyAmount;

    /**
     * 年代标签编号
     */
    @ApiModelProperty(value = "年代标签编号")
    @Digits(integer = 20, fraction = 0)
    @Column(name = "age_tag_id")
    private Long ageTagId;

    /**
     * 作者标签编号
     */
    @ApiModelProperty(value = "作者标签编号")
    @Digits(integer = 20, fraction = 0)
    @Column(name = "author_tag_id")
    private Long authorTagId;

    /**
     * 剧目标签编号
     */
    @ApiModelProperty(value = "剧目标签编号")
    @Digits(integer = 20, fraction = 0)
    @Column(name = "repertoire_tag_id")
    private Long repertoireTagId;
    
    /**
     * 角色标签编号
     */
    @ApiModelProperty(value = "角色标签编号")
    @Digits(integer = 20, fraction = 0)
    @Transient
    private Long roleTagId;

    /**
     * 目录编号
     */
    @ApiModelProperty(value = "目录编号")
    @Digits(integer = 20, fraction = 0)
    @Column(name = "dir_id")
    private Long dirId;

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
    
    /**
     * 剧典图片
     */
    @ApiModelProperty(value = "剧典图片")
    @NotEmpty(message = "剧典图片不能为空！")
    @Transient
    private List<DramaImage> dramaImages;
    
    /**
     * 剧典资源
     */
    @ApiModelProperty(value = "剧典资源")
//    @NotEmpty(message = "剧典资源不能为空！")
    @Transient    
    private List<DramaPart> dramaParts;
    
    
    /**
     * 是否关某联专题
     */
    @ApiModelProperty(value = "是否关联某专题")
    @Transient   
    private boolean isRelative;
  
	/**
     * 获取剧典编号
     *
     * @return drama_id - 剧典编号
     */
    public Long getDramaId() {
        return dramaId;
    }

    /**
     * 设置剧典编号
     *
     * @param dramaId 剧典编号
     */
    public void setDramaId(Long dramaId) {
        this.dramaId = dramaId;
    }

    /**
     * 获取剧典名称
     *
     * @return drama_name - 剧典名称
     */
    public String getDramaName() {
        return dramaName;
    }

    /**
     * 设置剧典名称
     *
     * @param dramaName 剧典名称
     */
    public void setDramaName(String dramaName) {
        this.dramaName = dramaName;
    }

    /**
     * 获取剧典简介
     *
     * @return drama_desc - 剧典简介
     */
    public String getDramaDesc() {
        return dramaDesc;
    }

    /**
     * 设置剧典简介
     *
     * @param dramaDesc 剧典简介
     */
    public void setDramaDesc(String dramaDesc) {
        this.dramaDesc = dramaDesc;
    }

    /**
     * 获取最热剧典 0 否 1 是
     *
     * @return is_hottest - 最热剧典 0 否 1 是
     */
    public Integer getIsHottest() {
        return isHottest;
    }

    /**
     * 设置最热剧典 0 否 1 是
     *
     * @param isHottest 最热剧典 0 否 1 是
     */
    public void setIsHottest(Integer isHottest) {
        this.isHottest = isHottest;
    }

    /**
     * 获取剧典列表页剧典推荐 0 否 1 是
     *
     * @return is_list_recommend - 剧典列表页剧典推荐 0 否 1 是
     */
    public Integer getIsListRecommend() {
        return isListRecommend;
    }

    /**
     * 设置剧典列表页剧典推荐 0 否 1 是
     *
     * @param isListRecommend 剧典列表页剧典推荐 0 否 1 是
     */
    public void setIsListRecommend(Integer isListRecommend) {
        this.isListRecommend = isListRecommend;
    }

    /**
     * 获取首页剧典推荐 0 否 1 是
     *
     * @return is_index_recommend - 首页剧典推荐 0 否 1 是
     */
    public Integer getIsIndexRecommend() {
        return isIndexRecommend;
    }

    /**
     * 设置首页剧典推荐 0 否 1 是
     *
     * @param isIndexRecommend 首页剧典推荐 0 否 1 是
     */
    public void setIsIndexRecommend(Integer isIndexRecommend) {
        this.isIndexRecommend = isIndexRecommend;
    }

    /**
     * 获取折子戏 0 否 1 是
     *
     * @return is_highlight - 折子戏 0 否 1 是
     */
    public Integer getIsHighlight() {
        return isHighlight;
    }

    /**
     * 设置折子戏 0 否 1 是
     *
     * @param isHighlight 折子戏 0 否 1 是
     */
    public void setIsHighlight(Integer isHighlight) {
        this.isHighlight = isHighlight;
    }

    /**
     * 获取大典专题 0 否 1 是
     *
     * @return is_subject - 大典专题 0 否 1 是
     */
    public Integer getIsSubject() {
        return isSubject;
    }

    /**
     * 设置大典专题 0 否 1 是
     *
     * @param isSubject 大典专题 0 否 1 是
     */
    public void setIsSubject(Integer isSubject) {
        this.isSubject = isSubject;
    }

    /**
     * 获取播放量
     *
     * @return paly_amount - 播放量
     */
    public Integer getPalyAmount() {
        return palyAmount;
    }

    /**
     * 设置播放量
     *
     * @param palyAmount 播放量
     */
    public void setPalyAmount(Integer palyAmount) {
        this.palyAmount = palyAmount;
    }

    /**
     * 获取年代标签编号
     *
     * @return age_tag_id - 年代标签编号
     */
    public Long getAgeTagId() {
        return ageTagId;
    }

    /**
     * 设置年代标签编号
     *
     * @param ageTagId 年代标签编号
     */
    public void setAgeTagId(Long ageTagId) {
        this.ageTagId = ageTagId;
    }

    /**
     * 获取作者标签编号
     *
     * @return author_tag_id - 作者标签编号
     */
    public Long getAuthorTagId() {
        return authorTagId;
    }

    /**
     * 设置作者标签编号
     *
     * @param authorTagId 作者标签编号
     */
    public void setAuthorTagId(Long authorTagId) {
        this.authorTagId = authorTagId;
    }

    /**
     * 获取剧目标签编号
     *
     * @return repertoire_tag_id - 剧目标签编号
     */
    public Long getRepertoireTagId() {
        return repertoireTagId;
    }

    /**
     * 设置剧目标签编号
     *
     * @param repertoireTagId 剧目标签编号
     */
    public void setRepertoireTagId(Long repertoireTagId) {
        this.repertoireTagId = repertoireTagId;
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

	public List<DramaImage> getDramaImages() {
		return dramaImages;
	}

	public void setDramaImages(List<DramaImage> dramaImages) {
		this.dramaImages = dramaImages;
	}

	public Long getRoleTagId() {
		return roleTagId;
	}

	public void setRoleTagId(Long roleTagId) {
		this.roleTagId = roleTagId;
	}

	public List<DramaPart> getDramaParts() {
		return dramaParts;
	}

	public void setDramaParts(List<DramaPart> dramaParts) {
		this.dramaParts = dramaParts;
	}
	
	public boolean getIsRelative() {
		return isRelative;
	}

	public void setIsRelative(boolean isRelative) {
		this.isRelative = isRelative;
	}

	@Override
	public String toString() {
		return "Drama [dramaId=" + dramaId + ", dramaName=" + dramaName + ", dramaImages=" + dramaImages + "]";
	}
	
}