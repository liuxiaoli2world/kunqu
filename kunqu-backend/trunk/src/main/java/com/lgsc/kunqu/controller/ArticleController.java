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
import com.lgsc.kunqu.model.Article;
import com.lgsc.kunqu.service.ArticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 文章
 * 
 * @author pomay
 *
 */
@Api(tags = "article", description = "文章")
@RestController
@RequestMapping("/api/kunqu/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	/**
	 * 根据id查询文章,返回结果附加文章图片
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据id查询文章")
	@RequestMapping(value = "/query/{articleId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response select(@ApiParam(value = "articleId", required = true) @PathVariable Long articleId) {
		List<Article> article = articleService.selectById(articleId);
		if (article != null) {
			return new Response().success(article);
		}
		return new Response().failure("查询失败");
	}
	/**
	 * 根据一组id查询文章，返回结果附加文章图片
	 * @param articleIds
	 * @return
	 */
	@ApiOperation(value = "根据一组id查询文章")
	@RequestMapping(value = "/selectByGroupId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectByGroupId(@ApiParam(value = "articleIds", required = true) @RequestBody String ids) {
		JSONObject JO = (JSONObject)JSONObject.parse(ids);
		ids = JO.get("ids").toString();
		String [] idss = ids.split(",");
		List<Long> longIds = new ArrayList<>();
		for(int i = 0;i<idss.length;i++){
			longIds.add(Long.valueOf(idss[i]));
		}
		List<Article> article = articleService.selectByGroupId(longIds);
		if (article != null) {
			return new Response().success(article);
		}
		return new Response().failure("查询失败");
	}

	/**
	 * 根据id查询前一篇和后一篇文章
	 * 
	 * @param articleId
	 * @return
	 */
	@ApiOperation(value = "根据id查询前一篇和后一篇文章")
	@RequestMapping(value = "/selectPreAndAfter/{articleId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response selectPreAndAfter(@ApiParam(value = "articleId", required = true) @PathVariable Long articleId) {
		List<Article> article = articleService.selectPreAfterArticle(articleId);
		if (article != null) {
			return new Response().success(article);
		}
		return new Response().failure("查询失败");
	}

	/**
	 * 根据年代标签id分页查找文章，返回结果附加文章图片
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param ageTagId
	 * @return
	 */
	@ApiOperation(value = "根据年代标签id分页查找文章")
	@RequestMapping(value = "/selectArticleByAgeTagId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectArticleByAgeTagId(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "年代标签id", required = true) @RequestParam("ageTagId") long ageTagId) {

		PageInfo<Article> pageInfo = articleService.selectArticleByAgeTagId(pageNum, pageSize, ageTagId);
		if (pageInfo != null) {
			return new Response().success(pageInfo);
		}
		return new Response().failure("查询失败");
	}

	/**
	 * 根据剧目标签id分页查找文章
	 */
	@ApiOperation(value = "根据剧目标签id分页查找文章")
	@RequestMapping(value = "/selectArticleByRepertoireTagId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectArticleByRepertoireTagId(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "剧目标签id", required = true) @RequestParam("repertoireTagId") long repertoireTagId) {

		PageInfo<Article> pageInfo = articleService.selectArticleByRepertoireTagId(pageNum, pageSize, repertoireTagId);
		if (pageInfo != null) {
			return new Response().success(pageInfo);
		}
		return new Response().failure("查询失败");
	}

	/**
	 * 根据作者标签id查找文章
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param authorTagId
	 * @return
	 */
	@ApiOperation(value = "根根据作者标签查找文章")
	@RequestMapping(value = "/selectArticleByAuthorTagId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectArticleByAuthorTagId(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "作者标签id", required = true) @RequestParam("authorTagId") long authorTagId) {
		PageInfo<Article> pageInfo = articleService.selectArticleByAuthorTagId(pageNum, pageSize, authorTagId);
		if (pageInfo != null) {
			return new Response().success(pageInfo);
		} else {
			return new Response().failure("查询失败");
		}

	}

	/**
	 * 根据类型分页查询，返回结果附加文章图片 查询类型：null查询所有文章 1首页推荐阅读 2 精品阅读 3阅读排行4 精品推荐
	 */
	@ApiOperation(value = "分页查询首页推荐阅读、精品阅读 、阅读排行、列表精品推荐")
	@RequestMapping(value = "/selectByStyle", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectByStyle(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "分页查询类型 null查询所有  1首页推荐阅读 2 精品阅读 3阅读排行4 精品推荐 ", required = false) @RequestParam(name = "type", required = false) Integer type) {
		PageInfo<Article> pageInfo = articleService.selectByStyle(pageNum, pageSize, type);
		if (pageInfo != null) {
			return new Response().success(pageInfo);
		}
		return new Response().failure("查询失败");
	}

	/**
	 * 分页查询阅读排行之周/月排行
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param type
	 *            分页查询 查询类型 null查询阅读排行模块 1周排行2 月排行
	 * @return
	 */
	@ApiOperation(value = "分页查询周排行/月排行")
	@RequestMapping(value = "/selectByDesc", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response selectByDesc(
			@ApiParam(value = "当前页", required = true) @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@ApiParam(value = "每页的数量", required = true) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "分页降序查询类型 : null 查询阅读排行模块 1周排行2 月排行 ", required = false) @RequestParam(name = "type", required = false) Integer type) {
		PageInfo<Article> pageInfo = articleService.selectByDesc(pageNum, pageSize, type);
		if (pageInfo != null) {
			return new Response().success(pageInfo);
		}
		return new Response().failure("查询失败");
	}

	/**
	 * 新增文章
	 */
	@ApiOperation(value = "新增文章")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response insertSelective(@ApiParam(value = "文章", required = true) @RequestBody @Validated Article record) {
		Response response = new Response();
		int num = articleService.insertArticle(record);
		if (num > 0) {
			response.success();
		} else {
			response.failure();
		}
		return response;
	}

	/**
	 * 修改文章内容
	 * 
	 * @param record
	 * @return
	 */
	@ApiOperation(value = "修改文章内容")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Response updateSelective(@ApiParam(value = "") @RequestBody @Validated Article record) {
		Response response = new Response();
		int num = articleService.updateByPrimaryKeySelective(record);
		if (num > 0) {
			response.success("文章内容修改成功");
		} else {
			response.failure("修改失败");
		}
		return response;
	}

	/**
	 * 根据id删除文章
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据id删除文章")
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response delete(@ApiParam(value = "", required = true) @PathVariable Long id) {
		Response response = new Response();
		int num = articleService.deleteByPrimaryKey(id);
		if (num > 0) {
			response.success("删除成功");
		} else {
			response.failure("删除失败");
		}
		return response;
	}

}
