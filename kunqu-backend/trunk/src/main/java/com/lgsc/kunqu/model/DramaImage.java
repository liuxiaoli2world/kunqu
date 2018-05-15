package com.lgsc.kunqu.model;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

@Table(name = "drama_image")
@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt", "dramaImageId", "dramaId", "handler"})
public class DramaImage {
    /**
     * 剧典图片编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drama_image_id")
    private Long dramaImageId;

    /**
     * 剧典编号
     */
    @ApiModelProperty(value = "剧典编号")
    @Digits(integer = 20, fraction = 0)
    @Column(name = "drama_id")
    private Long dramaId;

    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址，最大长度：300。")
    @NotBlank(message = "图片地址不能为空！")
    @Length(min = 0, max = 300)
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 图片使用场景 01 列表 02 分类 03 最热
     */
    @ApiModelProperty(value = "图片使用场景 01 列表 02 分类 03 最热")
    @NotBlank(message = "图片使用场景不能为空！")
    @Length(min = 0, max = 10)
    @Column(name = "image_scene")
    private String imageScene;

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
     * 获取剧典图片编号
     *
     * @return drama_image_id - 剧典图片编号
     */
    public Long getDramaImageId() {
        return dramaImageId;
    }

    /**
     * 设置剧典图片编号
     *
     * @param dramaImageId 剧典图片编号
     */
    public void setDramaImageId(Long dramaImageId) {
        this.dramaImageId = dramaImageId;
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
     * 获取图片地址
     *
     * @return image_url - 图片地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置图片地址
     *
     * @param imageUrl 图片地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取图片使用场景 01 列表 02 分类 03 最热
     *
     * @return image_scene - 图片使用场景 01 列表 02 分类 03 最热
     */
    public String getImageScene() {
        return imageScene;
    }

    /**
     * 设置图片使用场景 01 列表 02 分类 03 最热
     *
     * @param imageScene 图片使用场景 01 列表 02 分类 03 最热
     */
    public void setImageScene(String imageScene) {
        this.imageScene = imageScene;
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