package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.SpecialOperaMapper;
import com.lgsc.kunqu.model.SpecialOpera;

/**
 * 
 */
@Service
public class SpecialOperaService {
	
	@Autowired
	private SpecialOperaMapper specialOperaMapper;

	public int insert(SpecialOpera record) {
		return specialOperaMapper.insert(record);
	}

	public int insertSelective(SpecialOpera record) {
		return specialOperaMapper.insertSelective(record);
	}

	public SpecialOpera selectByPrimaryKey(Long id) {
		return specialOperaMapper.selectByPrimaryKey(id);
	}
	
	public PageInfo<SpecialOpera> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SpecialOpera> list = specialOperaMapper.selectAll();
		PageInfo<SpecialOpera> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	public int updateByPrimaryKey(SpecialOpera record) {
		return specialOperaMapper.updateByPrimaryKey(record);
	}

	public int updateByPrimaryKeySelective(SpecialOpera record) {
		return specialOperaMapper.updateByPrimaryKeySelective(record);
	}

	public int deleteByPrimaryKey(Long id) {
		return specialOperaMapper.deleteByPrimaryKey(id);
	}

}
