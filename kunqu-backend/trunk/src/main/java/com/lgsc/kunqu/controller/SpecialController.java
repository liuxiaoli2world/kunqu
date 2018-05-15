package com.lgsc.kunqu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.common.vo.Response;
import com.lgsc.kunqu.model.Special;
import com.lgsc.kunqu.model.SpecialWeiChat;
import com.lgsc.kunqu.service.SpecialService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 专辑控制层
 * 
 * @author
 */
@Api(tags = "special", description = "曲典专辑")
@RestController
@RequestMapping("/api/kunqu/special")
public class SpecialController {

	@Autowired
	private SpecialService specialService;

	/**
	 * 
	 */
	@ApiOperation(value = "查询曲典专辑")
	@RequestMapping(value = "/query/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(@ApiParam(value = "", required = true) @PathVariable Long id) {
		return new Response().success(specialService.selectOneSpecailById(id));
	}

	/**
	 * 
	 */
	@ApiOperation(value = "查询所有区典专辑")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectAll(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		return new Response().success(specialService.selectAll(pageNum, pageSize));
	}

	/**
	 * 新增曲典专辑
	 */
	@ApiOperation(value = "新增曲典专辑")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertSelective(@ApiParam(value = "曲典专辑", required = true) @RequestBody @Validated Special record) {
		Response response = new Response();
		int num = specialService.insertSpecial(record);
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
	@ApiOperation(value = "修改曲典专辑")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateSelective(@ApiParam(value = "曲典专辑") @RequestBody @Validated Special record) {
		Response response = new Response();
		int num = specialService.updateByPrimaryKeySelective(record);
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
	@ApiOperation(value = "删除曲典专辑")
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(@ApiParam(value = "曲典专辑编号", required = true) @PathVariable Long id) {
		Response response = new Response();
		int num = specialService.deleteByPrimaryKey(id);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * 根据剧目标签id分页查找曲典专辑
	 */
	@ApiOperation(value = "根据剧目标签id分页查找曲典专辑")
	@RequestMapping(value = "/selectSpecialByRepertoireTagId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectSpecialByRepertoireTagId(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "剧目标签id", required = true) @RequestParam("repertoireTagId") long repertoireTagId) {
		PageInfo<Special> pageInfo = specialService.selectSpecialByRepertoireTagId(pageNum, pageSize, repertoireTagId);
		if (pageInfo != null) {
			return new Response().success(pageInfo);
		}
		return new Response().failure();
	}

	/**
	 * 根据年代标签id分页查找曲典专辑
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param ageTagId
	 * @return
	 */
	@ApiOperation(value = "根据年代标签id分页查找曲典专辑")
	@RequestMapping(value = "/selectSpecialByAgeTagId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectSpecialByAgeTagId(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "年代标签id", required = true) @RequestParam("ageTagId") long ageTagId) {
		PageInfo<Special> pageInfo = specialService.selectSpecialByAgeTagId(pageNum, pageSize, ageTagId);
		if (pageInfo != null) {
			return new Response().success(pageInfo);
		}
		return new Response().failure();
	}
	@ApiOperation(value = "根据作者标签id分页查找曲典专辑")
	@RequestMapping(value = "/selectSpecialByAuthorTagId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectSpecialByAuthorTagId(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "作者标签id", required = true) @RequestParam("authorTagId") long authorTagId){
		PageInfo<Special> pageInfo = specialService.selectSpecialByAuthorTagId(pageNum, pageSize, authorTagId);
		if(pageInfo!=null){
			return new Response().success(pageInfo);
		}else{
			return new Response().failure();
		}
	}
	@ApiOperation(value = "查询曲典推荐")
	@RequestMapping(value = "/selectSpecialIsSpecialRecommend", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectSpecialIsSubject(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
		PageInfo<Special> pageInfo = specialService.selectSpecialIsSubject(pageNum, pageSize);
		if(pageInfo!=null){
			return new Response().success(pageInfo);
		}else{
			return new Response().failure();
		}
		
	}
	@ApiOperation(value = "查询首页推荐")
	@RequestMapping(value = "/selectSpecialIsIndexRecommend", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectSpecialIsIndexRecommend(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
		PageInfo<Special> pageInfo = specialService.selectSpecialIsIndexRecommend(pageNum, pageSize);
		if(pageInfo!=null){
			return new Response().success(pageInfo);
		}else{
			return new Response().failure();
		}
		
	}
	@ApiOperation(value = "根据一组id查询所有曲典")
	@RequestMapping(value = "/selectSpecialBySpecialIds", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectSpecialBySpecialIds(
			@ApiParam(value = "曲典id数组", required = true) @RequestBody String ids){
		JSONObject JO = (JSONObject)JSONObject.parse(ids);
		ids = JO.get("ids").toString();
		String [] id = ids.split(",");
		List<Long> longId = new ArrayList<>();
		for(int i=0;i<id.length;i++){
			 longId.add(Long.valueOf(id[i]));  
		}
		List<Special> list = specialService.selectSpecialBySpecialIds(longId);
		if(list!=null){
			return new Response().success(list);
		}else{
			return new Response().failure();
		}
	}
	@ApiOperation(value = "根据曲典id查询上一条和下一条曲典")
	@RequestMapping(value = "/selectPreAndNextSpecial", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectPreAndNextSpecial(Long specialId){
		Response reponse = new Response();
		List<SpecialWeiChat> list = specialService.selectPreAndNextSpecial(specialId);
		return  reponse.success(list);
	}
	
	
	

}
