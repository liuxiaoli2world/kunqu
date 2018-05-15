package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lgsc.kunqu.mapper.AgeTagMapper;
import com.lgsc.kunqu.mapper.ArticleMapper;
import com.lgsc.kunqu.mapper.DramaMapper;
import com.lgsc.kunqu.mapper.SpecialMapper;
import com.lgsc.kunqu.model.AgeTag;
import com.lgsc.kunqu.model.Article;
import com.lgsc.kunqu.model.Drama;
import com.lgsc.kunqu.model.Special;

/**
 * 年代标签
 * 
 * @author pomay
 *
 */
@Service
public class AgeTagService {

	@Autowired
	private AgeTagMapper ageTagMapper;

	@Autowired
	private ArticleMapper articleMapper;

	@Autowired
	private SpecialMapper specialMapper;

	@Autowired
	private DramaMapper dramaMapper;

	public int insert(AgeTag record) {
		return ageTagMapper.insert(record);
	}

	/**
	 * 添加一个年代标签
	 * 
	 * @param record
	 * @return
	 */
	public int insertSelective(AgeTag record) {
		return ageTagMapper.insertSelective(record);
	}

	/**
	 * 根据id查询年代标签
	 * 
	 * @param id
	 * @return
	 */
	public AgeTag selectByPrimaryKey(Long ageTagId) {
		return ageTagMapper.selectByPrimaryKey(ageTagId);
	}

	/**
	 * 分页查询所有年代标签
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<AgeTag> selectAll() {

		List<AgeTag> list = ageTagMapper.selectAll();

		return list;
	}

	public int updateByPrimaryKey(AgeTag record) {
		return ageTagMapper.updateByPrimaryKey(record);
	}

	/**
	 * 更新年代标签
	 * 
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(AgeTag record) {
		return ageTagMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据id删除年代标签并更新关联关系
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public int deleteByPrimaryKey(Long ageTagId) {

		List<Article> articles = articleMapper.selectArticleByAgeTagId(ageTagId);
		List<Drama> dramas = dramaMapper.selectDramaByAgeTagId(ageTagId);
		List<Special> specials = specialMapper.selectSpecialByAgeTagId(ageTagId);
		if (dramas != null && dramas.size() != 0) {
			for (Drama drama : dramas) {
				drama.setAgeTagId(null);
				dramaMapper.updateByPrimaryKey(drama);
			}
		}
		if (articles != null && articles.size() != 0) {
			for (Article article : articles) {
				article.setAgeTagId(null);
				articleMapper.updateByPrimaryKey(article);
			}
		}
		if (specials != null && specials.size() != 0) {
			for (Special special : specials) {
				special.setAgeTagId(null);
				specialMapper.updateByPrimaryKey(special);
			}
		}
		return ageTagMapper.deleteByPrimaryKey(ageTagId);
	}

}
