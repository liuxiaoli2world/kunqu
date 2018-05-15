package com.lgsc.kunqu.model;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "drama_about")
@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt"})
public class DramaAbout {
    /**
     * 相关剧典编号
     */
    @Id
    @Column(name = "drama_about_id")
    private Long dramaAboutId;

    /**
     * 剧典
     */
    @Column(name = "drama_id")
    private Long dramaId;

    /**
     * 相关剧典
     */
    @Column(name = "about_id")
    private Long aboutId;

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
     * 获取相关剧典编号
     *
     * @return drama_about_id - 相关剧典编号
     */
    public Long getDramaAboutId() {
        return dramaAboutId;
    }

    /**
     * 设置相关剧典编号
     *
     * @param dramaAboutId 相关剧典编号
     */
    public void setDramaAboutId(Long dramaAboutId) {
        this.dramaAboutId = dramaAboutId;
    }

    /**
     * 获取剧典
     *
     * @return drama_id - 剧典
     */
    public Long getDramaId() {
        return dramaId;
    }

    /**
     * 设置剧典
     *
     * @param dramaId 剧典
     */
    public void setDramaId(Long dramaId) {
        this.dramaId = dramaId;
    }

    /**
     * 获取相关剧典
     *
     * @return about_id - 相关剧典
     */
    public Long getAboutId() {
        return aboutId;
    }

    /**
     * 设置相关剧典
     *
     * @param aboutId 相关剧典
     */
    public void setAboutId(Long aboutId) {
        this.aboutId = aboutId;
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