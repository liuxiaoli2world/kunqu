package com.lgsc.kunqu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lgsc.kunqu.common.dao.BaseMapper;
import com.lgsc.kunqu.model.CeremonySource;

public interface CeremonySourceMapper extends BaseMapper<CeremonySource> {
	
	/**
	 * 查询主题资源
	 * @param id 专题id
	 * @param sourceType 资源类型
	 * @return
	 */
	List<Map<String, Object>> selectBy(@Param("id") Long id, @Param("sourceType")  String sourceType);
	
	/**
	 * 查询资源id 
	 * @param id 专题id
	 * @param sourceType 资源类型
	 * @return
	 */
	List<Long> selectSourceIds(@Param("id")Long id, @Param("sourceType") String sourceType);
}