package com.babc.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babc.server.dao.CommentDao;
import com.babc.server.dao.UserDao;
import com.babc.server.model.CommentEntity;
import com.babc.server.model.Paging;
import com.babc.server.model.UserEntity;
import com.babc.server.model.vo.CommentVo;

@Service("commentService")
public class CommentService {
	
	private UserDao userDao;
	private CommentDao commentDao;
	
	@Autowired	
	public CommentService(UserDao userDao, CommentDao commentDao) {
		this.userDao = userDao;
		this.commentDao = commentDao;
	}
/*
	public CommentVo get(Long id){
		CommentEntity commentEntity = commentDao.get(id);
		return entityToVo(commentEntity);
	}
	
	public List<CommentVo> get(Paging paging){
		List<CommentEntity> commentEntities = commentDao.get(paging);
		return entityToVo(commentEntities);
	}
	
	public List<CommentVo> get(Paging paging, Long storyId){
		List<CommentEntity> commentEntities = commentDao.get(paging, storyId);
		return entityToVo(commentEntities);
	}
	
	public CommentVo add(CommentVo commentVo){
		CommentEntity commentEntity = commentDao.add(new CommentEntity(commentVo));
		return entityToVo(commentEntity);
	}
	
	public void delete(Long id){
		commentDao.delete(id);
	}
	
	private List<CommentVo> entityToVo(List<CommentEntity> commentEntities){
		List<CommentVo> commentVos = new ArrayList<CommentVo>();
		for(CommentEntity commentEntity: commentEntities){
			commentVos.add(entityToVo(commentEntity));
		}
		return commentVos;
	}
	
	private CommentVo entityToVo(CommentEntity commentEntity){
		UserEntity userEntity = userDao.get(commentEntity.getAuthorId());
		return new CommentVo(commentEntity, userEntity);
	}
	*/
}
