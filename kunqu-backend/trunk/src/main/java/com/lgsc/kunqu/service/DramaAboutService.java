package com.lgsc.kunqu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.DramaAboutMapper;
import com.lgsc.kunqu.mapper.DramaMapper;
import com.lgsc.kunqu.model.Drama;
import com.lgsc.kunqu.model.DramaAbout;

/**
 * 相关剧典service
 */
@Service
public class DramaAboutService {

	@Autowired
	private DramaAboutMapper dramaAboutMapper;

	@Autowired
	private DramaMapper dramaMapper;

	/**
	 * 根据剧典id查询相关剧典
	 */
	public PageInfo<Drama> selectAboutById(Integer pageNum, Integer pageSize, Long dramaId) {
		PageHelper.startPage(pageNum, pageSize);
		List<Long> list = dramaAboutMapper.selectAboutById(dramaId);
		List<Drama> dramalist = new ArrayList<>();
		for (Long aboutId : list) {
			dramalist.add(dramaMapper.selectById(aboutId));
		}
		return new PageInfo<>(dramalist);
	}

	/**
	 * 添加相关剧典
	 */
	public int insertAbout(DramaAbout dramaAbout) {
		int num = 0;
		if (dramaMapper.selectById(dramaAbout.getDramaId()) != null
				&& dramaMapper.selectById(dramaAbout.getAboutId()) != null) {
			num = dramaAboutMapper.insertSelective(dramaAbout);
		}
		return num;
	}
	
	/**
	 * 批量添加相关剧典
	 */
	@Transactional
	public Boolean batchInsertAbout(long dramaId, long[] aboutIds) {
		boolean result = false;
		dramaAboutMapper.deleteByDramaId(dramaId);
		Date now = new Date();
		for (Long aboutId : aboutIds) {
			DramaAbout record = new DramaAbout();
			record.setDramaId(dramaId);
			record.setAboutId(aboutId);
			record.setCreatedAt(now);
			dramaAboutMapper.insertSelective(record);
		}
		result = true;
		return result;
	}

	/**
	 * 删除相关剧典
	 */
	public int deleteByPrimaryKey(Long dramaAboutId) {
		return dramaAboutMapper.deleteByPrimaryKey(dramaAboutId);
	}

	/**
	 * 根据dramaId删除相关剧典
	 */
	public int deleteByDramaId(Long dramaId) {
		return dramaAboutMapper.deleteByDramaId(dramaId);
	}

	/**
	 * 根据dramaId,aboutId删除相关剧典
	 */
	public int deleteByDramaIdAndAboutId(Long dramaId, Long aboutId) {
		return dramaAboutMapper.deleteByDramaIdAndAboutId(dramaId, aboutId);
	}

	/**
	 * 修改相关剧典
	 */
	public int updateAbout(DramaAbout dramaAbout) {
		int num = 0;
		if (dramaMapper.selectById(dramaAbout.getDramaId()) != null
				&& dramaMapper.selectById(dramaAbout.getAboutId()) != null) {
			num = dramaAboutMapper.updateByPrimaryKeySelective(dramaAbout);
		}
		return num;
	}
	
	/**
	 * 根据dramaId查询剧典和是否为相关剧典
	 */
	public PageInfo<Map<String, Object>> selectDramaAndAbout(Integer pageNum, Integer pageSize, Long dramaId) {
		return selectDramaAndAbout(pageNum, pageSize, dramaId, null);
	}
	
	/**
	 * 根据dramaId查询剧典和是否为相关剧典
	 */
	public PageInfo<Map<String, Object>> selectDramaAndAbout(Integer pageNum, Integer pageSize, Long dramaId, String dramaName) {
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> list = dramaAboutMapper.selectDramaAndAbout(dramaId, dramaName);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

}
