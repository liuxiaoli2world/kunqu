package com.lgsc.kunqu.model;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "留言")
@Table(name = "message_board")
@JsonIgnoreProperties(value = {"createdBy", "updatedBy", "updatedAt", "handler"})
public class MessageBoard {
    /**
     * 留言编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_board_id")
    private Long messageBoardId;

    /**
     * 留言主题
     */
    @ApiModelProperty(value = "留言主题，最大长度：50。")
    @NotBlank(message = "留言主题不能为空！")
    @Length(min = 0, max = 50)
    @Column(name = "message_theme")
    private String messageTheme;

    /**
     * 留言人
     */
    @ApiModelProperty(value = "留言人，最大长度：30。")
    @NotBlank(message = "留言人不能为空！")
    @Length(min = 0, max = 30)
    @Column(name = "message_name")
    private String messageName;

    /**
     * 留言内容
     */
    @ApiModelProperty(value = "留言内容，最大长度：500。")
    @NotBlank(message = "留言内容不能为空！")
    @Length(min = 0, max = 500)
    @Column(name = "message_content")
    private String messageContent;

    /**
     * 回复
     */
    @ApiModelProperty(value = "回复，最大长度：500。")
    @Length(min = 0, max = 500)
    private String reply;

    /**
     * 状态 0 待回复 1 已回复
     */
    @ApiModelProperty(value = "状态 0=待回复 1=已回复。")
    @Digits(integer = 1, fraction = 0)
    private Short status;
    
    /**
     * 主题id
     */
    @ApiModelProperty(value = "主题id")
    @NotNull(message = "主题id不能为空！")
    @Digits(integer = 20, fraction = 0)
    @Column(name = "theme_id")
    private Long themeId;
    
    /**
     * 主题类型，drama:剧典，special:曲典，article:文章
     */
    @ApiModelProperty(value = "主题类型，drama:剧典，special:曲典，article:文章")
    @NotBlank(message = "主题类型不能为空！")
    @Length(min = 0, max = 10)
    @Column(name = "theme_type")
    private String themeType;
    
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @NotNull(message = "用户id不能为空！")
    @Digits(integer = 20, fraction = 0)
    @Column(name = "user_id")
    private Long userId;

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
     * 获取留言主题
     *
     * @return message_theme - 留言主题
     */
    public String getMessageTheme() {
        return messageTheme;
    }

    /**
     * 设置留言主题
     *
     * @param messageTheme 留言主题
     */
    public void setMessageTheme(String messageTheme) {
        this.messageTheme = messageTheme;
    }

    /**
     * 获取留言人
     *
     * @return message_name - 留言人
     */
    public String getMessageName() {
        return messageName;
    }

    /**
     * 设置留言人
     *
     * @param messageName 留言人
     */
    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    /**
     * 获取留言内容
     *
     * @return message_content - 留言内容
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * 设置留言内容
     *
     * @param messageContent 留言内容
     */
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    /**
     * 获取回复
     *
     * @return reply - 回复
     */
    public String getReply() {
        return reply;
    }

    /**
     * 设置回复
     *
     * @param reply 回复
     */
    public void setReply(String reply) {
        this.reply = reply;
    }

    /**
     * 获取状态 0 待回复 1 已回复
     *
     * @return status - 状态 0 待回复 1 已回复
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置状态 0 待回复 1 已回复
     *
     * @param status 状态 0 待回复 1 已回复
     */
    public void setStatus(Short status) {
        this.status = status;
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

	public Long getThemeId() {
		return themeId;
	}

	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}

	public String getThemeType() {
		return themeType;
	}

	public void setThemeType(String themeType) {
		this.themeType = themeType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
    
}