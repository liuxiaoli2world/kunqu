package com.lgsc.kunqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.common.vo.Response;
import com.lgsc.kunqu.model.Drama;
import com.lgsc.kunqu.service.DramaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 剧典
 * 
 * @author
 */
@Api(tags = "drama", description = "剧典")
@RestController
@RequestMapping("/kunqu/drama")
public class DramaController {

	@Autowired
	private DramaService dramaService;

	/**
	 * 根据dramaId查询剧典
	 */
	@ApiOperation(value = "根据dramaId查询剧典")
	@RequestMapping(value = "/queryDetail/{dramaId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectById(@ApiParam(value = "剧典id", required = true) @PathVariable("dramaId") Long dramaId) {
		Drama drama = dramaService.selectByPrimaryKey(dramaId);
		if (drama != null) {
			return new Response().success(drama);
		}
		return new Response().failure();
	}

	/**
	 * 根据类型查询 0 全部 1 最热剧典 2 列表页剧典推荐 3 首页剧典推荐 4 折子戏 5大典专题
	 */
	@ApiOperation(value = "根据类型查询 0 全部 1 最热剧典 2 列表页剧典推荐 3 首页剧典推荐 4 折子戏 5大典专题")
	@RequestMapping(value = "/query", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "查询类型（ 0  所有、1 最热剧典、2 列表页剧典推荐、3 首页剧典推荐、4 折子戏 、5大典专题）", required = true) @RequestParam("type") Integer type) {
		PageInfo<Drama> infoList = dramaService.selects(pageNum, pageSize, type);
		if (infoList != null) {
			return new Response().success(infoList);
		}
		return new Response().failure();
	}

	/**
	 * 根据年代标签id分页查找剧典
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param ageTagId
	 * @return
	 */
	@ApiOperation(value = "根据年代标签id分页查找剧典")
	@RequestMapping(value = "/selectDramaByAgeTagId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectDramaByAgeTagId(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "年代标签id", required = true) @RequestParam("ageTagId") long ageTagId) {
		PageInfo<Drama> infoList = dramaService.selectDramaByAgeTagId(pageNum, pageSize, ageTagId);
		if (infoList != null) {
			return new Response().success(infoList);
		}
		return new Response().failure();
	}

	/**
	 * 根据剧目标签id分页查找剧典
	 */
	@ApiOperation(value = "根据剧目标签id分页查找剧典")
	@RequestMapping(value = "/selectDramaByRepertoireTagId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectDramaByRepertoireTagId(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "剧目标签id", required = true) @RequestParam("repertoireTagId") long repertoireTagId) {
		PageInfo<Drama> infoList = dramaService.selectDramaByRepertoireTagId(pageNum, pageSize, repertoireTagId);
		if (infoList != null) {
			return new Response().success(infoList);
		}
		return new Response().failure();
	}
	/**
	 * 根据作者标签id分页查找剧典
	 */
	@ApiOperation(value = "根据作者标签id分页查找剧典")
	@RequestMapping(value = "/selectDramaByAuthorTagId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectDramaByAuthorTagId(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "作者标签id", required = true) @RequestParam("AuthorTagId") long AuthorTagId) {
		PageInfo<Drama> infoList = dramaService.selectDramaByAuthorTagId(pageNum, pageSize, AuthorTagId);
		if (infoList != null) {
			return new Response().success(infoList);
		}
		return new Response().failure();
	}
	/**
	 * 添加剧典
	 */
	@ApiOperation(value = "插入剧典")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertDrama(@ApiParam(value = "剧典", required = true) @RequestBody Drama drama) {
		int num = dramaService.insertDrama(drama);
		if (num > 0) {
			return new Response().success(num);
		}
		return new Response().failure();
	}

	/**
	 * 修改剧典
	 */
	@ApiOperation(value = "修改剧典")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateDrama(@ApiParam(value = "剧典", required = true) @RequestBody Drama drama) {
		int num = dramaService.updateDrama(drama);
		if (num > 0) {
			return new Response().success(num);
		}
		return new Response().failure();
	}

	/**
	 * 删除剧典
	 */
	@ApiOperation(value = "根据dramaId删除剧典")
	@RequestMapping(value = "/remove/{dramaId}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response deleteById(@ApiParam(value = "剧典id", required = true) @PathVariable("dramaId") Long dramaId) {
		int num = dramaService.deleteByPrimaryKey(dramaId);
		if (num > 0) {
			return new Response().success(num);
		}
		return new Response().failure();
	}
}
