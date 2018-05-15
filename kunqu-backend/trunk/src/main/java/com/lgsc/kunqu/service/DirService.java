package com.lgsc.kunqu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.ArticleMapper;
import com.lgsc.kunqu.mapper.DirMapper;
import com.lgsc.kunqu.mapper.DramaMapper;
import com.lgsc.kunqu.mapper.OverviewMapper;
import com.lgsc.kunqu.mapper.SpecialMapper;
import com.lgsc.kunqu.model.Dir;
import com.lgsc.kunqu.model.Overview;

/**
 * 目录
 */
@Service
public class DirService {
	
	@Autowired
	private DirMapper dirMapper;
	
	@Autowired
	private OverviewMapper overviewMapper;
	
	@Autowired
	private DramaMapper dramaMapper;
	
	@Autowired
	private SpecialMapper specialMapper;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	public int insert(Dir record) {
		return dirMapper.insert(record);
	}

	public int insertSelective(Dir record) {
		return dirMapper.insertSelective(record);
	}

	public Dir selectByPrimaryKey(long id) {
		return dirMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 查询所有根目录
	 */
	public List<Dir> selectRoot() {
		Dir record = new Dir();
		record.setParentId(0L);
		return dirMapper.select(record);
	}
	
	/**
	 * 根据目录id查询祖先节点，不包括当前目录
	 * @param dirId 目录编号
	 * @return 祖先节点
	 */
	public List<Dir> selectParentList(long dirId) {
		return dirMapper.selectParentList(dirId);
	}
	
	/**
	 * 根据目录id查询后代节点，不包括当前目录
	 * @param dirId 目录编号
	 * @return 后代节点
	 */
	public List<Dir> selectChildList(long dirId) {
		return dirMapper.selectChildList(dirId);
	}
	
	/**
	 * 根据目录id查询子目录
	 */
	public List<Dir> selectChild(long dirId) {
		return dirMapper.selectChild(dirId);
	}
	
	/**
	 * 查询目录内容
	 */
	public Object selectContent(String type, long dirId, int pageNum, int pageSize) {
		Object result = null;
		if ("overview".equals(type)) {// 查询概述
			Overview record = new Overview();
			record.setDirId(dirId);
			result = overviewMapper.selectOne(record);
		} else if ("thesis".equals(type)) {// 查询典论
			PageHelper.startPage(pageNum, pageSize);
			List<Map<String, Object>> list = dirMapper.selectComposite(dirId);
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
			result = pageInfo;
		} else {
			
		}
		return result;
	}
	
	public List<Dir> selectAll() {
		return dirMapper.selectAll();
	}
	
	public int updateByPrimaryKey(Dir record) {
		return dirMapper.updateByPrimaryKey(record);
	}

	public int updateByPrimaryKeySelective(Dir record) {
		return dirMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 删除目录
	 */
	@Transactional
	public int deleteByPrimaryKey(long dirId) {
		// 设置剧典的目录指向为空
		dramaMapper.updateDirIdNull(dirId);
		
		// 设置曲典的目录指向为空
		specialMapper.updateDirIdNull(dirId);
		
		// 设置文章的目录指向为空
		articleMapper.updateDirIdNull(dirId);
		
		// 设置概述的目录指向为空
		overviewMapper.updateDirIdNull(dirId);
		
		// 删除目录
		return dirMapper.deleteByPrimaryKey(dirId);
	}
	
	/**
	 * 查询资源数量
	 * @return 统计结果
	 */
	public List<Map<String, Object>> selectSourceCount() {
		return dirMapper.selectSourceCount();
	}

}
