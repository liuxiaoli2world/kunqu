package com.lgsc.kunqu.model;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

@Table(name = "ceremony")
@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt","handler"})
public class Ceremony {
	
    /**
     * 大典专题id
     */
    @Id
    @Column(name = "ceremony_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ceremonyId;

    /**
     * 专题名称
     */
    @Column(name = "ceremony_name")
    @ApiModelProperty("专题名称不能为空,最大长度100")
    @NotBlank(message = "专题名称不能为空")
    @Length(min = 1,max = 100)
    private String ceremonyName;

    /**
     * 封面地址
     */
    @Column(name = "cover_img")
    @ApiModelProperty("封面地址不能为空,最大长度300")
    @NotBlank(message = "封面地址不能为空")
    @Length(min = 1,max = 300)
    private String coverImg;

    /**
     * 专题介绍
     */
    @Column(name = "ceremony_desc")
    private String ceremonyDesc;

    /**
     * 是否首页推荐 0=否 1=是
     */
    @Column(name = "is_index_recommend")
    @ApiModelProperty("是否首页推荐不能为空")
    @NotNull(message = "是否首页推荐不能为空")
    private Integer isIndexRecommend;

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

    /**
     * 获取大典专题id
     *
     * @return ceremony_id - 大典专题id
     */
    public Long getCeremonyId() {
        return ceremonyId;
    }

    /**
     * 设置大典专题id
     *
     * @param ceremonyId 大典专题id
     */
    public void setCeremonyId(Long ceremonyId) {
        this.ceremonyId = ceremonyId;
    }

    /**
     * 获取专题名称
     *
     * @return ceremony_name - 专题名称
     */
    public String getCeremonyName() {
        return ceremonyName;
    }

    /**
     * 设置专题名称
     *
     * @param ceremonyName 专题名称
     */
    public void setCeremonyName(String ceremonyName) {
        this.ceremonyName = ceremonyName;
    }

    /**
     * 获取封面地址
     *
     * @return cover_img - 封面地址
     */
    public String getCoverImg() {
        return coverImg;
    }

    /**
     * 设置封面地址
     *
     * @param coverImg 封面地址
     */
    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    /**
     * 获取专题介绍
     *
     * @return ceremony_desc - 专题介绍
     */
    public String getCeremonyDesc() {
        return ceremonyDesc;
    }

    /**
     * 设置专题介绍
     *
     * @param ceremonyDesc 专题介绍
     */
    public void setCeremonyDesc(String ceremonyDesc) {
        this.ceremonyDesc = ceremonyDesc;
    }

    /**
     * 获取是否首页推荐 0=否 1=是
     *
     * @return is_index_recommend - 是否首页推荐 0=否 1=是
     */
    public Integer getIsIndexRecommend() {
        return isIndexRecommend;
    }

    /**
     * 设置是否首页推荐 0=否 1=是
     *
     * @param isIndexRecommend 是否首页推荐 0=否 1=是
     */
    public void setIsIndexRecommend(Integer isIndexRecommend) {
        this.isIndexRecommend = isIndexRecommend;
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