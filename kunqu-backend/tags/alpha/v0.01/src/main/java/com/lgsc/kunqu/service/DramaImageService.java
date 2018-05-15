package com.lgsc.kunqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.mapper.DramaImageMapper;
import com.lgsc.kunqu.mapper.DramaMapper;
import com.lgsc.kunqu.model.DramaImage;

/**
 * 
 */
@Service
public class DramaImageService {
	
	@Autowired
	private DramaImageMapper dramaImageMapper;
	
	@Autowired
	private DramaMapper dramaMapper;

	public int insert(DramaImage record) {
		return dramaImageMapper.insert(record);
	}

	/**
	 * 添加剧典封面
	 */
	public int insertSelective(DramaImage dramaImage) {
		if(dramaMapper.selectById(dramaImage.getDramaId())==null){
			return -1;
		}
		if(dramaImage.getImageScene()==null){
			dramaImage.setImageScene("01");
		}
		return dramaImageMapper.insertSelective(dramaImage);
	}

	public DramaImage selectByPrimaryKey(Long id) {
		return dramaImageMapper.selectByPrimaryKey(id);
	}
	
	public PageInfo<DramaImage> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DramaImage> list = dramaImageMapper.selectAll();
		PageInfo<DramaImage> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	/**
	 * 修改剧典封面
	 */
	public int updateByPrimaryKeySelective(DramaImage dramaImage) {
		if(dramaMapper.selectById(dramaImage.getDramaId())==null){
			return -1;
		}
		if(!"01".equals(dramaImage.getImageScene())||!"02".equals(dramaImage.getImageScene())){
			dramaImage.setImageScene("01");
		}
		return dramaImageMapper.updateByPrimaryKeySelective(dramaImage);
	}

	/**
	 * 删除剧典封面
	 */
	public int deleteByPrimaryKey(Long dramaImageId) {
		return dramaImageMapper.deleteByPrimaryKey(dramaImageId);
	}

	/**
	 * 根据DramaId查询剧典封面
	 */
	public List<DramaImage> selectByDramaId(long dramaId) {
		return dramaImageMapper.selectByDramaId(dramaId);
	}

}
