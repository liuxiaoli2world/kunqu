package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.DramaMapper;
import com.lgsc.kunqu.mapper.DramaPartMapper;
import com.lgsc.kunqu.model.DramaPart;

/**
 * 
 */
@Service
public class DramaPartService {
	
	@Autowired
	private DramaPartMapper dramaPartMapper;

	@Autowired
	private DramaMapper dramaMapper;
	
	/**
	 * 查询剧典片段详情
	 */
	public DramaPart selectByPrimaryKey(Long dramaPartId) {
		return dramaPartMapper.selectByPrimaryKey(dramaPartId);
	}
	
	public PageInfo<DramaPart> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DramaPart> list = dramaPartMapper.selectAll();
		PageInfo<DramaPart> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	

	/**
	 * 根据剧典id查询剧典片段
	 */
	public List<DramaPart> selectPartById(Long dramaId) {
		return dramaPartMapper.selectPartById(dramaId);
	}
	
	/**
	 * 添加剧典片段
	 */
	public int insertSelective(DramaPart dramaPart) {
		if(dramaMapper.selectById(dramaPart.getDramaId())==null){
			return -1;
		}
		return dramaPartMapper.insertSelective(dramaPart);
	}
	
	/**
	 * 根据dramaPartId（主键）删除剧典片段
	 */
	public int deleteByPrimaryKey(Long dramaPartId) {
		return dramaPartMapper.deleteByPrimaryKey(dramaPartId); 
	}

	/**
	 * 根据dramaId删除剧典片段
	 */
	public int deleteByDramaId(Long dramaId) {
		return dramaPartMapper.deleteByDramaId(dramaId);
	}

	/**
	 * 修改剧典片段
	 */
	public int updateDramaImage(DramaPart dramaPart) {
		if(dramaMapper.selectById(dramaPart.getDramaId())==null){
			return -1;
		}
		return dramaPartMapper.updateByPrimaryKeySelective(dramaPart);
	}

}
