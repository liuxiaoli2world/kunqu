package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.RoleTagSpecialMapper;
import com.lgsc.kunqu.mapper.SpecialMapper;
import com.lgsc.kunqu.model.RoleTagSpecial;
import com.lgsc.kunqu.model.Special;

/**
 * 角色专辑关系
 */
@Service
public class RoleTagSpecialService {
	
	@Autowired
	private RoleTagSpecialMapper roleTagSpecialMapper;
	
	@Autowired
	private SpecialMapper specialMapper;

	public int insert(RoleTagSpecial record) {
		return roleTagSpecialMapper.insert(record);
	}

	public int insertSelective(RoleTagSpecial record) {
		return roleTagSpecialMapper.insertSelective(record);
	}

	public RoleTagSpecial selectByPrimaryKey(long roleTagSpecialId) {
		return roleTagSpecialMapper.selectByPrimaryKey(roleTagSpecialId);
	}
	
	/**
	 * 根据角色标签id查询曲典专辑
	 */
	public PageInfo<Special> selectSpecialByRoleTagId(long roleTagId, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
        List<Special> list = specialMapper.selectSpecialByRoleTagId(roleTagId);
		PageInfo<Special> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	public PageInfo<RoleTagSpecial> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RoleTagSpecial> list = roleTagSpecialMapper.selectAll();
		PageInfo<RoleTagSpecial> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	public int updateByPrimaryKey(RoleTagSpecial record) {
		return roleTagSpecialMapper.updateByPrimaryKey(record);
	}

	public int updateByPrimaryKeySelective(RoleTagSpecial record) {
		return roleTagSpecialMapper.updateByPrimaryKeySelective(record);
	}

	public int deleteByPrimaryKey(long roleTagSpecialId) {
		return roleTagSpecialMapper.deleteByPrimaryKey(roleTagSpecialId);
	}

}
