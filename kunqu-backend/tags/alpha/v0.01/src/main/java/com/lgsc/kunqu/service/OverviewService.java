package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.OverviewMapper;
import com.lgsc.kunqu.model.Overview;

/**
 * 概述
 */
@Service
public class OverviewService {
	
	@Autowired
	private OverviewMapper overviewMapper;

	public int insert(Overview record) {
		return overviewMapper.insert(record);
	}

	public int insertSelective(Overview record) {
		return overviewMapper.insertSelective(record);
	}

	public Overview selectByPrimaryKey(long overviewId) {
		return overviewMapper.selectByPrimaryKey(overviewId);
	}
	
	public PageInfo<Overview> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Overview> list = overviewMapper.selectAll();
		PageInfo<Overview> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	public int updateByPrimaryKey(Overview record) {
		return overviewMapper.updateByPrimaryKey(record);
	}

	public int updateByPrimaryKeySelective(Overview record) {
		return overviewMapper.updateByPrimaryKeySelective(record);
	}

	public int deleteByPrimaryKey(long overviewId) {
		return overviewMapper.deleteByPrimaryKey(overviewId);
	}

}
