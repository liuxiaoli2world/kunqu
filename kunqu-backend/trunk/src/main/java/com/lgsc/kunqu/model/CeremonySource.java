package com.lgsc.kunqu.model;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

@Table(name = "ceremony_source")
@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt","handler"})
public class CeremonySource {
    /**
     * 专题资源关系id
     */
    @Id
    @Column(name = "ceremony_source_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ceremonySourceId;

    /**
     * 大典专题id
     */
    @Column(name = "ceremony_id")
    @ApiModelProperty("大典专题编号不能为空")
    @NotNull(message = "大典专题编号不能为空")
    @Digits(integer = 20 ,fraction = 0)
    private Long ceremonyId;

    /**
     * 资源类型，drama=剧典，special=曲典，article=文章，author=作者
     */
    @Column(name = "source_type")
    @ApiModelProperty("资源类型(drama=剧典，special=曲典，article=文章，author=作者)不能为空,最大长度10")
    @NotBlank(message = "资源类型不能为空")
    @Length(min = 1,max = 10)
    private String sourceType;

    /**
     * 资源id，剧典id或曲典专辑id或文章id或作者id
     */
    @Column(name = "source_id")
    @ApiModelProperty("资源编号不能为空")
    @NotNull(message = "资源编号不能为空")
    @Digits(integer = 20 ,fraction = 0)
    private Long sourceId;

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
     * 获取专题资源关系id
     *
     * @return ceremony_source_id - 专题资源关系id
     */
    public Long getCeremonySourceId() {
        return ceremonySourceId;
    }

    /**
     * 设置专题资源关系id
     *
     * @param ceremonySourceId 专题资源关系id
     */
    public void setCeremonySourceId(Long ceremonySourceId) {
        this.ceremonySourceId = ceremonySourceId;
    }

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
     * 获取资源类型，drama=剧典，special=曲典，article=文章，author=作者
     *
     * @return source_type - 资源类型，drama=剧典，special=曲典，article=文章，author=作者
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * 设置资源类型，drama=剧典，special=曲典，article=文章，author=作者
     *
     * @param sourceType 资源类型，drama=剧典，special=曲典，article=文章，author=作者
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * 获取资源id，剧典id或曲典专辑id或文章id或作者id
     *
     * @return source_id - 资源id，剧典id或曲典专辑id或文章id或作者id
     */
    public Long getSourceId() {
        return sourceId;
    }

    /**
     * 设置资源id，剧典id或曲典专辑id或文章id或作者id
     *
     * @param sourceId 资源id，剧典id或曲典专辑id或文章id或作者id
     */
    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
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