package com.lgsc.kunqu.model;

import java.util.Date;
import javax.persistence.*;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt", "handler"})
@ApiModel(value="曲典")
public class Opera {
    /**
     * 曲典编号
     */
	@ApiModelProperty("曲典编号")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opera_id")
    private Long operaId;

    /**
     * 曲典名称
     */
	@ApiModelProperty("曲典名称")
	@Length(min = 0, max = 50)
    @Column(name = "opera_name")
    private String operaName;

    /**
     * 演唱者
     */
    @ApiModelProperty("演唱者")
    @Length(min = 0, max = 20)
    private String singer;

    /**
     * 歌词链接
     */
    @ApiModelProperty("歌词链接")
    @Length(min = 0, max = 500)
    @Column(name = "lyric_url")
    private String lyricUrl;

    /**
     * 歌曲链接
     */
    @ApiModelProperty("歌曲链接，最大长度：500")
    @NotBlank(message = "歌曲链接不能为空！")
    @Length(min = 0, max = 500)
    @Column(name = "song_url")
    private String songUrl;
    
    /**
     * 图片链接
     */
    @ApiModelProperty("图片链接")
    @Length(min = 0, max = 300)
    @Column(name = "image_url")
    private String imageUrl;
   
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
     * 获取曲典名称
     *
     * @return opera_name - 曲典名称
     */
    public String getOperaName() {
        return operaName;
    }

    /**
     * 设置曲典名称
     *
     * @param operaName 曲典名称
     */
    public void setOperaName(String operaName) {
        this.operaName = operaName;
    }

    /**
     * 获取演唱者
     *
     * @return singer - 演唱者
     */
    public String getSinger() {
        return singer;
    }

    /**
     * 设置演唱者
     *
     * @param singer 演唱者
     */
    public void setSinger(String singer) {
        this.singer = singer;
    }

    /**
     * 获取歌词链接
     *
     * @return lyric_url - 歌词链接
     */
    public String getLyricUrl() {
        return lyricUrl;
    }

    /**
     * 设置歌词链接
     *
     * @param lyricUrl 歌词链接
     */
    public void setLyricUrl(String lyricUrl) {
        this.lyricUrl = lyricUrl;
    }

    /**
     * 获取歌曲链接
     *
     * @return song_url - 歌曲链接
     */
    public String getSongUrl() {
        return songUrl;
    }

    /**
     * 设置歌曲链接
     *
     * @param songUrl 歌曲链接
     */
    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }
    
    public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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