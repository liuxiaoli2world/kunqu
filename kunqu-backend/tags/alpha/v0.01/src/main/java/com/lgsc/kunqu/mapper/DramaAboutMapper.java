package com.lgsc.kunqu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lgsc.kunqu.common.dao.BaseMapper;
import com.lgsc.kunqu.model.DramaAbout;

public interface DramaAboutMapper extends BaseMapper<DramaAbout> {

	/**
	 * 根据剧典id查询相关剧典
	 */
	List<Long> selectAboutById(@Param("dramaId")Long dramaId);

	/**
	 * 根据dramaId,aboutId删除相关剧典
	 */
	Integer deleteByDramaIdAndAboutId(@Param("dramaId")Long dramaId,@Param("aboutId") Long aboutId);

	/**
	 * 根据aboutId删除相关剧典
	 */
	Integer deleteByAboutId(@Param("aboutId") Long aboutId);

	
	/**
	 * 根据dramaId删除相关剧典
	 */
	Integer deleteByDramaId(@Param("dramaId")Long dramaId);
}