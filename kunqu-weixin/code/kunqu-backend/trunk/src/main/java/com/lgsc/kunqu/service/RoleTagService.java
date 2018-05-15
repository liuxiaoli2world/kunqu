package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.RoleTagArticleMapper;
import com.lgsc.kunqu.mapper.RoleTagDramaMapper;
import com.lgsc.kunqu.mapper.RoleTagMapper;
import com.lgsc.kunqu.mapper.RoleTagSpecialMapper;
import com.lgsc.kunqu.model.RoleTag;
import com.lgsc.kunqu.model.RoleTagArticle;
import com.lgsc.kunqu.model.RoleTagDrama;
import com.lgsc.kunqu.model.RoleTagSpecial;

/**
 * 角色
 */
@Service
public class RoleTagService {
	
	@Autowired
	private RoleTagMapper roleTagMapper;
	
	@Autowired
	private RoleTagDramaMapper roleTagDramaMapper;
	
	@Autowired
	private RoleTagSpecialMapper roleTagSpecialMapper;
	
	@Autowired
	private RoleTagArticleMapper roleTagArticleMapper;

	public int insert(RoleTag record) {
		return roleTagMapper.insert(record);
	}

	public int insertSelective(RoleTag record) {
		return roleTagMapper.insertSelective(record);
	}

	public RoleTag selectByPrimaryKey(long roleTagId) {
		return roleTagMapper.selectByPrimaryKey(roleTagId);
	}
	
	/**
	 * 查询所有角色标签
	 */
	public List<RoleTag> selectAll() {
		return roleTagMapper.selectAll();
	}
	
	/**
	 * 查询所有角色标签
	 */
	public PageInfo<RoleTag> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RoleTag> list = roleTagMapper.selectAll();
		PageInfo<RoleTag> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	public int updateByPrimaryKey(RoleTag record) {
		return roleTagMapper.updateByPrimaryKey(record);
	}

	public int updateByPrimaryKeySelective(RoleTag record) {
		return roleTagMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 删除角色标签
	 */
	@Transactional
	public int deleteByPrimaryKey(long roleTagId) {
		// 删除角色剧典关系
		RoleTagDrama roleTagDrama = new RoleTagDrama();
		roleTagDrama.setRoleTagId(roleTagId);
		roleTagDramaMapper.delete(roleTagDrama);
		
		// 删除角色曲典专辑关系
		RoleTagSpecial roleTagSpecial = new RoleTagSpecial();
		roleTagSpecial.setRoleTagId(roleTagId);
		roleTagSpecialMapper.delete(roleTagSpecial);
		
		// 删除角色文章关系
		RoleTagArticle roleTagArticle = new RoleTagArticle();
		roleTagArticle.setRoleTagId(roleTagId);
		roleTagArticleMapper.delete(roleTagArticle);
		
		// 删除角色标签
		return roleTagMapper.deleteByPrimaryKey(roleTagId);
	}

}
