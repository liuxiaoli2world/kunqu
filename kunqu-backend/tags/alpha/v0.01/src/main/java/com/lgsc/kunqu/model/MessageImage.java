package com.lgsc.kunqu.model;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "message_image")
@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt", "handler"})
public class MessageImage {
    /**
     * 留言图片编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_image_id")
    private Long messageImageId;

    /**
     * 留言编号
     */
    @Column(name = "message_board_id")
    private Long messageBoardId;

    /**
     * 留言图片
     */
    @Column(name = "message_image")
    private String messageImage;

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
     * 获取留言图片编号
     *
     * @return message_image_id - 留言图片编号
     */
    public Long getMessageImageId() {
        return messageImageId;
    }

    /**
     * 设置留言图片编号
     *
     * @param messageImageId 留言图片编号
     */
    public void setMessageImageId(Long messageImageId) {
        this.messageImageId = messageImageId;
    }

    /**
     * 获取留言编号
     *
     * @return message_board_id - 留言编号
     */
    public Long getMessageBoardId() {
        return messageBoardId;
    }

    /**
     * 设置留言编号
     *
     * @param messageBoardId 留言编号
     */
    public void setMessageBoardId(Long messageBoardId) {
        this.messageBoardId = messageBoardId;
    }

    /**
     * 获取留言图片
     *
     * @return message_image - 留言图片
     */
    public String getMessageImage() {
        return messageImage;
    }

    /**
     * 设置留言图片
     *
     * @param messageImage 留言图片
     */
    public void setMessageImage(String messageImage) {
        this.messageImage = messageImage;
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