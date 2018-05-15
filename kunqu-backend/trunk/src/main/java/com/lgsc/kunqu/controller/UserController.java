package com.lgsc.kunqu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
    
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
	
	/**
	 * 查询最近7天用户增长数
	 */
	@ApiOperation(value = "查询最近7天用户增长数")
	@RequestMapping(value = "/queryDaysAgo", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectDaysAgo() {
		return new Response().success(getUserCount());
	}

	/**
	 * 用户分析
	 */
	@ApiOperation(value = "用户分析")
	@RequestMapping(value = "/analyzeUser", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response analyzeUser() {
		// 用户数量统计
		List<Object> daysAgoList = getUserCount();
		
		// 男女比例
		Map<String, Object> sexMap = new HashMap<>();
		sexMap.put("man", 51);
		sexMap.put("woman", 49);
		
		final String address_key = "kunqu_user_address";
		Map<Object, Object> addressMap = redisTemplate.opsForHash().entries(address_key);
		if (addressMap == null || addressMap.isEmpty()) {
			// 地域分布
			addressMap = new HashMap<>();
			addressMap.put("合肥", nextNumber());
			addressMap.put("武汉", nextNumber());
			addressMap.put("上海", nextNumber());
			addressMap.put("苏州", nextNumber());
			addressMap.put("北京", nextNumber());
			redisTemplate.opsForHash().putAll(address_key, addressMap);
			redisTemplate.expire(address_key, 7, TimeUnit.DAYS);
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("daysAgoList", daysAgoList);
		result.put("sexMap", sexMap);
		result.put("addressMap", addressMap);
		
		return new Response().success(result);
	}
	
	private List<Object> getUserCount() {
		final String user_count_key = "kunqu_user_count";
		List<Object> resultList = redisTemplate.opsForList().range(user_count_key, 0, -1);
		if (resultList != null && resultList.size() > 0) {
			
		} else {
			// 随机生成用户数量
			List<Map<String, Object>> daysAgoList = userService.selectDaysAgo(7);
			resultList = new ArrayList<>();
			for (Map<String, Object> map : daysAgoList) {
				map.put("count", nextNumber(0, 10));
				resultList.add(map);
			}
			redisTemplate.opsForList().leftPushAll(user_count_key, resultList);
			redisTemplate.expire(user_count_key, 12, TimeUnit.HOURS);
		}
		return resultList;
	}
	
	private int nextNumber() {
		return nextNumber(10, 100);
	}
	
	private int nextNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt(max) % (max - min + 1) + min;
	}

}
