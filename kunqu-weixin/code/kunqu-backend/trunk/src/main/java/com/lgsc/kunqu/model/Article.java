package com.lgsc.kunqu.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "文章")
@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt", "ageTagId", "authorTagId", "repertoireTagId", "dirId", "handler"})
public class Article {
    /**
     * 文章编号
     */
    @Id
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 来源
     */
    private String source;

    /**
     * 发布时间
     */
    @Column(name = "release_date")
    private Date releaseDate;

    /**
     * 总阅读
     */
    @Column(name = "sum_read_count")
    private Integer sumReadCount;

    /**
     * 天阅读
     */
    @Column(name = "day_read_count")
    private Integer dayReadCount;

    /**
     * 周排行
     */
    @Column(name = "week_count")
    private Integer weekCount;

    /**
     * 月排行
     */
    @Column(name = "month_count")
    private Integer monthCount;

    /**
     * 阅读排行 0 否 1 是
     */
    @Column(name = "is_rank_list")
    private Integer isRankList;

    /**
     * 精品阅读 0 否 1 是
     */
    @Column(name = "is_boutique")
    private Integer isBoutique;

    /**
     * 首页推荐阅读 0 否 1 是
     */
    @Column(name = "is_index_recommend")
    private Integer isIndexRecommend;
       
    /**
     * 年代标签编号
     */
    @Column(name = "age_tag_id")
    private Long ageTagId;

    /**
     * 作者标签编号
     */
    @Column(name = "author_tag_id")
    private Long authorTagId;

    /**
     * 剧目标签编号
     */
    @Column(name = "repertoire_tag_id")
    private Long repertoireTagId;

    /**
     * 目录编号
     */
    @Column(name = "dir_id")
    private Long dirId;

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
     * 内容
     */
    private String content;
    
    /**
     * 文章简介（虚拟列）
     */
    @Column(name = "article_desc")
    @ApiModelProperty(hidden = true)
    private String articleDesc;
    
    /**
     * 列表精品推荐 0 否 1 是
     */
    @Column(name = "is_list_recommend")
    private Integer isListRecommend;
    
    /**
     * 文章图片
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private List<ArticleImage> articleImages;

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
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取来源
     *
     * @return source - 来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置来源
     *
     * @param source 来源
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取发布时间
     *
     * @return release_date - 发布时间
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * 设置发布时间
     *
     * @param releaseDate 发布时间
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * 获取总阅读
     *
     * @return sum_read_count - 总阅读
     */
    public Integer getSumReadCount() {
        return sumReadCount;
    }

    /**
     * 设置总阅读
     *
     * @param sumReadCount 总阅读
     */
    public void setSumReadCount(Integer sumReadCount) {
        this.sumReadCount = sumReadCount;
    }

    /**
     * 获取天阅读
     *
     * @return day_read_count - 天阅读
     */
    public Integer getDayReadCount() {
        return dayReadCount;
    }

    /**
     * 设置天阅读
     *
     * @param dayReadCount 天阅读
     */
    public void setDayReadCount(Integer dayReadCount) {
        this.dayReadCount = dayReadCount;
    }

    /**
     * 获取周排行
     *
     * @return week_count - 周排行
     */
    public Integer getWeekCount() {
        return weekCount;
    }

    /**
     * 设置周排行
     *
     * @param weekCount 周排行
     */
    public void setWeekCount(Integer weekCount) {
        this.weekCount = weekCount;
    }

    /**
     * 获取月排行
     *
     * @return month_count - 月排行
     */
    public Integer getMonthCount() {
        return monthCount;
    }

    /**
     * 设置月排行
     *
     * @param monthCount 月排行
     */
    public void setMonthCount(Integer monthCount) {
        this.monthCount = monthCount;
    }

    /**
     * 获取阅读排行 0 否 1 是
     *
     * @return is_rank_list - 阅读排行 0 否 1 是
     */
    public Integer getIsRankList() {
        return isRankList;
    }

    /**
     * 设置阅读排行 0 否 1 是
     *
     * @param isRankList 阅读排行 0 否 1 是
     */
    public void setIsRankList(Integer isRankList) {
        this.isRankList = isRankList;
    }

    /**
     * 获取精品阅读 0 否 1 是
     *
     * @return is_boutique - 精品阅读 0 否 1 是
     */
    public Integer getIsBoutique() {
        return isBoutique;
    }

    /**
     * 设置精品阅读 0 否 1 是
     *
     * @param isBoutique 精品阅读 0 否 1 是
     */
    public void setIsBoutique(Integer isBoutique) {
        this.isBoutique = isBoutique;
    }

    /**
     * 获取首页推荐阅读 0 否 1 是
     *
     * @return is_index_recommend - 首页推荐阅读 0 否 1 是
     */
    public Integer getIsIndexRecommend() {
        return isIndexRecommend;
    }

    /**
     * 设置首页推荐阅读 0 否 1 是
     *
     * @param isIndexRecommend 首页推荐阅读 0 否 1 是
     */
    public void setIsIndexRecommend(Integer isIndexRecommend) {
        this.isIndexRecommend = isIndexRecommend;
    }

    /**
     * 获取年代标签编号
     *
     * @return age_tag_id - 年代标签编号
     */
    public Long getAgeTagId() {
        return ageTagId;
    }

    /**
     * 设置年代标签编号
     *
     * @param ageTagId 年代标签编号
     */
    public void setAgeTagId(Long ageTagId) {
        this.ageTagId = ageTagId;
    }

    /**
     * 获取作者标签编号
     *
     * @return author_tag_id - 作者标签编号
     */
    public Long getAuthorTagId() {
        return authorTagId;
    }

    /**
     * 设置作者标签编号
     *
     * @param authorTagId 作者标签编号
     */
    public void setAuthorTagId(Long authorTagId) {
        this.authorTagId = authorTagId;
    }

    /**
     * 获取剧目标签编号
     *
     * @return repertoire_tag_id - 剧目标签编号
     */
    public Long getRepertoireTagId() {
        return repertoireTagId;
    }

    /**
     * 设置剧目标签编号
     *
     * @param repertoireTagId 剧目标签编号
     */
    public void setRepertoireTagId(Long repertoireTagId) {
        this.repertoireTagId = repertoireTagId;
    }

    /**
     * 获取目录编号
     *
     * @return dir_id - 目录编号
     */
    public Long getDirId() {
        return dirId;
    }

    /**
     * 设置目录编号
     *
     * @param dirId 目录编号
     */
    public void setDirId(Long dirId) {
        this.dirId = dirId;
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

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
  /**
   * 返回精品推荐
   * @return
   */
	public Integer getIsListRecommend() {
		return isListRecommend;
	}
	/**
	 * 设置列表精品推荐 0否1是
	 * @param isListRecommend
	 */
	public void setIsListRecommend(Integer isListRecommend) {
		this.isListRecommend = isListRecommend;
	}

	public List<ArticleImage> getArticleImages() {
		return articleImages;
	}

	public void setArticleImages(List<ArticleImage> articleImages) {
		this.articleImages = articleImages;
	}

	public String getArticleDesc() {
		return articleDesc;
	}

	public void setArticleDesc(String articleDesc) {
		this.articleDesc = articleDesc;
	}
    
}