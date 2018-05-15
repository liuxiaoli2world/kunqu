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

@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt", "handler"})
@ApiModel(value="曲典专辑")
public class Special {
    /**
     * 曲典专辑编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "special_id")
    @ApiModelProperty("曲典专辑编号")
    private Long specialId;

    /**
     * 曲典专辑名称
     */
    @ApiModelProperty("曲典专辑名称，最大长度：50。")
    @NotBlank(message = "曲典专辑名称不能为空！")
    @Length(min = 0, max = 50)
    @Column(name = "special_name")
    private String specialName;

    /**
     * 来源
     */
    @ApiModelProperty("来源，最大长度：20。")
    @Length(min = 0, max = 20)
    private String source;

    /**
     * 曲典专辑介绍
     */
    @ApiModelProperty("曲典专辑介绍，最大长度：500。")
    @Length(min = 0, max = 500)
    @Column(name = "special_desc")
    private String specialDesc;

    /**
     * 目录链接
     */
    @ApiModelProperty("目录链接，最大长度：500。")
    @Length(min = 0, max = 500)
    @Column(name = "dir_url")
    private String dirUrl;

    /**
     * 首页曲典推荐 0 否 1 是
     */
    @ApiModelProperty("首页曲典推荐 0 否 1 是")
    @Digits(integer = 1, fraction = 0)
    @Column(name = "is_index_recommend")
    private Integer isIndexRecommend;

    /**
     * 大典专题 0 否 1 是
     */
    @ApiModelProperty("曲典推荐 0 否 1 是")
    @Digits(integer = 1, fraction = 0)
    @Column(name = "is_subject")
    private Integer isSubject;

    /**
     * 喜欢人数
     */
    @ApiModelProperty("喜欢人数")
    @Digits(integer = 11, fraction = 0)
    @Column(name = "like_amount")
    private Integer likeAmount;

    /**
     * 播放量
     */
    @ApiModelProperty("播放量")
    @Digits(integer = 11, fraction = 0)
    @Column(name = "paly_amount")
    private Integer palyAmount;
    
    /**
     * 年代标签编号
     */
    @ApiModelProperty("年代标签编号")
    @Digits(integer = 20, fraction = 0)
    @Column(name = "age_tag_id")
    private Long ageTagId;

    /**
     * 作者标签编号
     */
    @ApiModelProperty("作者标签编号")
    @Digits(integer = 20, fraction = 0)
    @Column(name = "author_tag_id")
    private Long authorTagId;

    /**
     * 剧目标签编号
     */
    @ApiModelProperty("剧目标签编号")
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
     * 曲典列表
     */
    @ApiModelProperty(value = "曲典列表")
    @NotEmpty(message = "曲典列表不能为空！")
    @Transient
    private List<Opera> operaList;
    
    /**
     * 曲典图片
     */
    @ApiModelProperty(value = "曲典图片")
    @NotEmpty(message = "曲典图片不能为空！")
    @Transient
    private List<SpecialImage> imageList;
    
    /**
     * 获取曲典专辑编号
     *
     * @return special_id - 曲典专辑编号
     */
    public Long getSpecialId() {
        return specialId;
    }

    /**
     * 设置曲典专辑编号
     *
     * @param specialId 曲典专辑编号
     */
    public void setSpecialId(Long specialId) {
        this.specialId = specialId;
    }

    /**
     * 获取曲典专辑名称
     *
     * @return special_name - 曲典专辑名称
     */
    public String getSpecialName() {
        return specialName;
    }

    /**
     * 设置曲典专辑名称
     *
     * @param specialName 曲典专辑名称
     */
    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }

    /**
     * 获取来源
     *
     * @return source - 来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置来源
     *
     * @param source 来源
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取曲典专辑介绍
     *
     * @return special_desc - 曲典专辑介绍
     */
    public String getSpecialDesc() {
        return specialDesc;
    }

    /**
     * 设置曲典专辑介绍
     *
     * @param specialDesc 曲典专辑介绍
     */
    public void setSpecialDesc(String specialDesc) {
        this.specialDesc = specialDesc;
    }

    /**
     * 获取目录链接
     *
     * @return dir_url - 目录链接
     */
    public String getDirUrl() {
        return dirUrl;
    }

    /**
     * 设置目录链接
     *
     * @param dirUrl 目录链接
     */
    public void setDirUrl(String dirUrl) {
        this.dirUrl = dirUrl;
    }

    /**
     * 获取首页曲典推荐 0 否 1 是
     *
     * @return is_index_recommend - 首页曲典推荐 0 否 1 是
     */
    public Integer getIsIndexRecommend() {
        return isIndexRecommend;
    }

    /**
     * 设置首页曲典推荐 0 否 1 是
     *
     * @param isIndexRecommend 首页曲典推荐 0 否 1 是
     */
    public void setIsIndexRecommend(Integer isIndexRecommend) {
        this.isIndexRecommend = isIndexRecommend;
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
     * 获取喜欢人数
     *
     * @return like_amount - 喜欢人数
     */
    public Integer getLikeAmount() {
        return likeAmount;
    }

    /**
     * 设置喜欢人数
     *
     * @param likeAmount 喜欢人数
     */
    public void setLikeAmount(Integer likeAmount) {
        this.likeAmount = likeAmount;
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

	public List<Opera> getOperaList() {
		return operaList;
	}

	public void setOperaList(List<Opera> operaList) {
		this.operaList = operaList;
	}

	public List<SpecialImage> getImageList() {
		return imageList;
	}

	public void setImageList(List<SpecialImage> imageList) {
		this.imageList = imageList;
	}

	public Long getRoleTagId() {
		return roleTagId;
	}

	public void setRoleTagId(Long roleTagId) {
		this.roleTagId = roleTagId;
	}
	
}