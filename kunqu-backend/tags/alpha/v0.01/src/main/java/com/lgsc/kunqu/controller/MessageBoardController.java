package com.lgsc.kunqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgsc.kunqu.model.MessageBoard;
import com.lgsc.kunqu.service.MessageBoardService;
import com.lgsc.kunqu.common.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 留言
 * @author 
 */
@Api(tags = "messageboard", description = "留言")
@RestController
@RequestMapping("/api/kunqu/messageboard")
public class MessageBoardController {
	
	@Autowired
	private MessageBoardService messageBoardService;
    
    /**
	 * 
	 */
	@ApiIgnore
	@ApiOperation(value = "")
	@RequestMapping(value = "/query/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(@ApiParam(value = "", required = true) @PathVariable Long id) {
		return new Response().success(messageBoardService.selectByPrimaryKey(id));
	}
	
	/**
	 * 根据条件查询留言
	 */
	@ApiOperation(value = "查询留言")
	@RequestMapping(value = "/queryBy", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json; charset=UTF-8")
	public Response selectByCondition(
			@ApiParam(value = "回复状态 0=未回复 1=已回复，不传查全部", required = false) @RequestParam(name = "status", required = false) Short status,
			@ApiParam(value = "关键词", required = false) @RequestParam(name = "keyword", required = false) String keyword,
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
	) {
		return new Response().success(messageBoardService.selectByCondition(status, keyword, pageNum, pageSize));
	}
	
	/**
	 * 添加留言
	 */
	@ApiOperation(value = "添加留言")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertSelective(@ApiParam(value = "留言对象", required = true) @RequestBody @Validated MessageBoard record) {
		Response response = new Response();
		int num = messageBoardService.insertSelective(record);
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
	@ApiIgnore
	@ApiOperation(value = "")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateSelective(@ApiParam(value = "", required = true) @RequestBody @Validated MessageBoard record) {
		Response response = new Response();
		int num = messageBoardService.updateByPrimaryKeySelective(record);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 回复留言
	 */
	@ApiOperation(value = "回复留言")
	@RequestMapping(value = "/replyMessage", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json; charset=UTF-8")
	public Response replyMessage(
			@ApiParam(value = "留言id", required = true) @RequestParam Long messageBoardId,
			@ApiParam(value = "回复内容", required = true) @RequestParam String reply
	) {
		Response response = new Response();
		MessageBoard record = new MessageBoard();
		record.setMessageBoardId(messageBoardId);
		record.setReply(reply);
		record.setStatus((short) 1);
		int num = messageBoardService.updateByPrimaryKeySelective(record);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}
	
	/**
	 * 删除留言
	 */
	@ApiOperation(value = "删除留言")
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(@ApiParam(value = "留言id", required = true) @PathVariable Long id) {
		Response response = new Response();
		int num = messageBoardService.deleteByPrimaryKey(id);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

}
