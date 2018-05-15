package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lgsc.kunqu.mapper.ArticleMapper;
import com.lgsc.kunqu.mapper.DramaMapper;
import com.lgsc.kunqu.mapper.RepertoireTagMapper;
import com.lgsc.kunqu.mapper.SpecialMapper;
import com.lgsc.kunqu.model.Article;
import com.lgsc.kunqu.model.Drama;
import com.lgsc.kunqu.model.RepertoireTag;
import com.lgsc.kunqu.model.Special;

/**
 * 
 */
@Service
public class RepertoireTagService {

	@Autowired
	private RepertoireTagMapper repertoireTagMapper;

	@Autowired
	private ArticleMapper articleMapper;

	@Autowired
	private SpecialMapper specialMapper;

	@Autowired
	private DramaMapper dramaMapper;

	/**
	 * 添加剧目标签
	 */
	public int insertSelective(RepertoireTag record) {
		return repertoireTagMapper.insertSelective(record);
	}

	/**
	 * 查询剧目标签
	 */
	public RepertoireTag selectByPrimaryKey(Long id) {
		return repertoireTagMapper.selectByPrimaryKey(id);
	}

	/**
	 * 查询所有标签
	 */
	public List<RepertoireTag> selectAll() {
		List<RepertoireTag> list = repertoireTagMapper.selectAll();
		return list;
	}

	/**
	 * 修改剧目标签
	 */
	public int updateByPrimaryKeySelective(RepertoireTag record) {
		return repertoireTagMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 删除剧目标签
	 */
	@Transactional(rollbackFor = Exception.class)
	public int deleteByPrimaryKey(Long id) {
		List<Drama> dramas = dramaMapper.selectDramaByRepertoireTagId(id);
		List<Article> articles = articleMapper.selectArticleByRepertoireTagId(id);
		List<Special> specials = specialMapper.selectSpecialByRepertoireTagId(id);
		if (dramas != null && dramas.size() != 0) {
			for (Drama drama : dramas) {
				drama.setRepertoireTagId(null);
				dramaMapper.updateByPrimaryKey(drama);
			}
		}
		if (articles != null && articles.size() != 0) {
			for (Article article : articles) {
				article.setRepertoireTagId(null);
				articleMapper.updateByPrimaryKey(article);
			}
		}
		if (specials != null && specials.size() != 0) {
			for (Special special : specials) {
				special.setRepertoireTagId(null);
				specialMapper.updateByPrimaryKey(special);
			}
		}
		return repertoireTagMapper.deleteByPrimaryKey(id);
	}
}
