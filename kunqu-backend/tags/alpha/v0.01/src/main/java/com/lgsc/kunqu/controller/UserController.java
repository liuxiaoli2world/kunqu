package com.lgsc.kunqu.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.common.vo.Response;
import com.lgsc.kunqu.common.vo.UserPassDto;
import com.lgsc.kunqu.model.User;
import com.lgsc.kunqu.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 用户
 * @author 
 */
@Api(tags = "user", description = "用户")
@RestController
@RequestMapping("/api/kunqu/user")
public class UserController {
	
	@Autowired
	private UserService userService;
    
    /**
	 * 查询用户
	 */
	@ApiOperation(value = "查询用户")
	@RequestMapping(value = "/query/{userId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(@ApiParam(value = "用户id", required = true) @PathVariable Long userId) {
		return new Response().success(userService.selectByPrimaryKey(userId));
	}
	
	/**
	 * 查询所有用户
	 */
	@ApiOperation(value = "查询所有用户")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectAll(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
	) {
		return new Response().success(userService.selectAll(pageNum, pageSize));
	}
	
	/**
	 * 注册
	 */
	@ApiOperation(value = "注册")
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response register(@ApiParam(value = "用户", required = true) @RequestBody @Validated UserPassDto record) {
		Response response = new Response();
		User user = userService.selectByUsername(record.getUsername());
		if (user != null) {
			response.failure("该用户名已被注册！");
		} else {
			int num = userService.insertSelective(record);
			if (num > 0) {
				User user2 = userService.selectByUsername(record.getUsername());
				response.success(user2);
			} else {
				response.failure();
			}
		}
		return response;
	}
	
	/**
	 * 登录
	 */
	@ApiOperation(value = "登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json; charset=UTF-8")
	public Response login(
			@ApiParam(value = "用户名", required = true) @RequestParam String username,
			@ApiParam(value = "密码", required = true) @RequestParam String password
	) {
		Response response = new Response();
		User user = userService.selectByUsername(username);
		if (user != null && user.getPassword().equals(DigestUtils.md5Hex(password))) {
			response.success(user);
		} else {
			response.failure("用户或密码错误！");
		}
		return response;
	}
	
	/**
	 * 
	 */
	@ApiIgnore
	@ApiOperation(value = "")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateSelective(@ApiParam(value = "", required = true) @RequestBody @Validated User record) {
		Response response = new Response();
		int num = userService.updateByPrimaryKeySelective(record);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 
	 */
	@ApiIgnore
	@ApiOperation(value = "")
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(@ApiParam(value = "", required = true) @PathVariable Long id) {
		Response response = new Response();
		int num = userService.deleteByPrimaryKey(id);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

}
