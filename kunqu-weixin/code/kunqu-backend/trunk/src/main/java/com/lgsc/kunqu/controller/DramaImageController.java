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

import com.lgsc.kunqu.model.DramaImage;
import com.lgsc.kunqu.service.DramaImageService;
import com.lgsc.kunqu.common.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 剧典封面
 */
@Api(tags = "dramaimage", description = "剧典封面")
@RestController
@RequestMapping("/kunqu/dramaimage")
public class DramaImageController {

	@Autowired
	private DramaImageService dramaImageService;

	/**
	 * 查询剧典封面
	 */
	@ApiOperation(value = "查询剧典封面")
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectDramaImage(@ApiParam(value = "剧典id") @RequestParam("dramaId") long dramaId) {
		Response response = new Response();
		List<DramaImage> list = dramaImageService.selectByDramaId(dramaId);
		if (list != null && list.size() > 0) {
			response.success(list);
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * 添加剧典封面
	 */
	@ApiOperation(value = "添加剧典封面")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertDramaImage(@ApiParam(value = "剧典封面信息") @RequestBody @Validated DramaImage dramaImage) {
		Response response = new Response();
		int num = dramaImageService.insertSelective(dramaImage);
		if (num > 0) {
			response.success(num);
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * 修改剧典封面
	 */
	@ApiOperation(value = "添加剧典封面")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateDramaImage(@ApiParam(value = "剧典封面信息") @RequestBody @Validated DramaImage dramaImage) {
		Response response = new Response();
		int num = dramaImageService.updateByPrimaryKeySelective(dramaImage);
		if (num > 0) {
			response.success(num);
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * 删除剧典封面
	 */
	@ApiOperation(value = "删除剧典封面")
	@RequestMapping(value = "/remove/{dramaImageId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(
			@ApiParam(value = "剧典封面id", required = true) @PathVariable("dramaImageId") Long dramaImageId) {
		Response response = new Response();
		int num = dramaImageService.deleteByPrimaryKey(dramaImageId);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

}
