package com.lgsc.kunqu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.model.RepertoireTag;
import com.lgsc.kunqu.service.RepertoireTagService;
import com.lgsc.kunqu.common.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 剧目标签
 * 
 * @author
 */
@Api(tags = "repertoiretag", description = "剧目标签")
@RestController
@RequestMapping("/api/kunqu/repertoiretag")
public class RepertoireTagController {

	@Autowired
	private RepertoireTagService repertoireTagService;

	/**
	 * 查询剧目标签
	 */
	@ApiOperation(value = "剧目标签查询")
	@RequestMapping(value = "/query/{repertoireTagId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(
			@ApiParam(value = "剧目标签id", required = true) @PathVariable("repertoireTagId") Long repertoireTagId) {
		RepertoireTag repertoireTag = repertoireTagService.selectByPrimaryKey(repertoireTagId);
		if (repertoireTag != null) {
			return new Response().success(repertoireTag);
		}
		return new Response().failure();
	}

	/**
	 * 查询所有剧目标签
	 */
	@ApiOperation(value = "查询所有剧目标签")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectAll() {
		List<RepertoireTag> list = repertoireTagService.selectAll();
		if (list != null) {
			return new Response().success(list);
		}
		return new Response().failure();
	}

	/**
	 * 添加剧目标签
	 */
	@ApiOperation(value = "添加剧目标签")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertSelective(@ApiParam(value = "剧目标签信息") @RequestBody @Validated RepertoireTag record) {
		Response response = new Response();
		int num = repertoireTagService.insertSelective(record);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * 修改剧目标签
	 */
	@ApiOperation(value = "修改剧目标签")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateSelective(@ApiParam(value = "剧目标签信息") @RequestBody @Validated RepertoireTag repertoireTag) {
		Response response = new Response();
		int num = repertoireTagService.updateByPrimaryKeySelective(repertoireTag);
		if (num > 0) {
			response.success(num);
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * 删除剧目标签
	 */
	@ApiOperation(value = "删除剧目标签")
	@RequestMapping(value = "/remove/{repertoireTagId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(
			@ApiParam(value = "剧目标签id", required = true) @PathVariable("repertoireTagId") Long repertoireTagId) {
		Response response = new Response();
		int num = repertoireTagService.deleteByPrimaryKey(repertoireTagId);
		if (num > 0) {
			response.success(num);
		} else {
			response.failure();
		}
		return response;
	}

}
