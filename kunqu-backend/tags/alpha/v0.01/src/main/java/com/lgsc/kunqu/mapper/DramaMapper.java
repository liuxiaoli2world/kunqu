package com.lgsc.kunqu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lgsc.kunqu.common.dao.BaseMapper;
import com.lgsc.kunqu.model.Drama;

public interface DramaMapper extends BaseMapper<Drama> {
	
	/**
	 * 根据类型查询 0 全部  1 最热剧典 2 列表页剧典推荐 3 首页剧典推荐 4 折子戏 5大典专题
	 */
	List<Drama> selects(@Param("type")Integer type);
	
	/**
	 * 根据剧典id查询剧典,带图
	 */
	Drama selectById(@Param("dramaId")Long dramaId);
	/**
	 * 根据剧典id list查询剧典,带图
	 */
	public List<Drama> selectByIds(@Param("dramaIds")List<Long> dramaIds);
	/**
	 * 根据角色标签id查询剧典
	 */
	public List<Drama> selectDramaByRoleTagId(long roleTagId);
	/**
	 * 根据作者标签查询剧典
	 * @param authorTagId
	 * @return
	 */
	public List<Drama> selectDramaByAuthorTagId(long authorTagId);
	/**
	 * 根据年代标签id查询剧典
	 * @param ageTagId
	 * @return
	 */
	public List<Drama> selectDramaByAgeTagId(long ageTagId);
	
	/**
	 * 修改目录编号为空
	 * @param dirId
	 * @return
	 */
	public int updateDirIdNull(long dirId);
	
	/**
	 * 根据标签查询
	 * @param param
	 * @return
	 */
	public List<Drama> selectByTag(Map<String, Object> param);
	
	/**
	 * 根据关键字查询
	 * @param keyword
	 * @return
	 */
	public List<Drama> selectByKeyword(@Param("keyword") String keyword);
	
	/**
	 * 根据剧目标签查询剧典
	 */
	public List<Drama> selectDramaByRepertoireTagId(@Param("repertoireTagId")long repertoireTagId);

	
}