package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lgsc.kunqu.mapper.ArticleMapper;
import com.lgsc.kunqu.mapper.AuthorTagMapper;
import com.lgsc.kunqu.mapper.DramaMapper;
import com.lgsc.kunqu.mapper.SpecialMapper;
import com.lgsc.kunqu.model.Article;
import com.lgsc.kunqu.model.AuthorTag;
import com.lgsc.kunqu.model.Drama;
import com.lgsc.kunqu.model.Special;

/**
 * 作者标签Service
 * @author luotianyu
 *
 */
@Service
public class AuthorTagService {
	
	@Autowired
	private AuthorTagMapper authorTagMapper;
	
	@Autowired
	private DramaMapper dramaMapper;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
    private SpecialMapper specailMapper;

	/**
	 * 插入一个作者标签
	 * @param record
	 * @return
	 */
	public int insertSelective(AuthorTag record) {
		return authorTagMapper.insertSelective(record);
	}
    /**
     * 根据id查询作者标签
     * @param id
     * @return
     */
	public AuthorTag selectByPrimaryKey(Long id) {
		return authorTagMapper.selectByPrimaryKey(id);
	}
	/**
	 * 返回全部作者
	 * @return
	 */
	public List<AuthorTag> selectAll() {
        List<AuthorTag> list = authorTagMapper.selectAll();	
		return list;
	}
	
	public int updateByPrimaryKeySelective(AuthorTag record) {
		return authorTagMapper.updateByPrimaryKeySelective(record);
	}
    /**
     * 删除作者标签，删除作者标签我们要更像相应的剧典，曲典，文章
     * @param id
     * @return
     */
	@Transactional(rollbackFor=Exception.class)
	public int deleteByPrimaryKey(Long id) {
		int result = authorTagMapper.deleteByPrimaryKey(id);
		if(result>0){
			List<Drama> dramas = dramaMapper.selectDramaByAuthorTagId(id);
			List<Article> articles = articleMapper.selectArticleByAuthorTagId(id);
			List<Special> specials = specailMapper.selectSpecialByAuthorTagId(id);
			if(dramas!=null&&dramas.size()!=0){
				for (Drama drama : dramas) {
					drama.setAuthorTagId(null);
					dramaMapper.updateByPrimaryKey(drama);
				}
			}
			if(articles!=null&&articles.size()!=0){
				for (Article article : articles) {
					article.setAuthorTagId(null);
					articleMapper.updateByPrimaryKey(article);
				}
			}
			if(specials!=null&&specials.size()!=0){
			    for (Special special : specials) {
					special.setAuthorTagId(null);
					specailMapper.updateByPrimaryKey(special);
				}
			}
		}
		return result;
	}

}
