package com.lgsc.kunqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.common.vo.Response;
import com.lgsc.kunqu.model.AgeTag;
import com.lgsc.kunqu.service.AgeTagService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 年代标签
 * 
 * @author pomay
 *
 */
@Api(tags = "agetag", description = "年代标签")
@RestController
@RequestMapping("/kunqu/agetag")
public class AgeTagController {

	@Autowired
	private AgeTagService ageTagService;

	/**
	 * 根据id查询年代标签
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据id查询年代标签")
	@RequestMapping(value = "/select/{ageTagId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(@ApiParam(value = "ageTagId", required = true) @PathVariable Long ageTagId) {
		return new Response().success(ageTagService.selectByPrimaryKey(ageTagId));
	}

	/**
	 * 查询所有年代标签
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ApiOperation(value = "查询所有年代标签")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectAll() {
		return new Response().success(ageTagService.selectAll());
	}

	/**
	 * 新增年代标签
	 * 
	 * @param record
	 * @return
	 */
	@ApiOperation(value = "新增年代标签")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertSelective(@ApiParam(value = "新增的年代标签信息") @RequestBody @Validated AgeTag record) {
		Response response = new Response();
		int num = ageTagService.insertSelective(record);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * 修改年代标签
	 * 
	 * @param record
	 * @return
	 */
	@ApiOperation(value = "修改年代标签")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateSelective(@ApiParam(value = "修改的年代标签信息") @RequestBody @Validated AgeTag record) {
		Response response = new Response();
		int num = ageTagService.updateByPrimaryKeySelective(record);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * 根据id删除年代标签
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据id删除年代标签")
	@RequestMapping(value = "/delete/{ageTagId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(@ApiParam(value = "ageTagId", required = true) @PathVariable Long ageTagId) {
		Response response = new Response();
		int num = ageTagService.deleteByPrimaryKey(ageTagId);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

}
