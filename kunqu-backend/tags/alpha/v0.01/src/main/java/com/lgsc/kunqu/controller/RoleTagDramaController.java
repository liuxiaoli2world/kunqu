package com.lgsc.kunqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.model.RoleTagDrama;
import com.lgsc.kunqu.service.RoleTagDramaService;
import com.lgsc.kunqu.common.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 角色标签-剧典关系
 * @author 
 */
@Api(tags = "roletagdrama", description = "角色剧典关系")
@RestController
@RequestMapping("/api/kunqu/roletagdrama")
public class RoleTagDramaController {
	
	@Autowired
	private RoleTagDramaService roleTagDramaService;
    
    /**
	 * 
	 */
	@ApiIgnore
	@ApiOperation(value = "")
	@RequestMapping(value = "/query/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(@ApiParam(value = "", required = true) @PathVariable Long id) {
		return new Response().success(roleTagDramaService.selectByPrimaryKey(id));
	}
	
	/**
	 * 根据角色标签id查询剧典
	 */
	@ApiOperation(value = "根据角色标签id查询剧典")
	@RequestMapping(value = "/queryByRoleTag", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectDramaByRoleTagId(@ApiParam(value = "roleTagId", required = true) @RequestParam Long roleTagId,
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		return new Response().success(roleTagDramaService.selectDramaByRoleTagId(roleTagId, pageNum, pageSize));
	}
	
	/**
	 * 
	 */
	@ApiIgnore
	@ApiOperation(value = "")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectAll(@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		return new Response().success(roleTagDramaService.selectAll(pageNum, pageSize));
	}
	
	/**
	 * 新增角色剧典关系
	 */
	@ApiOperation(value = "新增角色剧典关系")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertSelective(@ApiParam(value = "角色剧典关系", required = true) @RequestBody @Validated RoleTagDrama record) {
		Response response = new Response();
		if (record.getRoleTagDramaId() != null) {
			record.setRoleTagDramaId(null);
		}
		int num = roleTagDramaService.insertSelective(record);
		if (num > 0) {
			response.success(record);
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 修改角色剧典关系
	 */
	@ApiOperation(value = "修改角色剧典关系")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateSelective(@ApiParam(value = "角色剧典关系", required = true) @RequestBody @Validated RoleTagDrama record) {
		Response response = new Response();
		if (record.getRoleTagDramaId() != null) {
			int num = roleTagDramaService.updateByPrimaryKeySelective(record);
			if (num > 0) {
				response.success();
			} else {
				response.failure();
			}
		} else {
			response.failure("角色剧典关系编号不能为空！");
		}
		return response;
	}
	
	/**
	 * 删除角色剧典关系
	 */
	@ApiOperation(value = "删除角色剧典关系")
	@RequestMapping(value = "/remove/{roleTagDramaId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(@ApiParam(value = "角色剧典关系编号", required = true) @PathVariable Long roleTagDramaId) {
		Response response = new Response();
		int num = roleTagDramaService.deleteByPrimaryKey(roleTagDramaId);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

}
