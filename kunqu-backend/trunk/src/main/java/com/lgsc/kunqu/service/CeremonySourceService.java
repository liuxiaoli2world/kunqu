package com.lgsc.kunqu.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.ArticleMapper;
import com.lgsc.kunqu.mapper.CeremonySourceMapper;
import com.lgsc.kunqu.mapper.DramaMapper;
import com.lgsc.kunqu.mapper.SpecialMapper;
import com.lgsc.kunqu.model.Article;
import com.lgsc.kunqu.model.CeremonySource;
import com.lgsc.kunqu.model.Drama;
import com.lgsc.kunqu.model.Special;


/**
 * 
 */
@Service
public class CeremonySourceService {

	@Autowired
	private CeremonySourceMapper ceremonySourceMapper;
	
	@Autowired
	private DramaMapper dramaMapper;
	
	@Autowired
	private SpecialMapper specialMapper;
	
	@Autowired
	private ArticleMapper articleMapper;
	

	/**
	 * 新增专题资源
	 */
	@Transactional
	public int insertSelective(Long id, String sourceType, List<Long> sourceIds) {
		int mum = 0;
		if (sourceIds == null) {
			return mum;
		}
		//sourceIds 去重
		removeDuplicate(sourceIds);
		
		// 1.查询数据库中的专题资源id,数据库中的旧关系
		List<Long> sourceIds1 = selectSourceIds(id, sourceType);

		for (Long sourceId : sourceIds) {
			Date date = new Date();
			CeremonySource ceremonySource = new CeremonySource();
			ceremonySource.setCeremonyId(id);
			ceremonySource.setSourceType(sourceType);
			ceremonySource.setSourceId(sourceId);
			if (!sourceIds1.contains(sourceId)) { // 插入
				ceremonySource.setCreatedAt(date);
				ceremonySource.setUpdatedAt(date);
				mum = ceremonySourceMapper.insertSelective(ceremonySource);
			} else {
				ceremonySource = ceremonySourceMapper.selectOne(ceremonySource);
				ceremonySource.setCreatedAt(date);
				ceremonySource.setUpdatedAt(date);
				mum = ceremonySourceMapper.updateByPrimaryKey(ceremonySource);
			}
		}

		// 2.删除旧的关系
		if (sourceIds1.removeAll(sourceIds)) {
			for (Long sourceId : sourceIds1) {
				CeremonySource ceremonySource = new CeremonySource();
				ceremonySource.setCeremonyId(id);
				ceremonySource.setSourceType(sourceType);
				ceremonySource.setSourceId(sourceId);
				mum = ceremonySourceMapper.delete(ceremonySource);
			}
		}
		return mum;
	}
	
	/**
	 * 去重操作
	 * @param list
	 */
	private void  removeDuplicate(List<Long> list)   { 
	    HashSet<Long> h = new  HashSet<Long>(list); 
	    list.clear(); 
	    list.addAll(h); 
	} 

	private CeremonySource queryCeremonySource(Long id, String sourceType, Long sourceId) {
		CeremonySource ceremonySource = new CeremonySource();
		ceremonySource.setCeremonyId(id);
		ceremonySource.setSourceType(sourceType);
		ceremonySource.setSourceId(sourceId);
		return ceremonySourceMapper.selectOne(ceremonySource);
	}

	/**
	 * 查询是否关联
	 * 
	 * @param id
	 * @param sourceType
	 * @param sourceId
	 * @return
	 */
	public Boolean isExist(Long id, String sourceType, Long sourceId) {
		return queryCeremonySource(id, sourceType, sourceId) == null ? false : true;
	}

	public CeremonySource selectByPrimaryKey(long id) {
		return ceremonySourceMapper.selectByPrimaryKey(id);
	}

	public List<CeremonySource> selectAll() {
		return ceremonySourceMapper.selectAll();
	}

	public PageInfo<CeremonySource> selectAll(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<CeremonySource> list = ceremonySourceMapper.selectAll();
		PageInfo<CeremonySource> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	public int updateByPrimaryKeySelective(CeremonySource record) {
		return ceremonySourceMapper.updateByPrimaryKeySelective(record);
	}

	public int deleteByPrimaryKey(long id) {
		return ceremonySourceMapper.deleteByPrimaryKey(id);
	}

	public PageInfo<Map<String, Object>> selectBy(Integer pageNum, Integer pageSize, Long id, String sourceType) {
		return new PageInfo<Map<String, Object>>(ceremonySourceMapper.selectBy(id,sourceType));
	}
	
	public Object selectBySourceType(Map<String, Object> param, int pageNum, int pageSize,Long ceremonyId ){
		Object result = null;
		if ("drama".equals(param.get("sourceType"))) {
			PageHelper.startPage(pageNum, pageSize);
			List<Drama> list = dramaMapper.selectByTag(param);
			for (Drama drama : list) {
				if (isExist(ceremonyId, "drama", drama.getDramaId())) {
					drama.setIsRelative(true);
				}
			}
			PageInfo<Drama> pageInfo = new PageInfo<>(list);
			result = pageInfo;
		} else if ("special".equals(param.get("sourceType"))) {
			PageHelper.startPage(pageNum, pageSize);
			List<Special> list = specialMapper.selectByTag(param);
			for (Special special : list) {
				if (isExist(ceremonyId, "special", special.getSpecialId())) {
					special.setIsRelative(true);
				}
			}
			PageInfo<Special> pageInfo = new PageInfo<>(list);
			result = pageInfo;
		} else if ("article".equals(param.get("sourceType"))) {
			PageHelper.startPage(pageNum, pageSize);
			List<Article> list = articleMapper.selectByTag(param);
			for (Article article : list) {
				if (isExist(ceremonyId, "article",article.getArticleId())) {
					article.setIsRelative(true);
				}
			}
			PageInfo<Article> pageInfo = new PageInfo<>(list);
			result = pageInfo;
		}
		return result;
	}

	public List<Long> selectSourceIds(Long id, String sourceType) {
		return ceremonySourceMapper.selectSourceIds(id, sourceType);
	}
}
