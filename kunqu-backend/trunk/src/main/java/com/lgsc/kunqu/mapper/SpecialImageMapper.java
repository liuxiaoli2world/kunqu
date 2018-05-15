package com.lgsc.kunqu.mapper;

import java.util.List;

import com.lgsc.kunqu.common.dao.BaseMapper;
import com.lgsc.kunqu.model.SpecialImage;

public interface SpecialImageMapper extends BaseMapper<SpecialImage> {
	
	public List<SpecialImage> selectSpecialImageById(Long specialId);
}
