package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.OperaMapper;
import com.lgsc.kunqu.model.Opera;

/**
 * 
 */
@Service
public class OperaService {
	
	@Autowired
	private OperaMapper operaMapper;

	public int insert(Opera record) {
		return operaMapper.insert(record);
	}

	public int insertSelective(Opera record) {
		return operaMapper.insertSelective(record);
	}

	public Opera selectByPrimaryKey(Long id) {
		return operaMapper.selectByPrimaryKey(id);
	}
	
	public PageInfo<Opera> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Opera> list = operaMapper.selectAll();
		PageInfo<Opera> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	public int updateByPrimaryKey(Opera record) {
		return operaMapper.updateByPrimaryKey(record);
	}

	public int updateByPrimaryKeySelective(Opera record) {
		return operaMapper.updateByPrimaryKeySelective(record);
	}

	public int deleteByPrimaryKey(Long id) {
		return operaMapper.deleteByPrimaryKey(id);
	}

}
