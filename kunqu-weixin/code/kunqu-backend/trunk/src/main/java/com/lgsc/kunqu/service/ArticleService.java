package com.lgsc.kunqu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.ArticleMapper;
import com.lgsc.kunqu.model.Article;

/**
 * 文章
 * 
 * @author pomay
 *
 */
@Service
public class ArticleService {

	@Autowired
	private ArticleMapper articleMapper;

	public int insert(Article record) {
		return articleMapper.insert(record);
	}

	public int insertSelective(Article record) {
		return articleMapper.insertSelective(record);
	}

	/**
	 * 根据id查询文章，返回结果附加文章图片
	 * 
	 * @param id
	 * @return
	 */
	public List<Article> selectById(Long articleId) {
		return articleMapper.selectById(articleId);
	}

	/**
	 * 根据年代标签分页查找文章，返回结果附加文章图片
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param ageTagId
	 * @return
	 */
	public PageInfo<Article> selectArticleByAgeTagId(int pageNum, int pageSize, long ageTagId) {
		PageHelper.startPage(pageNum, pageSize);
		List<Article> list = articleMapper.selectArticleByAgeTagId(ageTagId);
		PageInfo<Article> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 分页查询相应类型模块 null 查询所有文章 1首页推荐阅读 2 精品阅读 3阅读排行4 精品推荐
	 * 
	 * @param type
	 * @return
	 */
	public PageInfo<Article> selectByStyle(int pageNum, int pageSize, Integer type) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<Article> pageInfo = null;
		// 查找类型为空，查询所有文章
		if (type == null) {
			pageInfo = new PageInfo<>(articleMapper.selectAllArticle());
		} else {
			switch (type) {
			case 1: // 首页推荐阅读 0 否 1 是
				pageInfo = new PageInfo<>(articleMapper.selectByStyle("is_index_recommend", 1));
				break;
			case 2: // 精品阅读 0 否 1 是
				pageInfo = new PageInfo<>(articleMapper.selectByStyle("is_boutique", 1));
				break;
			case 3: // 阅读排行 0 否 1 是
				pageInfo = new PageInfo<>(articleMapper.selectByStyle("is_rank_list", 1));
				break;
			case 4: // 列表精品推荐 0 否 1 是
				pageInfo = new PageInfo<>(articleMapper.selectByStyle("is_list_recommend", 1));
				break;
			default:
				break;
			}
		}
		return pageInfo;
	}

	/**
	 * 分页查询阅读排行之周/月排行
	 * 
	 * @param type
	 *            根据排行方式查询 查询类型 null阅读排行 1周排行 2 月排行
	 * @return
	 */
	public PageInfo<Article> selectByDesc(int pageNum, int pageSize, Integer type) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<Article> pageInfo = null;
		if (type == null) {
			pageInfo = new PageInfo<>(articleMapper.selectByStyle("is_rank_list", 1));
		} else {
			switch (type) {
			case 1: // 周排行 0 否 1 是
				pageInfo = new PageInfo<>(articleMapper.selectArticleByDesc("week_count"));
				break;
			case 2: // 月排行 0 否 1 是
				pageInfo = new PageInfo<>(articleMapper.selectArticleByDesc("month_count"));
				break;
			default:
				break;
			}
		}
		return pageInfo;
	}

	public int updateByPrimaryKey(Article record) {
		return articleMapper.updateByPrimaryKey(record);
	}

	public int updateByPrimaryKeySelective(Article record) {
		return articleMapper.updateByPrimaryKeySelective(record);
	}

	public int deleteByPrimaryKey(Long id) {
		return articleMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据剧目标签分页查找文章，返回结果附加文章图片
	 */
	public PageInfo<Article> selectArticleByRepertoireTagId(int pageNum, int pageSize, long repertoireTagId) {
		PageHelper.startPage(pageNum, pageSize);
		List<Article> list = articleMapper.selectArticleByRepertoireTagId(repertoireTagId);
		PageInfo<Article> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 根据id查找前一篇和后一篇文章
	 * 
	 * @param articleId
	 * @return
	 */
	public List<Article> selectPreAfterArticle(long articleId) {
		List<Article> result = new ArrayList<Article>();
		List<Article> articles = articleMapper.selectPreAfterArticle(articleId);
		// 假如只获取到一条
		if (articles.size() == 1) {
			for (Article article : articles) {
				// 如果获取到的是后一条
				if (article.getArticleId() > articleId) {
					result.add(null);
					result.add(article);
				}
				// 如果获取到是前一条
				if (article.getArticleId() < articleId) {
					result.add(article);
					result.add(null);
				}
			}
			return result;
		} else
			return articles;
	}

	/**
	 * 根据作者标签id分页查找文章，返回结果附加文章图片
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param ageTagId
	 * @return
	 */
	public PageInfo<Article> selectArticleByAuthorTagId(int pageNum, int pageSize, long authorTagId) {
		PageHelper.startPage(pageNum, pageSize);
		List<Article> list = articleMapper.selectArticleByAuthorTagId(authorTagId);
		PageInfo<Article> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

}
