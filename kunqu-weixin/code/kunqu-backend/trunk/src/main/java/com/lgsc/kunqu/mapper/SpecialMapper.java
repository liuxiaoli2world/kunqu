package com.lgsc.kunqu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lgsc.kunqu.common.dao.BaseMapper;
import com.lgsc.kunqu.model.Special;

public interface SpecialMapper extends BaseMapper<Special> {
	
	/**
	 * 根据角色标签id查询曲典专辑
	 */
	public List<Special> selectSpecialByRoleTagId(long roleTagId);
	
	/**
	 * 根据剧目标签id查询曲典专辑
	 */
	public List<Special> selectSpecialByRepertoireTagId(long RepertoireTagId);
	
	/**
	 * 根据作者标签id查询曲典专辑
	 * @param authorTagId
	 * @return
	 */
	public List<Special> selectSpecialByAuthorTagId(long authorTagId);
	/**
	 * 根据年代标签id查询曲典专辑
	 * @param ageTagId
	 * @return
	 */
	public List<Special> selectSpecialByAgeTagId(long ageTagId);
	/**
	 * 查询所有曲典专辑要包含Opera
	 * @return
	 */
	public List<Special> selectAllSpecial();
	
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
	public List<Special> selectByTag(Map<String, Object> param);
	
	/**
	 * 根据关键字查询
	 * @param keyword
	 * @return
	 */
	public List<Special> selectByKeyword(@Param("keyword") String keyword);
	
	/**
	 * 查询曲典专辑详细
	 * @param Special
	 * @return
	 */
	public Special selectOneSpecial(Long specialId);
	
	/**
	 * 查询曲典推荐
	 * @return
	 */	
	public List<Special> selectSpecialIsSubject();
	
	/**
	 * 查询前台推荐
	 * @return
	 */
	public List<Special> selectSpecialIsIndexRecommend();
	
	
	
	
	
}