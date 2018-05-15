package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.dto.CeremonyDTO;
import com.lgsc.kunqu.mapper.CeremonyMapper;
import com.lgsc.kunqu.mapper.CeremonySourceMapper;
import com.lgsc.kunqu.model.Ceremony;
import com.lgsc.kunqu.model.CeremonySource;

/**
 * 
 */
@Service
public class CeremonyService {
	
	@Autowired
	private CeremonyMapper ceremonyMapper;
	
	@Autowired
	private CeremonySourceMapper ceremonySourceMapper;  
	
	public int insertSelective(Ceremony record) {
		return ceremonyMapper.insertSelective(record);
	}

	public Ceremony selectByPrimaryKey(long id) {
		return ceremonyMapper.selectByPrimaryKey(id);
	}
    
    public List<Ceremony> selectAll() {
		return ceremonyMapper.selectAll();
	}
	
	public PageInfo<Ceremony> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Ceremony> list = ceremonyMapper.selectAll();
		PageInfo<Ceremony> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	public int updateByPrimaryKeySelective(Ceremony record) {
		return ceremonyMapper.updateByPrimaryKeySelective(record);
	}
	
	@Transactional
	public int deleteByPrimaryKey(long id) {
		Ceremony ceremony = new Ceremony();
		ceremony.setCeremonyId(id);
		ceremony = ceremonyMapper.selectOne(ceremony);
		if (ceremony == null) {
			return 0;
		}
			
		CeremonySource ceremonySource = new CeremonySource();
		ceremonySource.setCeremonyId(id);
		ceremonySourceMapper.delete(ceremonySource); //删除关系
		
		return ceremonyMapper.deleteByPrimaryKey(id);
	}
	
	public PageInfo<CeremonyDTO> selectByKeyword(Integer pageNum, Integer pageSize,String keyword) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<CeremonyDTO>(ceremonyMapper.selectByKeyword(keyword));
	}

}
