package com.lgsc.kunqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.model.Ceremony;
import com.lgsc.kunqu.service.CeremonyService;
import com.lgsc.kunqu.common.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @author 
 */
@Api(tags = "ceremony", description = "专题")
@RestController
@RequestMapping("/api/kunqu/ceremony")
public class CeremonyController {
	
	@Autowired
	private CeremonyService ceremonyService;
    
    /**
	 * 根据id查询专题
	 */
	@ApiOperation(value = "根据id查询专题")
	@RequestMapping(value = "/query/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(@ApiParam(value = "id", required = true) @PathVariable Long id) {
		return new Response().success(ceremonyService.selectByPrimaryKey(id));
	}
	
	/**
	 * 查询所有专题
	 */
	@ApiIgnore
	@ApiOperation(value = "查询所有专题")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectAll(@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		return new Response().success(ceremonyService.selectAll(pageNum, pageSize));
	}
	
	/**
	 * 根据关键字查询专题
	 */
	@ApiOperation(value = "根据关键字查询专题")
	@RequestMapping(value = "/queryByKeyword", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectByKeyword(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "关键字", required = false) @RequestParam(name = "keyword", required = false) String keyword) {
		return new Response().success(ceremonyService.selectByKeyword(pageNum, pageSize, keyword));
	}
	
	
	/**
	 * 新增专题
	 */
	@ApiOperation(value = "新增专题")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertSelective(@ApiParam(value = "专题", required = true) @RequestBody @Validated Ceremony record) {
		Response response = new Response();
		int num = ceremonyService.insertSelective(record);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 更新专题
	 */
	@ApiOperation(value = "更新专题")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateSelective(@ApiParam(value = "专题", required = true) @RequestBody @Validated Ceremony record) {
		Response response = new Response();
		int num = ceremonyService.updateByPrimaryKeySelective(record);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 删除专题
	 */
	@ApiOperation(value = "删除专题")
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(@ApiParam(value = "id", required = true) @PathVariable Long id) {
		Response response = new Response();
		int num = ceremonyService.deleteByPrimaryKey(id);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	

}
