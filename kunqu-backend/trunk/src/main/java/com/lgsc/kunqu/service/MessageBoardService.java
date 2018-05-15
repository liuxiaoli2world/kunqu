package com.lgsc.kunqu.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.lgsc.kunqu.mapper.MessageBoardMapper;
import com.lgsc.kunqu.model.MessageBoard;

/**
 * 
 */
@Service
public class MessageBoardService {
	
	@Autowired
	private MessageBoardMapper messageBoardMapper;

	public int insert(MessageBoard record) {
		return messageBoardMapper.insert(record);
	}

	public int insertSelective(MessageBoard record) {
		String themeType = record.getThemeType();
		if (!"drama".equals(themeType) && !"special".equals(themeType) && !"article".equals(themeType)) {
			return -1;
		}
		record.setMessageBoardId(null);
		record.setCreatedAt(new Date());
		return messageBoardMapper.insertSelective(record);
	}

	public MessageBoard selectByPrimaryKey(long id) {
		return messageBoardMapper.selectByPrimaryKey(id);
	}
    
	/**
	 * 根据条件查询
	 * @param status 回复状态
	 * @param keyword 关键词
	 * @param pageNum 当前页数
	 * @param pageSize 每页数量
	 * @return MessageBoard集合
	 */
    public PageInfo<MessageBoard> selectByCondition(Short status, String keyword, int pageNum, int pageSize) {
    	Map<String, Object> param = new HashMap<>();
    	if (status != null) {
    		param.put("status", status);
		}
    	if (StringUtils.isNotBlank(keyword)) {
    		param.put("keyword", keyword);
		}
    	PageHelper.startPage(pageNum, pageSize);
    	List<MessageBoard> list = messageBoardMapper.selectByCondition(param);
		PageInfo<MessageBoard> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	public PageInfo<MessageBoard> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MessageBoard> list = messageBoardMapper.selectAll();
		PageInfo<MessageBoard> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	public int updateByPrimaryKey(MessageBoard record) {
		return messageBoardMapper.updateByPrimaryKey(record);
	}

	public int updateByPrimaryKeySelective(MessageBoard record) {
		record.setUpdatedAt(new Date());
		return messageBoardMapper.updateByPrimaryKeySelective(record);
	}

	public int deleteByPrimaryKey(long id) {
		return messageBoardMapper.deleteByPrimaryKey(id);
	}

}
