package com.lgsc.kunqu.model;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Table(name = "role_tag_article")
@ApiModel(value = "角色文章关系")
@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt"})
public class RoleTagArticle {
    /**
     * 角色文章关系
     */
    @Id
    @Column(name = "role_tag_article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "角色文章关系编号")
    private Long roleTagArticleId;

    /**
     * 角色标签编号
     */
    @Column(name = "role_tag_id")
    @ApiModelProperty(value = "角色标签编号", required = true)
    private Long roleTagId;

    /**
     * 文章编号
     */
    @Column(name = "article_id")
    @ApiModelProperty(value = "文章编号", required = true)
    private Long articleId;

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
     * 获取角色文章关系
     *
     * @return role_tag_article_id - 角色文章关系
     */
    public Long getRoleTagArticleId() {
        return roleTagArticleId;
    }

    /**
     * 设置角色文章关系
     *
     * @param roleTagArticleId 角色文章关系
     */
    public void setRoleTagArticleId(Long roleTagArticleId) {
        this.roleTagArticleId = roleTagArticleId;
    }

    /**
     * 获取角色标签编号
     *
     * @return role_tag_id - 角色标签编号
     */
    public Long getRoleTagId() {
        return roleTagId;
    }

    /**
     * 设置角色标签编号
     *
     * @param roleTagId 角色标签编号
     */
    public void setRoleTagId(Long roleTagId) {
        this.roleTagId = roleTagId;
    }

    /**
     * 获取文章编号
     *
     * @return article_id - 文章编号
     */
    public Long getArticleId() {
        return articleId;
    }

    /**
     * 设置文章编号
     *
     * @param articleId 文章编号
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
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