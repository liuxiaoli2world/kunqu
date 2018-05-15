package com.lgsc.kunqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.model.RoleTagSpecial;
import com.lgsc.kunqu.service.RoleTagSpecialService;
import com.lgsc.kunqu.common.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 角色曲典专辑关系
 * @author 
 */
@Api(tags = "roletagspecial", description = "角色曲典专辑关系")
@RestController
@RequestMapping("/kunqu/roletagspecial")
public class RoleTagSpecialController {
	
	@Autowired
	private RoleTagSpecialService roleTagSpecialService;
    
    /**
	 * 
	 */
	@ApiIgnore
	@ApiOperation(value = "")
	@RequestMapping(value = "/query/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(@ApiParam(value = "", required = true) @PathVariable Long id) {
		return new Response().success(roleTagSpecialService.selectByPrimaryKey(id));
	}
	
	/**
	 * 根据角色标签id查询曲典专辑
	 */
	@ApiOperation(value = "根据角色标签id查询曲典专辑")
	@RequestMapping(value = "/queryByRoleTag", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectSpecialByRoleTagId(@ApiParam(value = "roleTagId", required = true) @RequestParam Long roleTagId,
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		return new Response().success(roleTagSpecialService.selectSpecialByRoleTagId(roleTagId, pageNum, pageSize));
	}
	
	/**
	 * 
	 */
	@ApiIgnore
	@ApiOperation(value = "")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectAll(@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		return new Response().success(roleTagSpecialService.selectAll(pageNum, pageSize));
	}
	
	/**
	 * 新增角色专辑关系
	 */
	@ApiOperation(value = "新增角色专辑关系")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertSelective(@ApiParam(value = "角色专辑关系", required = true) @RequestBody @Validated RoleTagSpecial record) {
		Response response = new Response();
		if (record.getRoleTagSpecialId() != null) {
			record.setRoleTagSpecialId(null);
		}
		int num = roleTagSpecialService.insertSelective(record);
		if (num > 0) {
			response.success(record);
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 修改角色专辑关系
	 */
	@ApiOperation(value = "修改角色专辑关系")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateSelective(@ApiParam(value = "角色专辑关系", required = true) @RequestBody @Validated RoleTagSpecial record) {
		Response response = new Response();
		if (record.getRoleTagSpecialId() != null) {
			int num = roleTagSpecialService.updateByPrimaryKeySelective(record);
			if (num > 0) {
				response.success();
			} else {
				response.failure();
			}
		} else {
			response.failure("角色专辑关系编号不能为空！");
		}
		return response;
	}
	
	/**
	 * 删除角色专辑关系
	 */
	@ApiOperation(value = "删除角色专辑关系")
	@RequestMapping(value = "/remove/{roleTagSpecialId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(@ApiParam(value = "角色专辑关系编号", required = true) @PathVariable Long roleTagSpecialId) {
		Response response = new Response();
		int num = roleTagSpecialService.deleteByPrimaryKey(roleTagSpecialId);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

}
