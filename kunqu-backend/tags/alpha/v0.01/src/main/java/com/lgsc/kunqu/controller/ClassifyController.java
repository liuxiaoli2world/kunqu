package com.lgsc.kunqu.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.common.vo.Response;
import com.lgsc.kunqu.service.ClassifyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 分类
 * @author
 */
@Api(tags = "classify", description = "分类")
@RestController
@RequestMapping("/api/kunqu/classify")
public class ClassifyController {

	@Autowired
	private ClassifyService classifyService;

	/**
	 * 根据标签查询
	 * @param queryType 查询类型，drama:剧典，special:曲典，article:文章
	 * @param ageTagId 年代标签编号
	 * @param authorTagId 作者标签编号
	 * @param repertoireTagId 剧目标签编号
	 * @param roleTagId 角色标签编号
	 * @param keyword 关键字
	 * @param pageNum 当前页
	 * @param pageSize 每页数量
	 * @return Response
	 */
	@ApiOperation(value = "根据标签查询")
	@RequestMapping(value = "/queryByTag", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectContent (
			@ApiParam(value = "查询类型，drama:剧典，special:曲典，article:文章", required = true) @RequestParam String queryType,
			@ApiParam(value = "年代标签编号", required = false) @RequestParam(name = "ageTagId", required = false) Long ageTagId,
			@ApiParam(value = "作者标签编号", required = false) @RequestParam(name = "authorTagId", required = false) Long authorTagId,
			@ApiParam(value = "剧目标签编号", required = false) @RequestParam(name = "repertoireTagId", required = false) Long repertoireTagId,
			@ApiParam(value = "角色标签编号", required = false) @RequestParam(name = "roleTagId", required = false) Long roleTagId,
			@ApiParam(value = "关键字", required = false) @RequestParam(name = "keyword", required = false) String keyword,
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
	) {
		Map<String, Object> param = new HashMap<>();
		param.put("queryType", queryType);
		param.put("ageTagId", ageTagId);
		param.put("authorTagId", authorTagId);
		param.put("repertoireTagId", repertoireTagId);
		param.put("roleTagId", roleTagId);
		param.put("keyword", keyword);
		return new Response().success(classifyService.selectByTag(param, pageNum, pageSize));
	}
	
	/**
	 * 根据关键字查询
	 * @param queryType 查询类型，drama:剧典，special:曲典，article:文章
	 * @param keyword 关键字
	 * @param pageNum 当前页
	 * @param pageSize 每页数量
	 * @return Response
	 */
	@ApiOperation(value = "根据关键字查询")
	@RequestMapping(value = "/queryByKeyword", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectByKeyword (
			@ApiParam(value = "查询类型，drama:剧典，special:曲典，article:文章", required = true) @RequestParam String queryType,
			@ApiParam(value = "关键字", required = false) @RequestParam(name = "keyword", required = false) String keyword,
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
	) {
		return new Response().success(classifyService.selectByKeyword(queryType, keyword, pageNum, pageSize));
	}

}
