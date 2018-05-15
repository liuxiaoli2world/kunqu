package com.lgsc.kunqu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.service.DramaPartService;
import com.lgsc.kunqu.common.vo.Response;
import com.lgsc.kunqu.model.DramaPart;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 剧典片段
 * @author
 */
@Api(tags = "dramapart", description = "剧典片段")
@RestController
@RequestMapping("/api/kunqu/dramapart")
public class DramaPartController {

	@Autowired
	private DramaPartService dramaPartService;

	/**
	 * 查询剧典片段详情
	 */
	@ApiOperation(value = "查询剧典片段详情")
	@RequestMapping(value = "/queryOnePart", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectOnePart(@ApiParam(value = "剧典片段id", required = true) @RequestParam("dramaPartId") Long dramaPartId) {
		DramaPart dramaPart = dramaPartService.selectByPrimaryKey(dramaPartId);
		if (dramaPart != null) {
			return new Response().success(dramaPart);
		}
		return new Response().failure();
	}

	/**
	 * 根据剧典id查询剧典片段
	 */
	@ApiOperation(value = "根据剧典id查询剧典片段")
	@RequestMapping(value = "/queryParts", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectPartById(@ApiParam(value = "剧典id", required = true) @RequestParam("dramaId") Long dramaId) {
		List<DramaPart> dramaPartList = dramaPartService.selectPartById(dramaId);
		if (dramaPartList != null) {
			return new Response().success(dramaPartList);
		}
		return new Response().failure();
	}
	
	/**
	 * 添加剧典片段
	 */
	@ApiOperation(value = "添加剧典片段")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertDramaImage(@ApiParam(value = "剧典片段信息") @RequestBody @Validated DramaPart dramaPart) {
		Response response = new Response();
		int num = dramaPartService.insertSelective(dramaPart);
		if (num > 0) {
			response.success(num);
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 修改剧典片段
	 */
	@ApiOperation(value = "修改剧典片段")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateDramaImage(@ApiParam(value = "剧典片段信息") @RequestBody @Validated DramaPart dramaPart) {
		Response response = new Response();
		int num = dramaPartService.updateDramaImage(dramaPart);
		if (num > 0) {
			response.success(num);
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 根据dramaPartId（主键）删除剧典片段
	 */
	@ApiOperation(value = "根据dramaPartId删除相关剧典")
	@RequestMapping(value = "/remove/{dramaPartId}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response deleteByDramaAboutId(@ApiParam(value = "剧典片段id", required = true) @PathVariable("dramaPartId") Long dramaPartId) {
		int num = dramaPartService.deleteByPrimaryKey(dramaPartId);
		if (num > 0) {
			return new Response().success(num);
		}
		return new Response().failure();
	}
	
	/**
	 * 根据dramaId删除剧典片段
	 */
	@ApiOperation(value = "根据dramaId删除剧典片段")
	@RequestMapping(value = "/remove/{dramaId}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response deleteByDramaId(@ApiParam(value = "剧典id", required = true) @PathVariable("dramaId") Long dramaId) {
		int num = dramaPartService.deleteByDramaId(dramaId);
		if (num > 0) {
			return new Response().success(num);
		}
		return new Response().failure();
	}

}
