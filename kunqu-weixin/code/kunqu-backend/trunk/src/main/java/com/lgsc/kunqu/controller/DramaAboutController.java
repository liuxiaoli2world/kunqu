package com.lgsc.kunqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.model.Drama;
import com.lgsc.kunqu.model.DramaAbout;
import com.lgsc.kunqu.service.DramaAboutService;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.common.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 相关剧典
 * 
 * @author
 */
@Api(tags = "dramaabout", description = "相关剧典")
@RestController
@RequestMapping("/kunqu/dramaabout")
public class DramaAboutController {

	@Autowired
	private DramaAboutService dramaAboutService;

	/**
	 * 根据剧典id查询相关剧典
	 */
	@ApiOperation(value = "根据剧典id查询相关剧典")
	@RequestMapping(value = "/queryAbout", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectAboutById(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "剧典id", required = true) @RequestParam("dramaId") Long dramaId) {
		PageInfo<Drama> pageInfo = dramaAboutService.selectAboutById(pageNum, pageSize, dramaId);
		if (pageInfo != null) {
			return new Response().success(pageInfo);
		}
		return new Response().failure();
	}

	/**
	 * 添加相关剧典
	 */
	@ApiOperation(value = "添加相关剧典")
	@RequestMapping(value = "/addAbout", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertAbout(@ApiParam(value = "剧典", required = true) @RequestBody DramaAbout dramaAbout) {
		int num = dramaAboutService.insertAbout(dramaAbout);
		if (num > 0) {
			return new Response().success(num);
		}
		return new Response().failure();
	}
	
	/**
	 * 修改相关剧典
	 */
	@ApiOperation(value = "修改相关剧典")
	@RequestMapping(value = "/updateAbout", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateAbout(@ApiParam(value = "剧典", required = true) @RequestBody DramaAbout dramaAbout) {
		int num = dramaAboutService.updateAbout(dramaAbout);
		if (num > 0) {
			return new Response().success(num);
		}
		return new Response().failure();
	}

	/**
	 * 根据dramaAboutId（主键）删除相关剧典
	 */
	@ApiOperation(value = "根据dramaAboutId删除相关剧典")
	@RequestMapping(value = "/remove/{dramaAboutId}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response deleteByDramaAboutId(
			@ApiParam(value = "剧典id", required = true) @PathVariable("dramaAboutId") Long dramaAboutId) {
		int num = dramaAboutService.deleteByPrimaryKey(dramaAboutId);
		if (num > 0) {
			return new Response().success(num);
		}
		return new Response().failure();
	}

	/**
	 * 根据剧典id删除相关剧典
	 */
	@ApiOperation(value = "根据dramaId删除相关剧典")
	@RequestMapping(value = "/remove/{dramaId}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response deleteByDramaId(@ApiParam(value = "剧典id", required = true) @PathVariable("dramaId") Long dramaId) {
		int num = dramaAboutService.deleteByDramaId(dramaId);
		if (num > 0) {
			return new Response().success(num);
		}
		return new Response().failure();
	}

	/**
	 * 根据剧典id，相关剧典id删除相关剧典
	 */
	@ApiOperation(value = "根据剧典id，相关剧典id删除相关剧典")
	@RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response deleteByAboutId(@ApiParam(value = "剧典id", required = true) @RequestParam("dramaId") Long dramaId,
			@ApiParam(value = "相关剧典id", required = true) @RequestParam("aboutId") Long aboutId) {
		int num = dramaAboutService.deleteByDramaIdAndAboutId(dramaId, aboutId);
		if (num > 0) {
			return new Response().success(num);
		}
		return new Response().failure();
	}

}
