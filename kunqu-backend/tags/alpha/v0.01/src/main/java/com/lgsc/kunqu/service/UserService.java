package com.lgsc.kunqu.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.common.vo.UserPassDto;
import com.lgsc.kunqu.mapper.UserMapper;
import com.lgsc.kunqu.model.User;

/**
 * 用户
 */
@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;

	public int insert(User record) {
		return userMapper.insert(record);
	}

	public int insertSelective(UserPassDto userDto) {
		userDto.setUserId(null);
		userDto.setCreatedAt(new Date());
		// 加密
		String passwordMd5 = DigestUtils.md5Hex(userDto.getPassword());
		userDto.setPassword(passwordMd5);
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		return userMapper.insertSelective(user);
	}

	public User selectByPrimaryKey(long id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据用户名查用户
	 * @param username
	 * @return
	 */
	public User selectByUsername(String username) {
		User user = new User();
		user.setUsername(username);
		return userMapper.selectOne(user);
	}
    
    public List<User> selectAll() {
		return userMapper.selectAll();
	}
	
	public PageInfo<User> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAll();
		PageInfo<User> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	public int updateByPrimaryKey(User record) {
		return userMapper.updateByPrimaryKey(record);
	}

	public int updateByPrimaryKeySelective(User record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	public int deleteByPrimaryKey(long id) {
		return userMapper.deleteByPrimaryKey(id);
	}

}
