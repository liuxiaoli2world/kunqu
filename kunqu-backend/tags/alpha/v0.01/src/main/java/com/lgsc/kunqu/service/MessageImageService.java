package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.lgsc.kunqu.mapper.MessageImageMapper;
import com.lgsc.kunqu.model.MessageImage;

/**
 * 
 */
@Service
public class MessageImageService {
	
	@Autowired
	private MessageImageMapper messageImageMapper;

	public int insert(MessageImage record) {
		return messageImageMapper.insert(record);
	}

	public int insertSelective(MessageImage record) {
		return messageImageMapper.insertSelective(record);
	}

	public MessageImage selectByPrimaryKey(long id) {
		return messageImageMapper.selectByPrimaryKey(id);
	}
    
    public List<MessageImage> selectAll() {
		return messageImageMapper.selectAll();
	}
	
	public PageInfo<MessageImage> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MessageImage> list = messageImageMapper.selectAll();
		PageInfo<MessageImage> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	public int updateByPrimaryKey(MessageImage record) {
		return messageImageMapper.updateByPrimaryKey(record);
	}

	public int updateByPrimaryKeySelective(MessageImage record) {
		return messageImageMapper.updateByPrimaryKeySelective(record);
	}

	public int deleteByPrimaryKey(long id) {
		return messageImageMapper.deleteByPrimaryKey(id);
	}

}
