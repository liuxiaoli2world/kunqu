package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.OperaMapper;
import com.lgsc.kunqu.mapper.SpecialImageMapper;
import com.lgsc.kunqu.mapper.SpecialMapper;
import com.lgsc.kunqu.mapper.SpecialOperaMapper;
import com.lgsc.kunqu.model.Opera;
import com.lgsc.kunqu.model.Special;
import com.lgsc.kunqu.model.SpecialImage;
import com.lgsc.kunqu.model.SpecialOpera;

import tk.mybatis.mapper.entity.Example;


/**
 * 
 */
@Service
public class SpecialService {

	@Autowired
	private SpecialMapper specialMapper;

	@Autowired
	private OperaMapper operaMapper;

	@Autowired
	private SpecialOperaMapper specialOperaMapper;
    
	@Autowired
	private SpecialImageMapper specialImageMapper;
	@Transactional(rollbackFor = Exception.class)
	public int insert(Special record) {
		int result = specialMapper.insert(record);
		long specialId = record.getSpecialId();
		List<Opera> operas = record.getOperaList();
		if (operas != null && operas.size() != 0) {
			for (Opera opera : operas) {
				SpecialOpera specaiOpera = new SpecialOpera();
				specaiOpera.setSpecialId(specialId);
				specaiOpera.setOperaId(opera.getOperaId());
				specialOperaMapper.insertSelective(specaiOpera);
				operaMapper.insert(opera);

			}
		}
		List<SpecialImage> specialImageList = record.getImageList();
		if(specialImageList.size() != 0&& specialImageList != null){
			for (SpecialImage images : specialImageList) {
				 images.setSpecialId(specialId);
				 specialImageMapper.insertSelective(images);
			}
		}

		return result;
	}

	public int insertSelective(Special record) {
		return specialMapper.insertSelective(record);
	}

	public Special selectOneSpecailById(long id) {
		return specialMapper.selectOneSpecial(id);
	}

	public PageInfo<Special> selectAll(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Special> list = specialMapper.selectAllSpecial();
		return new PageInfo<>(list);
	}

	public int updateByPrimaryKeySelective(Special record) {
		return specialMapper.updateByPrimaryKeySelective(record);
	}
    @Transactional(rollbackFor=Exception.class)
	public int deleteByPrimaryKey(Long id) {
		int result = specialMapper.deleteByPrimaryKey(id);
		List<SpecialImage> images = specialImageMapper.selectSpecialImageById(id);
		//Example example =new Example(SpecialOpera.class);
		//example.createCriteria().andEqualTo("special_id",id); 
		//specialOperaMapper.deleteByExample(example);
		for (SpecialImage specialImage : images) {
			specialImageMapper.deleteByPrimaryKey(specialImage.getSpecialImageId());
		}
		return result;
	}

	/**
	 * 根据剧目标签id分页查找曲典专辑
	 */
	public PageInfo<Special> selectSpecialByRepertoireTagId(int pageNum, int pageSize, long repertoireTagId) {
		PageHelper.startPage(pageNum, pageSize);
		List<Special> list = specialMapper.selectSpecialByRepertoireTagId(repertoireTagId);
		PageInfo<Special> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 根据年代标签id分页查找曲典专辑
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param ageTagId
	 * @return
	 */
	public PageInfo<Special> selectSpecialByAgeTagId(int pageNum, int pageSize, long ageTagId) {
		PageHelper.startPage(pageNum, pageSize);
		List<Special> list = specialMapper.selectSpecialByAgeTagId(ageTagId);
		PageInfo<Special> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 根据作者标签id分页查找曲典专辑
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param ageTagId
	 * @return
	 */
	public PageInfo<Special> selectSpecialByAuthorTagId(int pageNum, int pageSize, long authorTagId) {
		PageHelper.startPage(pageNum, pageSize);
		List<Special> list = specialMapper.selectSpecialByAgeTagId(authorTagId);
		PageInfo<Special> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	/**
	 * 查询曲典专辑
	 * @return
	 */
	public PageInfo<Special> selectSpecialIsSubject(int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		List<Special> list = specialMapper.selectSpecialIsSubject();
		return new PageInfo<>(list);
	}
	/**
	 * 查询前台推荐
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Special> selectSpecialIsIndexRecommend(int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		List<Special> list = specialMapper.selectSpecialIsIndexRecommend();
		return new PageInfo<>(list);
	}
}
