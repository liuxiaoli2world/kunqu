package com.lgsc.kunqu.model;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "drama_part")
@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt"})
public class DramaPart {
    /**
     * 剧典片段编号
     */
    @Id
    @Column(name = "drama_part_id")
    private Long dramaPartId;

    /**
     * 剧典编号
     */
    @Column(name = "drama_id")
    private Long dramaId;

    /**
     * 片段名称
     */
    @Column(name = "part_name")
    private String partName;

    /**
     * 片段链接
     */
    @Column(name = "part_url")
    private String partUrl;

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
     * 获取剧典片段编号
     *
     * @return drama_part_id - 剧典片段编号
     */
    public Long getDramaPartId() {
        return dramaPartId;
    }

    /**
     * 设置剧典片段编号
     *
     * @param dramaPartId 剧典片段编号
     */
    public void setDramaPartId(Long dramaPartId) {
        this.dramaPartId = dramaPartId;
    }

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
     * 获取片段名称
     *
     * @return part_name - 片段名称
     */
    public String getPartName() {
        return partName;
    }

    /**
     * 设置片段名称
     *
     * @param partName 片段名称
     */
    public void setPartName(String partName) {
        this.partName = partName;
    }

    /**
     * 获取片段链接
     *
     * @return part_url - 片段链接
     */
    public String getPartUrl() {
        return partUrl;
    }

    /**
     * 设置片段链接
     *
     * @param partUrl 片段链接
     */
    public void setPartUrl(String partUrl) {
        this.partUrl = partUrl;
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