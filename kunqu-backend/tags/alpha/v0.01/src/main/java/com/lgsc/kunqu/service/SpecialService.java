package com.lgsc.kunqu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.OperaMapper;
import com.lgsc.kunqu.mapper.RoleTagSpecialMapper;
import com.lgsc.kunqu.mapper.SpecialImageMapper;
import com.lgsc.kunqu.mapper.SpecialMapper;
import com.lgsc.kunqu.mapper.SpecialOperaMapper;
import com.lgsc.kunqu.model.Opera;
import com.lgsc.kunqu.model.RoleTagSpecial;
import com.lgsc.kunqu.model.Special;
import com.lgsc.kunqu.model.SpecialImage;
import com.lgsc.kunqu.model.SpecialOpera;
import com.lgsc.kunqu.model.SpecialWeiChat;



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
	
	@Autowired
	private RoleTagSpecialMapper roleTagSpecialMapper;
	
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
	
	/**
	 * 添加曲典专辑
	 */
	@Transactional
	public int insertSpecial(Special special) {
		if (StringUtils.isBlank(special.getSource())) {
			special.setSource("时代昆曲");
		}
		Date now = new Date();
		special.setSpecialId(null);
		special.setCreatedAt(now);
		// 插入到曲典专辑表
		specialMapper.insertSelective(special);
		
		// 插入到曲典专辑图片表
		if (special.getImageList() != null && special.getImageList().size() > 0) {
			for (SpecialImage specialImage : special.getImageList()) {
				specialImage.setSpecialImageId(null);
				specialImage.setSpecialId(special.getSpecialId());
				specialImage.setCreatedAt(now);
				specialImageMapper.insertSelective(specialImage);
			}
		}
		
		// 插入曲典
		if (special.getOperaList() != null && special.getOperaList().size() > 0) {
			for (Opera opera : special.getOperaList()) {
				if (StringUtils.isBlank(opera.getOperaName())) {
					opera.setOperaName("时代昆曲");
				}
				opera.setOperaId(null);
				opera.setCreatedAt(now);
				// 插入到曲典表
				operaMapper.insertSelective(opera);
				
				SpecialOpera specialOpera = new SpecialOpera();
				specialOpera.setSpecialId(special.getSpecialId());
				specialOpera.setOperaId(opera.getOperaId());
				specialOpera.setCreatedAt(now);
				// 插入到曲典专辑关系表
				specialOperaMapper.insertSelective(specialOpera);
			}
		}
		
		// 插入到角色专辑关系表
		RoleTagSpecial roleTagSpecial = new RoleTagSpecial();
		roleTagSpecial.setRoleTagId(special.getRoleTagId());
		roleTagSpecial.setSpecialId(special.getSpecialId());
		roleTagSpecial.setCreatedAt(now);
		roleTagSpecialMapper.insertSelective(roleTagSpecial);
		
		return 1;
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
		List<Special> list = specialMapper.selectSpecialByAuthorTagId(authorTagId);
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

	public List<Special> selectSpecialBySpecialIds(List<Long> ids) {
	    List<Special> list = specialMapper.selectSpecialBySpecialIds(ids);
		return list;
	}
    /**
     * 通过id查询曲典列表
     * @param specialId
     * @return
     */
	public List<SpecialWeiChat> selectPreAndNextSpecial(Long specialId){
		List<SpecialWeiChat> list = new ArrayList<>();
	    List<SpecialWeiChat> specials = specialMapper.selectSpecialForWeichat();
	    boolean isIn=false;
	    int index = -1;
	    for (SpecialWeiChat special : specials) {
			if(special.getSpecialId().equals(specialId)){
				index = specials.indexOf(special);
				isIn = true;
				break;
			}
		}
	    if(isIn){
	    	if(index==0){
	    		list.add(specials.get(specials.size()-1));
	    	}else{
	    		list.add(specials.get(index-1));
	    	}
	    	if(index==specials.size()-1){
	    		list.add(specials.get(0));
	    	}else{
	    		list.add(specials.get(index+1));
	    	}
	    }else{
	    	list.add(null);
	    	list.add(null);
	    }
	    return list;
		
	}
}
