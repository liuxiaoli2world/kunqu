package com.lgsc.kunqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.model.Overview;
import com.lgsc.kunqu.service.OverviewService;
import com.lgsc.kunqu.common.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 概述
 * @author 
 */
@Api(tags = "overview", description = "概述")
@RestController
@RequestMapping("/api/kunqu/overview")
public class OverviewController {
	
	@Autowired
	private OverviewService overviewService;
    
    /**
	 * 查询概述
	 */
	@ApiOperation(value = "查询概述")
	@RequestMapping(value = "/query/{overviewId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(@ApiParam(value = "概述编号", required = true) @PathVariable Long overviewId) {
		return new Response().success(overviewService.selectByPrimaryKey(overviewId));
	}
	
	/**
	 * 查询所有概述
	 */
	@ApiOperation(value = "查询所有概述")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectAll(@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		return new Response().success(overviewService.selectAll(pageNum, pageSize));
	}
	
	/**
	 * 新增概述
	 */
	@ApiOperation(value = "新增概述")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertSelective(@ApiParam(value = "概述", required = true) @RequestBody @Validated Overview record) {
		Response response = new Response();
		if (record.getOverviewId() != null) {
			record.setOverviewId(null);
		}
		int num = overviewService.insertSelective(record);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 修改概述
	 */
	@ApiOperation(value = "修改概述")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateSelective(@ApiParam(value = "概述", required = true) @RequestBody @Validated Overview record) {
		Response response = new Response();
		if (record.getOverviewId() != null) {
			int num = overviewService.updateByPrimaryKeySelective(record);
			if (num > 0) {
				response.success();
			} else {
				response.failure();
			}
		} else {
			response.failure("概述编号不能为空！");
		}
		return response;
	}
	
	/**
	 * 删除概述
	 */
	@ApiOperation(value = "删除概述")
	@RequestMapping(value = "/remove/{overviewId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(@ApiParam(value = "概述编号", required = true) @PathVariable Long overviewId) {
		Response response = new Response();
		int num = overviewService.deleteByPrimaryKey(overviewId);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

}
