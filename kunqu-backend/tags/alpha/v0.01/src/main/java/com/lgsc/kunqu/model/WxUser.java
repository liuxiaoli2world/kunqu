package com.lgsc.kunqu.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "wx_user")
public class WxUser {
    /**
     * 微信用户id
     */
    @Id
    @Column(name = "wx_user_id")
    private Long wxUserId;

    /**
     * 普通用户的标识，对当前开发者帐号唯一
     */
    private String openid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 普通用户性别，1为男性，2为女性
     */
    private Integer sex;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    /**
     * 用户头像
     */
    private String headimgurl;

    /**
     * 用户特权
     */
    private String privilege;

    /**
     * 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
     */
    private String unionid;

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
     * 获取微信用户id
     *
     * @return wx_user_id - 微信用户id
     */
    public Long getWxUserId() {
        return wxUserId;
    }

    /**
     * 设置微信用户id
     *
     * @param wxUserId 微信用户id
     */
    public void setWxUserId(Long wxUserId) {
        this.wxUserId = wxUserId;
    }

    /**
     * 获取普通用户的标识，对当前开发者帐号唯一
     *
     * @return openid - 普通用户的标识，对当前开发者帐号唯一
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置普通用户的标识，对当前开发者帐号唯一
     *
     * @param openid 普通用户的标识，对当前开发者帐号唯一
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取普通用户性别，1为男性，2为女性
     *
     * @return sex - 普通用户性别，1为男性，2为女性
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置普通用户性别，1为男性，2为女性
     *
     * @param sex 普通用户性别，1为男性，2为女性
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取省份
     *
     * @return province - 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取用户头像
     *
     * @return headimgurl - 用户头像
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * 设置用户头像
     *
     * @param headimgurl 用户头像
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    /**
     * 获取用户特权
     *
     * @return privilege - 用户特权
     */
    public String getPrivilege() {
        return privilege;
    }

    /**
     * 设置用户特权
     *
     * @param privilege 用户特权
     */
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    /**
     * 获取用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
     *
     * @return unionid - 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * 设置用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
     *
     * @param unionid 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid;
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