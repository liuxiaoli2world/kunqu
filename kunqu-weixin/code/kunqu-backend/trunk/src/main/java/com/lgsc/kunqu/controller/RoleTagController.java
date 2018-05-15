package com.lgsc.kunqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.common.vo.Response;
import com.lgsc.kunqu.model.RoleTag;
import com.lgsc.kunqu.service.RoleTagService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 角色标签
 * @author yqj
 */
@Api(tags = "roletag", description = "角色标签")
@RestController
@RequestMapping("/kunqu/roletag")
public class RoleTagController {
	
	@Autowired
	private RoleTagService roleTagService;
    
    /**
	 * 查询单个标签
	 */
	@ApiOperation(value = "查询单个标签")
	@RequestMapping(value = "/query/{roleTagId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(@ApiParam(value = "角色标签编号", required = true) @PathVariable Long roleTagId) {
		return new Response().success(roleTagService.selectByPrimaryKey(roleTagId));
	}
	
	/**
	 * 查询所有角色标签
	 */
	@ApiOperation(value = "查询所有角色标签")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectAll() {
		return new Response().success(roleTagService.selectAll());
	}
	
	/**
	 * 新增角色标签
	 */
	@ApiOperation(value = "新增角色标签")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertSelective(@ApiParam(value = "角色标签", required = true) @RequestBody @Validated RoleTag record) {
		Response response = new Response();
		if (record.getRoleTagId() != null) {
			record.setRoleTagId(null);
		}
		int num = roleTagService.insertSelective(record);
		if (num > 0) {
			response.success(record);
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 修改角色标签
	 */
	@ApiOperation(value = "修改角色标签")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateSelective(@ApiParam(value = "角色标签", required = true) @RequestBody @Validated RoleTag record) {
		Response response = new Response();
		if (record.getRoleTagId() != null) {
			int num = roleTagService.updateByPrimaryKeySelective(record);
			if (num > 0) {
				response.success();
			} else {
				response.failure();
			} 
		} else {
			response.failure("角色标签编号不能为空！");
		}
		return response;
	}
	
	/**
	 * 删除角色标签
	 */
	@ApiOperation(value = "删除角色标签")
	@RequestMapping(value = "/remove/{roleTagId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(@ApiParam(value = "角色标签编号", required = true) @PathVariable Long roleTagId) {
		Response response = new Response();
		int num = roleTagService.deleteByPrimaryKey(roleTagId);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

}
