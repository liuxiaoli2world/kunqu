package com.lgsc.kunqu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lgsc.kunqu.common.dao.BaseMapper;
import com.lgsc.kunqu.dto.CeremonyDTO;
import com.lgsc.kunqu.model.Ceremony;

public interface CeremonyMapper extends BaseMapper<Ceremony> {
	
	/**
	 * 根据关键字查询专题
	 * @param keyword
	 * @return
	 */
	List<CeremonyDTO> selectByKeyword(@Param("keyword") String keyword);
	
}