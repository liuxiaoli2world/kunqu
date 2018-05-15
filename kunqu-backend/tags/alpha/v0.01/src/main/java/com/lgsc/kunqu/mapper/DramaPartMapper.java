package com.lgsc.kunqu.mapper;

import java.util.List;

import com.lgsc.kunqu.common.dao.BaseMapper;
import com.lgsc.kunqu.model.DramaPart;

public interface DramaPartMapper extends BaseMapper<DramaPart> {

	/**
	 * 根据剧典id查询剧典片段
	 */
	List<DramaPart> selectPartById(Long dramaId);

	/**
	 * 根据dramaId删除剧典片段
	 */
	Integer deleteByDramaId(Long dramaId);
}