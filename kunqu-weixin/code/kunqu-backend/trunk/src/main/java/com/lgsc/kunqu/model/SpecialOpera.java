package com.lgsc.kunqu.model;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Table(name = "special_opera")
@ApiModel(value="专辑曲典关系")
@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt"})
public class SpecialOpera {
    /**
     * 专辑曲典关系编号
     */
    @Id
    @Column(name = "special_opera_id")
    @ApiModelProperty("专辑曲典关系编号")
    private Long specialOperaId;

    /**
     * 专辑编号
     */
    @Column(name = "special_id")
    @ApiModelProperty("专辑编号")
    private Long specialId;

    /**
     * 曲典编号
     */
    @Column(name = "opera_id")
    @ApiModelProperty("曲典编号")
    private Long operaId;

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
     * 获取专辑曲典关系编号
     *
     * @return special_opera_id - 专辑曲典关系编号
     */
    public Long getSpecialOperaId() {
        return specialOperaId;
    }

    /**
     * 设置专辑曲典关系编号
     *
     * @param specialOperaId 专辑曲典关系编号
     */
    public void setSpecialOperaId(Long specialOperaId) {
        this.specialOperaId = specialOperaId;
    }

    /**
     * 获取专辑编号
     *
     * @return special_id - 专辑编号
     */
    public Long getSpecialId() {
        return specialId;
    }

    /**
     * 设置专辑编号
     *
     * @param specialId 专辑编号
     */
    public void setSpecialId(Long specialId) {
        this.specialId = specialId;
    }

    /**
     * 获取曲典编号
     *
     * @return opera_id - 曲典编号
     */
    public Long getOperaId() {
        return operaId;
    }

    /**
     * 设置曲典编号
     *
     * @param operaId 曲典编号
     */
    public void setOperaId(Long operaId) {
        this.operaId = operaId;
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