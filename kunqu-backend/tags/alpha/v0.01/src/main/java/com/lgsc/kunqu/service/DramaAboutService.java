package com.lgsc.kunqu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
