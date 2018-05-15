package com.lgsc.kunqu.model;

import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "概述")
@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt"})
public class Overview {
    /**
     * 概述编号
     */
    @Id
    @Column(name = "overview_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "概述编号")
    private Long overviewId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", required = true)
    private String title;

    /**
     * 作者
     */
    @ApiModelProperty(value = "作者", required = false)
    private String author;

    /**
     * 来源
     */
    @ApiModelProperty(value = "来源", required = false)
    private String source;

    /**
     * 发布时间
     */
    @Column(name = "release_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发布时间", required = false)
    private Date releaseDate;

    /**
     * 目录编号
     */
    @Column(name = "dir_id")
    @ApiModelProperty(value = "目录编号", required = false)
    private Long dirId;

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
     * 内容
     */
    @ApiModelProperty(value = "内容", required = true)
    private String content;

    /**
     * 获取概述编号
     *
     * @return overview - 概述编号
     */
    public Long getOverviewId() {
        return overviewId;
    }

    /**
     * 设置概述编号
     *
     * @param overview 概述编号
     */
    public void setOverviewId(Long overviewId) {
        this.overviewId = overviewId;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
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
     * 获取发布时间
     *
     * @return release_date - 发布时间
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * 设置发布时间
     *
     * @param releaseDate 发布时间
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}