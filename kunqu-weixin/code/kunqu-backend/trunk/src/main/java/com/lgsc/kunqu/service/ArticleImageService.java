package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.ArticleImageMapper;
import com.lgsc.kunqu.model.ArticleImage;

/**
 * 文章图片
 * 
 * @author pomay
 *
 */
@Service
public class ArticleImageService {

	@Autowired
	private ArticleImageMapper articleImageMapper;

	public int insert(ArticleImage record) {
		return articleImageMapper.insert(record);
	}

	/**
	 * 新增文章图片
	 * 
	 * @param record
	 * @return
	 */
	public int insertSelective(ArticleImage record) {

		List<ArticleImage> articleImages = articleImageMapper.selectAll();
		// 如果新增场景为封面图片
		if (record.getImageScene().equals("01")) {
			for (ArticleImage articleImage : articleImages) {
				// 但已经有封面图片，把原来的图片设成文章图片
				if (articleImage.getImageScene().equals("01"))
					articleImage.setImageScene("02");
				articleImageMapper.updateByPrimaryKey(articleImage);
			}
		}
		return articleImageMapper.insertSelective(record);
	}

	/**
	 * 根据id查找文章图片
	 * 
	 * @param articleImageId
	 * @return
	 */
	public ArticleImage selectByPrimaryKey(Long articleImageId) {
		return articleImageMapper.selectByPrimaryKey(articleImageId);
	}

	/**
	 * 分页查询所有文章图片
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<ArticleImage> selectAll(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ArticleImage> list = articleImageMapper.selectAll();
		PageInfo<ArticleImage> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	public int updateByPrimaryKey(ArticleImage record) {
		List<ArticleImage> articleImages = articleImageMapper.selectAll();
		// 如果更新场景为封面图片
		if (record.getImageScene().equals("01")) {
			for (ArticleImage articleImage : articleImages) {
				// 但已经有封面图片，把原来的图片设成文章图片
				if (articleImage.getImageScene().equals("01"))
					articleImage.setImageScene("02");
				articleImageMapper.updateByPrimaryKey(articleImage);
			}
		}

		return articleImageMapper.updateByPrimaryKey(record);
	}

	/**
	 * 修改文章图片
	 * 
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(ArticleImage record) {

		return articleImageMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据id删除文章图片
	 * 
	 * @param articleImageId
	 * @return
	 */
	public int deleteByPrimaryKey(Long articleImageId) {
		return articleImageMapper.deleteByPrimaryKey(articleImageId);
	}

}
