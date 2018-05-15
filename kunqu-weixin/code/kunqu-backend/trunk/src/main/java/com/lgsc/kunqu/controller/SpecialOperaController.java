package com.lgsc.kunqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.model.SpecialOpera;
import com.lgsc.kunqu.service.SpecialOperaService;
import com.lgsc.kunqu.common.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 曲典-曲典专辑关系
 * @author 
 */
@Api(tags = "specialopera", description = "曲典-曲典专辑关系")
@RestController
@RequestMapping("/kunqu/specialopera")
public class SpecialOperaController {
	
	@Autowired
	private SpecialOperaService specialOperaService;
    
    /**
	 * 
	 */
	@ApiOperation(value = "查询曲典-曲典专辑关系")
	@RequestMapping(value = "/query/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(@ApiParam(value = "", required = true) @PathVariable Long id) {
		return new Response().success(specialOperaService.selectByPrimaryKey(id));
	}
	
	/**
	 * 
	 */
	@ApiOperation(value = "查询所有曲典-曲典专辑关系")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectAll(@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		return new Response().success(specialOperaService.selectAll(pageNum, pageSize));
	}
	
	/**
	 * 
	 */
	@ApiOperation(value = "新增曲典-曲典专辑关系")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertSelective(@ApiParam(value = "曲典-曲典专辑关系") @RequestBody @Validated SpecialOpera record) {
		Response response = new Response();
		int num = specialOperaService.insertSelective(record);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 
	 */
	@ApiOperation(value = "修改曲典-曲典专辑关系")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateSelective(@ApiParam(value = "曲典-曲典专辑关系") @RequestBody @Validated SpecialOpera record) {
		Response response = new Response();
		int num = specialOperaService.updateByPrimaryKeySelective(record);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 
	 */
	@ApiOperation(value = "删除曲典-曲典专辑关系")
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(@ApiParam(value = "曲典-曲典专辑关系编号", required = true) @PathVariable Long id) {
		Response response = new Response();
		int num = specialOperaService.deleteByPrimaryKey(id);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

}
