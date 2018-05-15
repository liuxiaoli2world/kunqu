package com.lgsc.kunqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.model.RoleTagArticle;
import com.lgsc.kunqu.service.RoleTagArticleService;
import com.lgsc.kunqu.common.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 角色文章关系
 * @author 
 */
@Api(tags = "roletagarticle", description = "角色文章关系")
@RestController
@RequestMapping("/api/kunqu/roletagarticle")
public class RoleTagArticleController {
	
	@Autowired
	private RoleTagArticleService roleTagArticleService;
    
    /**
	 * 
	 */
	@ApiIgnore
	@ApiOperation(value = "")
	@RequestMapping(value = "/query/{roleTagArticleId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(@ApiParam(value = "", required = true) @PathVariable Long roleTagArticleId) {
		return new Response().success(roleTagArticleService.selectByPrimaryKey(roleTagArticleId));
	}
	
	/**
	 * 根据角色标签id查询文章
	 */
	@ApiOperation(value = "根据角色标签id查询文章")
	@RequestMapping(value = "/queryByRoleTag", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectArticleByRoleTagId(@ApiParam(value = "roleTagId", required = true) @RequestParam Long roleTagId,
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		return new Response().success(roleTagArticleService.selectArticleByRoleTagId(roleTagId, pageNum, pageSize));
	}
	
	/**
	 * 
	 */
	@ApiIgnore
	@ApiOperation(value = "")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectAll(@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		return new Response().success(roleTagArticleService.selectAll(pageNum, pageSize));
	}
	
	/**
	 * 新增角色文章关系
	 */
	@ApiOperation(value = "新增角色文章关系")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertSelective(@ApiParam(value = "角色文章关系", required = true) @RequestBody @Validated RoleTagArticle record) {
		Response response = new Response();
		if (record.getRoleTagArticleId() != null) {
			record.setRoleTagArticleId(null);
		}
		int num = roleTagArticleService.insertSelective(record);
		if (num > 0) {
			response.success(record);
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 修改角色文章关系
	 */
	@ApiOperation(value = "修改角色文章关系")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateSelective(@ApiParam(value = "角色文章关系", required = true) @RequestBody @Validated RoleTagArticle record) {
		Response response = new Response();
		if (record.getRoleTagArticleId() != null) {
			int num = roleTagArticleService.updateByPrimaryKeySelective(record);
			if (num > 0) {
				response.success();
			} else {
				response.failure();
			}
		} else {
			response.failure("角色文章关系编号不能为空！");
		}
		return response;
	}
	
	/**
	 * 删除角色文章关系
	 */
	@ApiOperation(value = "删除角色文章关系")
	@RequestMapping(value = "/remove/{roleTagArticleId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(@ApiParam(value = "角色文章关系编号", required = true) @PathVariable Long roleTagArticleId) {
		Response response = new Response();
		int num = roleTagArticleService.deleteByPrimaryKey(roleTagArticleId);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

}
