package com.lgsc.kunqu.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.pagehelper.PageInfo;
import com.lgsc.kunqu.KunquApplication;
import com.lgsc.kunqu.mapper.ArticleMapper;
import com.lgsc.kunqu.mapper.DramaMapper;
import com.lgsc.kunqu.mapper.SpecialImageMapper;
import com.lgsc.kunqu.mapper.SpecialMapper;
import com.lgsc.kunqu.model.Article;
import com.lgsc.kunqu.model.ArticleImage;
import com.lgsc.kunqu.model.Drama;
import com.lgsc.kunqu.model.Special;
import com.lgsc.kunqu.model.SpecialImage;
import com.lgsc.kunqu.service.SpecialService;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = KunquApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class KunquTest {
	@Autowired
	private SpecialMapper specialMapper;
	
	@Autowired
	private  DramaMapper dramaMapper;
	
	@Autowired
	private SpecialService specialService;
	@Autowired
	private SpecialImageMapper specialImageMapper;
	@Test
	public void testSelectSpecial(){
		List<Special> list=specialMapper.selectSpecialByAuthorTagId(1l);
		for (Special special : list) {
			System.out.println(special.getOperaList().size());
		}
	}
	
	@Test
	public void testSelectDrama(){
		List<Drama> list = dramaMapper.selectDramaByAgeTagId(1l);
		for (Drama drama : list) {
			System.out.println(drama.getDramaImages().size());
		}
	}
	@Test
	public void testSelectAll(){
		PageInfo<Special> info  =specialService.selectAll(1, 4);
		List<Special> list = info.getList();
		for (Special special : list) {
			System.out.println(special);
		}
		
	}
	@Test
	public void testSelect(){
		List<SpecialImage> list=specialImageMapper.selectSpecialImageById(100l);
		for (SpecialImage specialImage : list) {
			System.out.println(specialImage.getImageUrl());
		}
	}
	@Test
	public void testInsertImage(){
		SpecialImage specialImage =new SpecialImage();
		specialImage.setImageUrl("测试一下插入");
		//specialImageMapper.insert(specialImage);
		specialImageMapper.deleteByPrimaryKey(103l);
	}
	
}
