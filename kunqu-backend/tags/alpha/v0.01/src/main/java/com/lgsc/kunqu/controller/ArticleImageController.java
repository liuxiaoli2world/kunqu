package com.lgsc.kunqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.model.ArticleImage;
import com.lgsc.kunqu.service.ArticleImageService;
import com.lgsc.kunqu.common.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author 
 */
@Api(tags = "articleimage", description = "文章图片")
@RestController
@RequestMapping("/api/kunqu/articleimage")
public class ArticleImageController {
	
	@Autowired
	private ArticleImageService articleImageService;
    
    /**
     * 根据id查找图片
     * @param id
     * @return
     */
	@ApiOperation(value = "根据id查找图片")
	@RequestMapping(value = "/query/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(@ApiParam(value = "", required = true) @PathVariable Long id) {
		return new Response().success(articleImageService.selectByPrimaryKey(id));
	}
	
	/**
	 * 分页查询所有图片
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ApiOperation(value = "分页查询所有图片")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectAll(@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		return new Response().success(articleImageService.selectAll(pageNum, pageSize));
	}
	
	/**
	 * 新增图片
	 * @param record
	 * @return
	 */
	@ApiOperation(value = "新增图片")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertSelective(@ApiParam(value = "") @RequestBody @Validated ArticleImage record) {
		Response response = new Response();
		int num = articleImageService.insertSelective(record);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 修改图片
	 * @param record
	 * @return
	 */
	@ApiOperation(value = "修改图片")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateSelective(@ApiParam(value = "") @RequestBody @Validated ArticleImage record) {
		Response response = new Response();
		int num = articleImageService.updateByPrimaryKeySelective(record);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 根据id删除图片
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据id删除图片")
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(@ApiParam(value = "", required = true) @PathVariable Long id) {
		Response response = new Response();
		int num = articleImageService.deleteByPrimaryKey(id);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

}
