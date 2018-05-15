package com.lgsc.kunqu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lgsc.kunqu.common.dao.BaseMapper;
import com.lgsc.kunqu.model.Dir;

public interface DirMapper extends BaseMapper<Dir> {
	
	/**
	 * 查询典论
	 * @param dirId 目录编号
	 * @return 典论
	 */
	public List<Map<String, Object>> selectComposite(long dirId);
	
	/**
	 * 根据目录id查询祖先节点，不包括当前目录
	 * @param dirId 目录编号
	 * @return 祖先节点
	 */
	public List<Dir> selectParentList(@Param("dirId") long dirId);
	
	/**
	 * 根据目录id查询后代节点，不包括当前目录
	 * @param dirId 目录编号
	 * @return 后代节点
	 */
	public List<Dir> selectChildList(@Param("dirId") long dirId);
	
	/**
	 * 根据目录id查询子目录
	 * @param dirId 目录编号
	 * @return 子目录
	 */
	public List<Dir> selectChild(long dirId);
	
	/**
	 * 查询资源数量
	 * @return 统计结果
	 */
	public List<Map<String, Object>> selectSourceCount();
	
}