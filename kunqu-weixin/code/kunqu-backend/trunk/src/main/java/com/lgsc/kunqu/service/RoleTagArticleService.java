package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.ArticleMapper;
import com.lgsc.kunqu.mapper.RoleTagArticleMapper;
import com.lgsc.kunqu.model.Article;
import com.lgsc.kunqu.model.RoleTagArticle;

/**
 * 角色文章关系
 */
@Service
public class RoleTagArticleService {
	
	@Autowired
	private RoleTagArticleMapper roleTagArticleMapper;
	
	@Autowired
	private ArticleMapper articleMapper;

	public int insert(RoleTagArticle record) {
		return roleTagArticleMapper.insert(record);
	}

	public int insertSelective(RoleTagArticle record) {
		return roleTagArticleMapper.insertSelective(record);
	}

	public RoleTagArticle selectByPrimaryKey(long roleTagArticleId) {
		return roleTagArticleMapper.selectByPrimaryKey(roleTagArticleId);
	}
	
	/**
	 * 根据角色标签id查询文章
	 */
	public PageInfo<Article> selectArticleByRoleTagId(long roleTagId, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleMapper.selectArticleByRoleTagId(roleTagId);
		PageInfo<Article> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	public PageInfo<RoleTagArticle> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RoleTagArticle> list = roleTagArticleMapper.selectAll();
		PageInfo<RoleTagArticle> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	public int updateByPrimaryKey(RoleTagArticle record) {
		return roleTagArticleMapper.updateByPrimaryKey(record);
	}

	public int updateByPrimaryKeySelective(RoleTagArticle record) {
		return roleTagArticleMapper.updateByPrimaryKeySelective(record);
	}

	public int deleteByPrimaryKey(long roleTagArticleId) {
		return roleTagArticleMapper.deleteByPrimaryKey(roleTagArticleId);
	}

}
