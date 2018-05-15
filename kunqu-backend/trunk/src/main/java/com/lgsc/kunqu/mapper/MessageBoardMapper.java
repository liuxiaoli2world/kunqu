package com.lgsc.kunqu.mapper;

import java.util.List;
import java.util.Map;

import com.lgsc.kunqu.common.dao.BaseMapper;
import com.lgsc.kunqu.model.MessageBoard;

public interface MessageBoardMapper extends BaseMapper<MessageBoard> {
	
	/**
	 * 根据条件查询
	 */
	public List<MessageBoard> selectByCondition(Map<String, Object> param);
	
}