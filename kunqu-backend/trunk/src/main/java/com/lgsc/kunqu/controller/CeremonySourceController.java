package com.lgsc.kunqu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.model.CeremonySource;
import com.lgsc.kunqu.service.CeremonySourceService;
import com.lgsc.kunqu.common.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @author 
 */
@Api(tags = "ceremonysource", description = "专题资源")
@RestController
@RequestMapping("/api/kunqu/ceremonysource")
public class CeremonySourceController {
	
	@Autowired
	private CeremonySourceService ceremonySourceService;
    
    /**
	 * 根据id查询专题资源
	 */
	@ApiOperation(value = "根据id查询专题资源")
	@RequestMapping(value = "/query/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(@ApiParam(value = "专题资源id", required = true) @PathVariable Long id) {
		return new Response().success(ceremonySourceService.selectByPrimaryKey(id));
	}
	
	/**
	 * 查询所有专题资源
	 */
	@ApiOperation(value = "查询所有专题资源")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectAll(@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		return new Response().success(ceremonySourceService.selectAll(pageNum, pageSize));
	}
	
	/**
	 * 查询关联资源
	 */
	//@ApiIgnore
	@ApiOperation(value = "查询已关联的资源")
	@RequestMapping(value = "/queryBy", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectBy(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "专题id", required = true) @RequestParam(name = "id", required = true) Long id,
			@ApiParam(value = "资源类型，drama:剧典，special:曲典，article:文章", required = true) @RequestParam(name = "sourceType", required = true) String sourceType ) {
		return new Response().success(ceremonySourceService.selectBy(pageNum, pageSize, id,sourceType));
	}
	
	
	/**
	 * 查询关资源id
	 */
	@ApiOperation(value = "查询已关联的资源id")
	@RequestMapping(value = "/querySourceIds", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectSourceIds(
			@ApiParam(value = "专题id", required = true) @RequestParam(name = "id", required = true) Long id,
			@ApiParam(value = "资源类型，drama:剧典，special:曲典，article:文章", required = true) @RequestParam(name = "sourceType", required = true) String sourceType ) {
		return new Response().success(ceremonySourceService.selectSourceIds(id,sourceType));
	}
	
	
	/**
	 * 查询资源(包含关联资源)
	 */
	@ApiOperation(value = "查询资源(包含已关联资源)")
	@RequestMapping(value = "/queryBySourceType", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectBySourceType(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "专题id", required = true) @RequestParam(name = "id", required = true) Long id,
			@ApiParam(value = "资源类型，drama:剧典，special:曲典，article:文章", required = true) @RequestParam(name = "sourceType", required = true) String sourceType ) {
		Map<String, Object> param = new HashMap<>();
		param.put("sourceType", sourceType);
		return new Response().success(ceremonySourceService.selectBySourceType(param , pageNum, pageSize, id));
	}
	
	
	/**
	 * 新增专题资源
	 */
	@ApiOperation(value = "新增专题资源")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertSelective(
			@ApiParam(value = "专题id", required = true) @RequestParam(name = "id", required = true) Long id,
			@ApiParam(value = "资源类型，drama:剧典，special:曲典，article:文章", required = true) @RequestParam(name = "sourceType", required = true) String sourceType,
			@ApiParam(value = "资源id列表", required = false) @RequestBody List<Long> sourceIds) {
		Response response = new Response();
		int num = ceremonySourceService.insertSelective(id,sourceType,sourceIds);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 更新单个专题资源
	 */
	@ApiOperation(value = "更新单个专题资源")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateSelective(@ApiParam(value = "专题资源", required = true) @RequestBody @Validated CeremonySource record) {
		Response response = new Response();
		int num = ceremonySourceService.updateByPrimaryKeySelective(record);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 删除单个主题资源
	 */
	@ApiOperation(value = "删除单个专题资源")
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(@ApiParam(value = "专题资源id", required = true) @PathVariable Long id) {
		Response response = new Response();
		int num = ceremonySourceService.deleteByPrimaryKey(id);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 查看资源是否关联
	 * @param sourceType 
	 * @param sourceId 
	 */
	@ApiIgnore
	@ApiOperation(value = "查看资源是否关联了专题")
	@RequestMapping(value = "/isRelative", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response isRelative(
			@ApiParam(value = "专题id", required = true) @RequestParam(name = "id", required = true) Long id,
			@ApiParam(value = "资源类型，drama:剧典，special:曲典，article:文章", required = true) @RequestParam(name = "sourceType", required = true) String sourceType,
			@ApiParam(value = "资源id", required = true) @RequestParam(name = "sourceId", required = true) Long sourceId) {
		return new Response().success(ceremonySourceService.isExist(id, sourceType, sourceId));
	}
	

}
