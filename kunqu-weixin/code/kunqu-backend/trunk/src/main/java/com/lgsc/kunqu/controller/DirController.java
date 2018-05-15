package com.lgsc.kunqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.model.Dir;
import com.lgsc.kunqu.service.DirService;
import com.lgsc.kunqu.common.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 目录
 * @author 
 */
@Api(tags = "dir", description = "目录")
@RestController
@RequestMapping("/kunqu/dir")
public class DirController {
	
	@Autowired
	private DirService dirService;
    
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

}
