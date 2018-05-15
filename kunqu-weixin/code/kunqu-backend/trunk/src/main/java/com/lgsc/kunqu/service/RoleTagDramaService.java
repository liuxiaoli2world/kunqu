package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.DramaMapper;
import com.lgsc.kunqu.mapper.RoleTagDramaMapper;
import com.lgsc.kunqu.model.Drama;
import com.lgsc.kunqu.model.RoleTagDrama;

/**
 * 角色剧典关系
 */
@Service
public class RoleTagDramaService {
	
	@Autowired
	private RoleTagDramaMapper roleTagDramaMapper;
	
	@Autowired
	private DramaMapper dramaMapper;

	public int insert(RoleTagDrama record) {
		return roleTagDramaMapper.insert(record);
	}

	public int insertSelective(RoleTagDrama record) {
		return roleTagDramaMapper.insertSelective(record);
	}

	public RoleTagDrama selectByPrimaryKey(long roleTagDramaId) {
		return roleTagDramaMapper.selectByPrimaryKey(roleTagDramaId);
	}
	
	/**
	 * 根据角色标签id查询剧典
	 */
	public PageInfo<Drama> selectDramaByRoleTagId(long roleTagId, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Drama> list = dramaMapper.selectDramaByRoleTagId(roleTagId);
		PageInfo<Drama> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	public PageInfo<RoleTagDrama> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RoleTagDrama> list = roleTagDramaMapper.selectAll();
		PageInfo<RoleTagDrama> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	public int updateByPrimaryKey(RoleTagDrama record) {
		return roleTagDramaMapper.updateByPrimaryKey(record);
	}

	public int updateByPrimaryKeySelective(RoleTagDrama record) {
		return roleTagDramaMapper.updateByPrimaryKeySelective(record);
	}

	public int deleteByPrimaryKey(long roleTagDramaId) {
		return roleTagDramaMapper.deleteByPrimaryKey(roleTagDramaId);
	}

}
