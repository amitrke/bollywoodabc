package com.babc.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babc.server.dao.CategoryDao;
import com.babc.server.dao.PictureDao;
import com.babc.server.model.CategoryEntity;
import com.babc.server.model.Paging;
import com.babc.server.model.PictureEntity;
import com.babc.server.model.vo.CategoryVo;
import com.babc.server.web.admin.model.KeyValuePair;

@Service("categoryService")
public class CategoryService {
	
	private CategoryDao categoryDao;
	private PictureDao pictureDao;
	
	@Autowired
	public CategoryService(CategoryDao categoryDao, PictureDao pictureDao) {
		this.categoryDao = categoryDao;
		this.pictureDao = pictureDao;
	}

	public CategoryVo get(Long id){
		CategoryEntity categoryEntity = categoryDao.getById(id);
		return entityToVo(categoryEntity);
	}
	
	public List<CategoryVo> get(Paging paging){
		List<CategoryEntity> categoryEntities = categoryDao.get(paging);
		return entityToVo(categoryEntities);
	}
	
	public List<CategoryEntity> getEntityList(Paging paging){
		return categoryDao.get(paging);
	}
	
	public List<CategoryVo> get(String type, Paging paging){
		List<CategoryEntity> categoryEntities = categoryDao.get(type, paging);
		return entityToVo(categoryEntities);
	}
	
	public List<KeyValuePair> getAsKeyVal(String type, Paging paging){
		List<CategoryEntity> categoryEntities = categoryDao.get(type, paging);
		List<KeyValuePair> keyValuePairs = new ArrayList<KeyValuePair>();
		for(CategoryEntity categoryEntity: categoryEntities){
			keyValuePairs.add(new KeyValuePair(categoryEntity.getId(), categoryEntity.getName()));
		}
		return keyValuePairs;
	}
	
	public CategoryVo add(CategoryVo categoryVo){
		PictureEntity pictureEntity = pictureDao.save(categoryVo.getPicture());
		categoryVo.setPicture(pictureEntity);
		CategoryEntity categoryEntity = categoryDao.save(new CategoryEntity(categoryVo));
		return entityToVo(categoryEntity);
	}
	
	public void delete(Long categoryId){
		CategoryEntity categoryEntity = categoryDao.getById(categoryId);
		pictureDao.remove(categoryEntity.getPicId());
		categoryDao.remove(categoryId);
	}
	
	private List<CategoryVo> entityToVo(List<CategoryEntity> categoryEntities){
		List<CategoryVo> categoryVos = new ArrayList<CategoryVo>();
		for(CategoryEntity categoryEntity: categoryEntities){
			categoryVos.add(entityToVo(categoryEntity));
		}
		return categoryVos;
	}
	
	private CategoryVo entityToVo(CategoryEntity categoryEntity){
		PictureEntity pictureEntity = pictureDao.getById(categoryEntity.getPicId());
		return new CategoryVo(categoryEntity, pictureEntity);
	}
}
