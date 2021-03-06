package com.lgsc.kunqu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "article_image")
@JsonIgnoreProperties(value = { "createdBy", "createdAt", "updatedBy", "updatedAt", "articleImageId", "articleId" })
public class ArticleImage {
	/**
	 * 文章图片
	 */
	@Id
	@Column(name = "article_image_id")
	private Long articleImageId;

	/**
	 * 文章编号
	 */
	@Column(name = "article_id")
	private Long articleId;

	/**
	 * 图片地址
	 */
	@Column(name = "image_url")
	private String imageUrl;
	/**
	 * 图片使用场景 01 分类 02 列表 03 文章内图片
	 */
	@Column(name = "image_scene")
	private String imageScene;
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
	 * 获取文章图片
	 *
	 * @return article_image_id - 文章图片
	 */
	public Long getArticleImageId() {
		return articleImageId;
	}

	/**
	 * 设置文章图片
	 *
	 * @param articleImageId
	 *            文章图片
	 */
	public void setArticleImageId(Long articleImageId) {
		this.articleImageId = articleImageId;
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
	 * @param articleId
	 *            文章编号
	 */
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
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
	 * @param imageUrl
	 *            图片地址
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * 获取图片使用场景 01 分类 02 列表 03 文章内图片
	 *
	 * @return image_scene - 图片使用场景 01 分类 02 列表 03 文章内图片
	 */
	public String getImageScene() {
		return imageScene;
	}

	/**
	 * 设置图片使用场景01 分类 02 列表 03 文章内图片
	 *
	 * @param imageScene
	 *            图片使用场景 01 分类 02 列表 03 文章内图片
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
	 * @param createdBy
	 *            创建人
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
	 * @param createdAt
	 *            创建时间
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
	 * @param updatedBy
	 *            最后修改人
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
	 * @param updatedAt
	 *            最后修改时间
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}