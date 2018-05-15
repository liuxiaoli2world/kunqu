package com.lgsc.kunqu.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.common.vo.Response;
import com.lgsc.kunqu.model.Drama;
import com.lgsc.kunqu.model.DramaAbout;
import com.lgsc.kunqu.service.DramaAboutService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 相关剧典
 * 
 * @author
 */
@Api(tags = "dramaabout", description = "相关剧典")
@Validated
@RestController
@RequestMapping("/api/kunqu/dramaabout")
public class DramaAboutController {
	
	private static Logger log = LogManager.getLogger(DramaAboutController.class);

	@Autowired
	private DramaAboutService dramaAboutService;

	/**
	 * 根据剧典id查询相关剧典
	 */
	@ApiOperation(value = "根据剧典id查询相关剧典")
	@RequestMapping(value = "/queryAbout", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectAboutById(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "剧典id", required = true) @RequestParam("dramaId") Long dramaId) {
		PageInfo<Drama> pageInfo = dramaAboutService.selectAboutById(pageNum, pageSize, dramaId);
		if (pageInfo != null) {
			return new Response().success(pageInfo);
		}
		return new Response().failure();
	}
	
	/**
	 * 根据dramaId查询剧典和是否为相关剧典
	 */
	@ApiOperation(value = "根据dramaId查询剧典和是否为相关剧典")
	@RequestMapping(value = "/queryDramaAndAbout", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectDramaAndAbout(
			@ApiParam(value = "当前页", required = true) @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "剧典id", required = true) @RequestParam long dramaId,
			@ApiParam(value = "剧典名称", required = false) @RequestParam(value = "dramaName", required = false) String dramaName) {
		PageInfo<Map<String, Object>> pageInfo = dramaAboutService.selectDramaAndAbout(pageNum, pageSize, dramaId, dramaName);
		if (pageInfo != null) {
			return new Response().success(pageInfo);
		}
		return new Response().failure();
	}

	/**
	 * 添加相关剧典
	 */
	@ApiIgnore
	@ApiOperation(value = "添加相关剧典")
	@RequestMapping(value = "/addAbout", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertAbout(@ApiParam(value = "剧典", required = true) @RequestBody DramaAbout dramaAbout) {
		int num = dramaAboutService.insertAbout(dramaAbout);
		if (num > 0) {
			return new Response().success(num);
		}
		return new Response().failure();
	}
	
	/**
	 * 批量添加相关剧典
	 */
	@ApiOperation(value = "批量添加相关剧典")
	@RequestMapping(value = "/batchAddAbout", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response batchInsertAbout(
			@ApiParam(value = "剧典id", required = true) @RequestParam long dramaId,
			@ApiParam(value = "相关剧典id数组，aboutId用','分隔，如：1,2,3", required = true) @RequestParam @NotBlank(message = "aboutIdStr不能为空") String aboutIdStr
	) {
		Response response = new Response();
		String[] aboutIdArray = aboutIdStr.split(",");
		long[] aboutIds = new long[aboutIdArray.length];
		try {
			for (int i = 0; i < aboutIdArray.length; i++) {
				aboutIds[i] = Long.parseLong(aboutIdArray[i]);
			}
		} catch (NumberFormatException e) {
			log.error(e);
			return response.failure("aboutIdStr格式错误");
		} catch (Exception e) {
			log.error(e);
			return response.failure(e.getMessage());
		}
		boolean result = dramaAboutService.batchInsertAbout(dramaId, aboutIds);
		if (result) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 修改相关剧典
	 */
	@ApiOperation(value = "修改相关剧典")
	@RequestMapping(value = "/updateAbout", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateAbout(@ApiParam(value = "剧典", required = true) @RequestBody DramaAbout dramaAbout) {
		int num = dramaAboutService.updateAbout(dramaAbout);
		if (num > 0) {
			return new Response().success(num);
		}
		return new Response().failure();
	}

	/**
	 * 根据dramaAboutId（主键）删除相关剧典
	 */
	@ApiOperation(value = "根据dramaAboutId删除相关剧典")
	@RequestMapping(value = "/remove/{dramaAboutId}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response deleteByDramaAboutId(
			@ApiParam(value = "剧典id", required = true) @PathVariable("dramaAboutId") Long dramaAboutId) {
		int num = dramaAboutService.deleteByPrimaryKey(dramaAboutId);
		if (num > 0) {
			return new Response().success(num);
		}
		return new Response().failure();
	}

	/**
	 * 根据剧典id删除相关剧典
	 */
	@ApiOperation(value = "根据dramaId删除相关剧典")
	@RequestMapping(value = "/remove/{dramaId}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response deleteByDramaId(@ApiParam(value = "剧典id", required = true) @PathVariable("dramaId") Long dramaId) {
		int num = dramaAboutService.deleteByDramaId(dramaId);
		if (num > 0) {
			return new Response().success(num);
		}
		return new Response().failure();
	}

	/**
	 * 根据剧典id，相关剧典id删除相关剧典
	 */
	@ApiOperation(value = "根据剧典id，相关剧典id删除相关剧典")
	@RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response deleteByAboutId(@ApiParam(value = "剧典id", required = true) @RequestParam("dramaId") Long dramaId,
			@ApiParam(value = "相关剧典id", required = true) @RequestParam("aboutId") Long aboutId) {
		int num = dramaAboutService.deleteByDramaIdAndAboutId(dramaId, aboutId);
		if (num > 0) {
			return new Response().success(num);
		}
		return new Response().failure();
	}

}
