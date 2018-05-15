package com.lgsc.kunqu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
import com.lgsc.kunqu.model.Dir;
import com.lgsc.kunqu.service.DirService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 目录
 * @author 
 */
@Api(tags = "dir", description = "目录")
@RestController
@RequestMapping("/api/kunqu/dir")
public class DirController {
	
	@Autowired
	private DirService dirService;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
    
    /**
	 * 查询单个目录
	 */
	@ApiOperation(value = "查询单个目录")
	@RequestMapping(value = "/query/{dirId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(@ApiParam(value = "目录编号", required = true) @PathVariable Long dirId) {
		return new Response().success(dirService.selectByPrimaryKey(dirId));
	}
	
	/**
	 * 查询目录内容
	 */
	@ApiOperation(value = "查询目录内容")
	@RequestMapping(value = "/queryContent", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectContent(@ApiParam(value = "内容类型 overview 概述 thesis 典论", required = true) @RequestParam String type, 
			@ApiParam(value = "目录编号", required = true) @RequestParam Long dirId,
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		return new Response().success(dirService.selectContent(type, dirId, pageNum, pageSize)); 
	}
	
	/**
	 * 查询所有根目录
	 */
	@ApiOperation(value = "查询所有根目录")
	@RequestMapping(value = "/queryRoot", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectRoot() {
		return new Response().success(dirService.selectRoot());
	}
	
	/**
	 * 根据目录id查询祖先节点
	 */
	@ApiOperation(value = "根据目录id查询祖先节点")
	@RequestMapping(value = "/queryParentList/{dirId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectParentList(@ApiParam(value = "目录编号", required = true) @PathVariable Long dirId) {
		return new Response().success(dirService.selectParentList(dirId));
	}
	
	/**
	 * 根据目录id查询后代节点
	 */
	@ApiOperation(value = "根据目录id查询后代节点")
	@RequestMapping(value = "/queryChildList/{dirId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectChildList(@ApiParam(value = "目录编号", required = true) @PathVariable Long dirId) {
		return new Response().success(dirService.selectChildList(dirId));
	}
	
	/**
	 * 根据目录id查询子目录
	 */
	@ApiOperation(value = "根据目录id查询子目录")
	@RequestMapping(value = "/queryChild/{dirId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectChild(@ApiParam(value = "目录编号", required = true) @PathVariable Long dirId) {
		return new Response().success(dirService.selectChild(dirId));
	}
	
	/**
	 * 查询所有目录
	 */
	@ApiOperation(value = "查询所有目录")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectAll() {
		return new Response().success(dirService.selectAll());
	}
	
	/**
	 * 新增目录
	 */
	@ApiOperation(value = "新增目录")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertSelective(@ApiParam(value = "目录", required = true) @RequestBody @Validated Dir record) {
		Response response = new Response();
		if (record.getDirId() != null) {
			record.setDirId(null);
		}
		int num = dirService.insertSelective(record);
		if (num > 0) {
			response.success(record);
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 修改目录
	 */
	@ApiOperation(value = "修改目录")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateSelective(@ApiParam(value = "目录", required = true) @RequestBody @Validated Dir record) {
		Response response = new Response();
		if (record.getDirId() != null) {
			int num = dirService.updateByPrimaryKeySelective(record);
			if (num > 0) {
				response.success();
			} else {
				response.failure();
			}
		} else {
			response.failure("目录编号不能为空！");
		}
		return response;
	}
	
	/**
	 * 删除目录
	 */
	@ApiIgnore
	@ApiOperation(value = "删除目录")
	@RequestMapping(value = "/remove/{dirId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(@ApiParam(value = "目录编号", required = true) @PathVariable Long dirId) {
		Response response = new Response();
		int num = dirService.deleteByPrimaryKey(dirId);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 查询剧典、曲典、文章数量
	 */
	@ApiOperation(value = "查询剧典、曲典、文章数量，drama:剧典，special:曲典，article:文章")
	@RequestMapping(value = "/querySourceCount", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectSourceCount() {
		return new Response().success(dirService.selectSourceCount());
	}
	
	/**
	 * 资源分析
	 */
	@ApiOperation(value = "资源分析，chartList:图表标题,drama:剧典，special:曲典，article:文章，TopList:排行,ClickCount:点击量,CommentCount:评论量")
	@RequestMapping(value = "/analyzeSource", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response analyzeSource() {
		final String source_key = "kunqu_dir_source";
		// 从redis中取值
		Map<Object, Object> result = redisTemplate.opsForHash().entries(source_key);
		
		if (result == null || result.isEmpty()) {// 如果不存在或过期，则重新生成数据
			// 图表标题
			Map<String, Object> dramaRow = new HashMap<>();
			dramaRow.put("sourceType", "剧典");
			dramaRow.put("count", nextNumber());
			dramaRow.put("pageView", nextNumber());
			Map<String, Object> specialRow = new HashMap<>();
			specialRow.put("sourceType", "曲典");
			specialRow.put("count", nextNumber());
			specialRow.put("pageView", nextNumber());
			Map<String, Object> articleRow = new HashMap<>();
			articleRow.put("sourceType", "文章");
			articleRow.put("count", nextNumber());
			articleRow.put("pageView", nextNumber());
			List<Map<String, Object>> chartList = new ArrayList<>();
			chartList.add(dramaRow);
			chartList.add(specialRow);
			chartList.add(articleRow);
			
			// 剧典排行
			Map<String, Object> dramaMudanRow = new HashMap<>();
			dramaMudanRow.put("dramaName", "牡丹亭");
			dramaMudanRow.put("dramaClickCount", nextNumber());
			dramaMudanRow.put("dramaCommentCount", nextNumber());
			Map<String, Object> dramaXixiangRow = new HashMap<>();
			dramaXixiangRow.put("dramaName", "西厢记");
			dramaXixiangRow.put("dramaClickCount", nextNumber());
			dramaXixiangRow.put("dramaCommentCount", nextNumber());
			Map<String, Object> dramaYuzanRow = new HashMap<>();
			dramaYuzanRow.put("dramaName", "玉簪记");
			dramaYuzanRow.put("dramaClickCount", nextNumber());
			dramaYuzanRow.put("dramaCommentCount", nextNumber());
			Map<String, Object> dramaYouguiRow = new HashMap<>();
			dramaYouguiRow.put("dramaName", "幽闺记");
			dramaYouguiRow.put("dramaClickCount", nextNumber());
			dramaYouguiRow.put("dramaCommentCount", nextNumber());
			Map<String, Object> dramaJingchaiRow = new HashMap<>();
			dramaJingchaiRow.put("dramaName", "荆钗记");
			dramaJingchaiRow.put("dramaClickCount", nextNumber());
			dramaJingchaiRow.put("dramaCommentCount", nextNumber());
			List<Map<String, Object>> dramaTopList = new ArrayList<>();
			dramaTopList.add(dramaMudanRow);
			dramaTopList.add(dramaXixiangRow);
			dramaTopList.add(dramaYuzanRow);
			dramaTopList.add(dramaYouguiRow);
			dramaTopList.add(dramaJingchaiRow);
			
			// 曲典排行
			Map<String, Object> specialMudanRow = new HashMap<>();
			specialMudanRow.put("specialName", "牡丹亭");
			specialMudanRow.put("specialClickCount", nextNumber());
			specialMudanRow.put("specialCommentCount", nextNumber());
			Map<String, Object> specialXixiangRow = new HashMap<>();
			specialXixiangRow.put("specialName", "西厢记");
			specialXixiangRow.put("specialClickCount", nextNumber());
			specialXixiangRow.put("specialCommentCount", nextNumber());
			Map<String, Object> specialYuzanRow = new HashMap<>();
			specialYuzanRow.put("specialName", "玉簪记");
			specialYuzanRow.put("specialClickCount", nextNumber());
			specialYuzanRow.put("specialCommentCount", nextNumber());
			Map<String, Object> specialYouguiRow = new HashMap<>();
			specialYouguiRow.put("specialName", "幽闺记");
			specialYouguiRow.put("specialClickCount", nextNumber());
			specialYouguiRow.put("specialCommentCount", nextNumber());
			Map<String, Object> specialJingchaiRow = new HashMap<>();
			specialJingchaiRow.put("specialName", "荆钗记");
			specialJingchaiRow.put("specialClickCount", nextNumber());
			specialJingchaiRow.put("specialCommentCount", nextNumber());
			List<Map<String, Object>> specialTopList = new ArrayList<>();
			specialTopList.add(specialMudanRow);
			specialTopList.add(specialXixiangRow);
			specialTopList.add(specialYuzanRow);
			specialTopList.add(specialYouguiRow);
			specialTopList.add(specialJingchaiRow);
			
			// 文章排行
			Map<String, Object> articleMudanRow = new HashMap<>();
			articleMudanRow.put("articleName", "牡丹亭");
			articleMudanRow.put("articleClickCount", nextNumber());
			articleMudanRow.put("articleCommentCount", nextNumber());
			Map<String, Object> articleXixiangRow = new HashMap<>();
			articleXixiangRow.put("articleName", "西厢记");
			articleXixiangRow.put("articleClickCount", nextNumber());
			articleXixiangRow.put("articleCommentCount", nextNumber());
			Map<String, Object> articleYuzanRow = new HashMap<>();
			articleYuzanRow.put("articleName", "玉簪记");
			articleYuzanRow.put("articleClickCount", nextNumber());
			articleYuzanRow.put("articleCommentCount", nextNumber());
			Map<String, Object> articleYouguiRow = new HashMap<>();
			articleYouguiRow.put("articleName", "幽闺记");
			articleYouguiRow.put("articleClickCount", nextNumber());
			articleYouguiRow.put("articleCommentCount", nextNumber());
			Map<String, Object> articleJingchaiRow = new HashMap<>();
			articleJingchaiRow.put("articleName", "荆钗记");
			articleJingchaiRow.put("articleClickCount", nextNumber());
			articleJingchaiRow.put("articleCommentCount", nextNumber());
			List<Map<String, Object>> articleTopList = new ArrayList<>();
			articleTopList.add(articleMudanRow);
			articleTopList.add(articleXixiangRow);
			articleTopList.add(articleYuzanRow);
			articleTopList.add(articleYouguiRow);
			articleTopList.add(articleJingchaiRow);
			
			result = new HashMap<>();
			result.put("chartList", chartList);
			result.put("dramaTopList", dramaTopList);
			result.put("specialTopList", specialTopList);
			result.put("articleTopList", articleTopList);
			
			redisTemplate.opsForHash().putAll(source_key, result);
			redisTemplate.expire(source_key, 7, TimeUnit.DAYS);
		}
		
		return new Response().success(result);
	}
	
	private int nextNumber() {
		int min = 100;
		int max = 1000;
		Random random = new Random();
		return random.nextInt(max) % (max - min + 1) + min;
	}

}
