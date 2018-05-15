package com.lgsc.kunqu.mapper;

import java.util.List;

import com.lgsc.kunqu.common.dao.BaseMapper;
import com.lgsc.kunqu.model.DramaImage;

public interface DramaImageMapper extends BaseMapper<DramaImage> {

	/**
	 * 根据DramaId查询剧典封面
	 */
	List<DramaImage> selectByDramaId(long dramaId);
}