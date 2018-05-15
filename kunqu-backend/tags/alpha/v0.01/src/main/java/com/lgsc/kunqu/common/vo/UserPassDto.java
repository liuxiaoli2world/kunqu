package com.lgsc.kunqu.common.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class UserPassDto {
    /**
     * 用户编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名，最大长度50")
    @NotBlank(message = "用户名不能为空！")
    @Length(min = 0, max = 50)
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空！")
    @Length(min = 0, max = 100)
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱，最大长度100")
    @Length(min = 0, max = 100)
    @Email
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @Digits(integer = 11, fraction = 0)
    private Long phone;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片地址，最大长度300")
    @Length(min = 0, max = 300)
    private String image;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址，最大长度50")
    @Length(min = 0, max = 50)
    private String address;

    /**
     * 剧典浏览量
     */
    @ApiModelProperty(value = "剧典浏览量", hidden = true)
    @Digits(integer = 11, fraction = 0)
    @Column(name = "drama_num")
    private Integer dramaNum;

    /**
     * 曲典浏览量
     */
    @ApiModelProperty(value = "曲典浏览量", hidden = true)
    @Digits(integer = 11, fraction = 0)
    @Column(name = "special_num")
    private Integer specialNum;

    /**
     * 文章浏览量
     */
    @ApiModelProperty(value = "文章浏览量", hidden = true)
    @Digits(integer = 11, fraction = 0)
    @Column(name = "article_num")
    private Integer articleNum;

    /**
     * 是否可用 0=不可用 1=可用
     */
    @ApiModelProperty(value = "是否可用 0=不可用 1=可用", hidden = true)
    @Digits(integer = 1, fraction = 0)
    private Short enable;

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
     * 微信用户id
     */
    @ApiModelProperty(value = "微信用户id", hidden = true)
    @Digits(integer = 11, fraction = 0)
    @Column(name = "wx_user_id")
    private Long wxUserId;
    
    /**
     * 获取用户编号
     *
     * @return user_id - 用户编号
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户编号
     *
     * @param userId 用户编号
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public Long getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(Long phone) {
        this.phone = phone;
    }

    /**
     * 获取图片
     *
     * @return image - 图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置图片
     *
     * @param image 图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取剧典浏览量
     *
     * @return drama_num - 剧典浏览量
     */
    public Integer getDramaNum() {
        return dramaNum;
    }

    /**
     * 设置剧典浏览量
     *
     * @param dramaNum 剧典浏览量
     */
    public void setDramaNum(Integer dramaNum) {
        this.dramaNum = dramaNum;
    }

    /**
     * 获取曲典浏览量
     *
     * @return special_num - 曲典浏览量
     */
    public Integer getSpecialNum() {
        return specialNum;
    }

    /**
     * 设置曲典浏览量
     *
     * @param specialNum 曲典浏览量
     */
    public void setSpecialNum(Integer specialNum) {
        this.specialNum = specialNum;
    }

    /**
     * 获取文章浏览量
     *
     * @return article_num - 文章浏览量
     */
    public Integer getArticleNum() {
        return articleNum;
    }

    /**
     * 设置文章浏览量
     *
     * @param articleNum 文章浏览量
     */
    public void setArticleNum(Integer articleNum) {
        this.articleNum = articleNum;
    }

    /**
     * 获取是否可用 0=不可用 1=可用
     *
     * @return enable - 是否可用 0=不可用 1=可用
     */
    public Short getEnable() {
        return enable;
    }

    /**
     * 设置是否可用 0=不可用 1=可用
     *
     * @param enable 是否可用 0=不可用 1=可用
     */
    public void setEnable(Short enable) {
        this.enable = enable;
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

	public Long getWxUserId() {
		return wxUserId;
	}

	public void setWxUserId(Long wxUserId) {
		this.wxUserId = wxUserId;
	}
    
}