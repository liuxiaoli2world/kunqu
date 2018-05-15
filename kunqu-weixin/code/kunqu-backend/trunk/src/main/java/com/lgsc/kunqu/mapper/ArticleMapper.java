package com.lgsc.kunqu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lgsc.kunqu.common.dao.BaseMapper;
import com.lgsc.kunqu.model.Article;

public interface ArticleMapper extends BaseMapper<Article> {
	/**
	 * 根据id查询文章，返回结果附加文章图片
	 * 
	 * @param articleId
	 * @return
	 */
	List<Article> selectById(Long articleId);

	/**
	 * 根据id查询前一条和后一条记录
	 * 
	 * @param articleId
	 * @return
	 */
	List<Article> selectPreAfterArticle(Long articleId);

	/**
	 * 查询所有文章，返回结果附加文章图片
	 * 
	 * @return
	 */
	List<Article> selectAllArticle();

	/**
	 * 查询相应类型模块
	 * 
	 * @param style
	 * @param value
	 * @return
	 */
	List<Article> selectByStyle(@Param("style") String style, @Param("value") Integer value);

	/**
	 * 根据相应内容倒序查询
	 * 
	 * @param _parameter
	 *            类型(如周排行、月排行)
	 * @return
	 */

	List<Article> selectArticleByDesc(@Param("kind") String _parameter);

	/**
	 * 根据角色标签id查询文章
	 */
	public List<Article> selectArticleByRoleTagId(long roleTagId);

	/**
	 * 根据作者标签查询文章
	 * 
	 * @param authorTagId
	 * @return
	 */
	public List<Article> selectArticleByAuthorTagId(long authorTagId);

	/**
	 * 根据年代标签查询文章
	 * 
	 * @param ageTagId
	 * @return
	 */
	public List<Article> selectArticleByAgeTagId(long ageTagId);

	/**
	 * 修改目录编号为空
	 * 
	 * @param dirId
	 * @return
	 */
	public int updateDirIdNull(long dirId);

	/**
	 * 根据标签查询
	 * 
	 * @param param
	 * @return
	 */
	public List<Article> selectByTag(Map<String, Object> param);

	/**
	 * 根据关键字查询
	 * 
	 * @param keyword
	 * @return
	 */
	public List<Article> selectByKeyword(@Param("keyword") String keyword);

	/**
	 * 根据剧目标签查询文章
	 */
	List<Article> selectArticleByRepertoireTagId(long repertoireTagId);

}