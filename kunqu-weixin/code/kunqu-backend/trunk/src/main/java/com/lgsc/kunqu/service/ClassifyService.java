package com.lgsc.kunqu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.ArticleMapper;
import com.lgsc.kunqu.mapper.DramaMapper;
import com.lgsc.kunqu.mapper.SpecialMapper;
import com.lgsc.kunqu.model.Article;
import com.lgsc.kunqu.model.Drama;
import com.lgsc.kunqu.model.Special;

/**
 * 分类
 */
@Service
public class ClassifyService {
	
	@Autowired
	private DramaMapper dramaMapper;
	
	@Autowired
	private SpecialMapper specialMapper;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	/**
	 * 根据标签查询
	 * @param Map queryType 查询类型，drama:剧典，special:曲典，article:文章
	 * @param Map ageTagId 年代标签编号
	 * @param Map authorTagId 作者标签编号
	 * @param Map repertoireTagId 剧目标签编号
	 * @param Map roleTagId 角色标签编号
	 * @param pageNum 当前页
	 * @param pageSize 每页数量
	 * @return 集合
	 */
	public Object selectByTag(Map<String, Object> param, int pageNum, int pageSize) {
		Object result = null;
		if ("drama".equals(param.get("queryType"))) {
			PageHelper.startPage(pageNum, pageSize);
			List<Drama> list = dramaMapper.selectByTag(param);
			PageInfo<Drama> pageInfo = new PageInfo<>(list);
			result = pageInfo;
		} else if ("special".equals(param.get("queryType"))) {
			PageHelper.startPage(pageNum, pageSize);
			List<Special> list = specialMapper.selectByTag(param);
			PageInfo<Special> pageInfo = new PageInfo<>(list);
			result = pageInfo;
		} else if ("article".equals(param.get("queryType"))) {
			PageHelper.startPage(pageNum, pageSize);
			List<Article> list = articleMapper.selectByTag(param);
			PageInfo<Article> pageInfo = new PageInfo<>(list);
			result = pageInfo;
		} else {
			return null;
		}
		return result;
	}
	
	/**
	 * 根据关键字查询
	 * @param queryType 查询类型，drama:剧典，special:曲典，article:文章
	 * @param keyword 关键字
	 * @param pageNum 当前页
	 * @param pageSize 每页数量
	 * @return 集合
	 */
	public Object selectByKeyword(String queryType, String keyword, int pageNum, int pageSize) {
		Object result = null;
		if ("drama".equals(queryType)) {
			PageHelper.startPage(pageNum, pageSize);
			List<Drama> list = dramaMapper.selectByKeyword(keyword);
			PageInfo<Drama> pageInfo = new PageInfo<>(list);
			result = pageInfo;
		} else if ("special".equals(queryType)) {
			PageHelper.startPage(pageNum, pageSize);
			List<Special> list = specialMapper.selectByKeyword(keyword);
			PageInfo<Special> pageInfo = new PageInfo<>(list);
			result = pageInfo;
		} else if ("article".equals(queryType)) {
			PageHelper.startPage(pageNum, pageSize);
			List<Article> list = articleMapper.selectByKeyword(keyword);
			PageInfo<Article> pageInfo = new PageInfo<>(list);
			result = pageInfo;
		} else {
			return null;
		}
		return result;
	}

}
