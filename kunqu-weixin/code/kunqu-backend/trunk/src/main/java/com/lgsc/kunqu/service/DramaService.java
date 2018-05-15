package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.DramaAboutMapper;
import com.lgsc.kunqu.mapper.DramaImageMapper;
import com.lgsc.kunqu.mapper.DramaMapper;
import com.lgsc.kunqu.mapper.DramaPartMapper;
import com.lgsc.kunqu.model.Drama;

/**
 * 
 */
@Service
public class DramaService {

	@Autowired
	private DramaMapper dramaMapper;

	@Autowired
	private DramaAboutMapper dramaAboutMapper;

	@Autowired
	private DramaPartMapper dramaPartMapper;

	@Autowired
	private DramaImageMapper dramaImageMapper;

	public int insert(Drama record) {
		return dramaMapper.insert(record);
	}

	public int insertSelective(Drama record) {
		return dramaMapper.insertSelective(record);
	}

	/**
	 * 根据dramaId查询剧典
	 */
	public Drama selectByPrimaryKey(Long dramaId) {
		return dramaMapper.selectById(dramaId);
	}

	/**
	 * 分页查询所有剧典
	 */
	public PageInfo<Drama> selectAll(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Drama> list = dramaMapper.selectAll();
		PageInfo<Drama> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	public int updateByPrimaryKey(Drama record) {
		return dramaMapper.updateByPrimaryKey(record);
	}

	public int updateByPrimaryKeySelective(Drama record) {
		return dramaMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 删除剧典
	 */
	@Transactional
	public int deleteByPrimaryKey(Long dramaId) {
		dramaImageMapper.deleteByPrimaryKey(dramaId); // 删除剧典封面
		dramaPartMapper.deleteByPrimaryKey(dramaId); // 删除剧典片段
		dramaAboutMapper.deleteByAboutId(dramaId); // 删除作为相关剧典的数据
		return dramaMapper.deleteByPrimaryKey(dramaId); // 删除剧典
	}

	/**
	 * 查询 查询类型 0 全部 1 最热剧典 2 列表页剧典推荐 3 首页剧典推荐 4 折子戏
	 */
	public PageInfo<Drama> selects(Integer pageNum, Integer pageSize, Integer type) {
		PageInfo<Drama> pageInfo = null;
		if (type >= 0 && type <= 5) {
			PageHelper.startPage(pageNum, pageSize);
			pageInfo = new PageInfo<>(dramaMapper.selects(type));
		}
		return pageInfo;
	}

	
	/**
	 * 根据年代标签id分页查找剧典
	 * @param pageNum
	 * @param pageSize
	 * @param ageTagId
	 * @return
	 */
	public PageInfo<Drama> selectDramaByAgeTagId(Integer pageNum, Integer pageSize,long ageTagId) {
		PageHelper.startPage(pageNum, pageSize);
		List<Drama> list = dramaMapper.selectDramaByAgeTagId(ageTagId);
		PageInfo<Drama> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 根据剧目标签id分页查找剧典
	 */
	public PageInfo<Drama> selectDramaByRepertoireTagId(Integer pageNum, Integer pageSize,long repertoireTagId) {
		PageHelper.startPage(pageNum, pageSize);
		List<Drama> list = dramaMapper.selectDramaByRepertoireTagId(repertoireTagId);
		PageInfo<Drama> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	/**
	 * 根据作者id查询剧典
	 * @param pageNum
	 * @param pageSize
	 * @param authorTagId
	 * @return
	 */
	public PageInfo<Drama> selectDramaByAuthorTagId(int pageNum, int pageSize, long authorTagId) {
		PageHelper.startPage(pageNum, pageSize);
		List<Drama> list = dramaMapper.selectDramaByAuthorTagId(authorTagId);
		PageInfo<Drama> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	/**
	 * 添加剧典
	 */
	public int insertDrama(Drama drama) {
		return dramaMapper.insertSelective(drama);
	}

	/**
	 * 修改剧典
	 */
	public int updateDrama(Drama drama) {
		return dramaMapper.updateByPrimaryKeySelective(drama);
	}

}
