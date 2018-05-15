package com.lgsc.kunqu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lgsc.kunqu.common.dao.BaseMapper;
import com.lgsc.kunqu.model.User;

public interface UserMapper extends BaseMapper<User> {
	
	/**
	 * 查询最近7天用户增长数
	 * @return 统计结果
	 */
	public List<Map<String, Object>> selectDaysAgo(@Param("list") List<Integer> list, @Param("pageSize") Integer pageSize);
	
}