package com.babc.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babc.server.dao.PhotogalleryDao;
import com.babc.server.dao.PictureDao;
import com.babc.server.model.Paging;
import com.babc.server.model.Photogallery;
import com.babc.server.model.PictureEntity;

@Service("pictureService")
public class PictureService {
	
	private @Autowired PhotogalleryDao photogalleryDao;
	private @Autowired PictureDao pictureDao;
	
	public void addPicsToAlbum(List<Photogallery> pictures){
		for(Photogallery picture: pictures){
			photogalleryDao.save(picture);
		}
	}
	
	public List<List<PictureEntity>> getPhotogalleryGrid(Long id, int columns){
		List<List<PictureEntity>> grid = new ArrayList<List<PictureEntity>>();
		List<Photogallery> galleryPics = photogalleryDao.get(id, new Paging(Integer.MAX_VALUE,0));
		int counter=0;
		List<PictureEntity> row = new ArrayList<PictureEntity>();
		for(Photogallery pic: galleryPics){
			row.add(pictureDao.getById(pic.getPicId()));
			if(counter >= columns){
				grid.add(row);
				row = new ArrayList<PictureEntity>();
				counter = -1;
			}
			counter++;
		}
		if (row.size() > 0){
			grid.add(row);
		}
		return grid;
	}
	
	public List<Photogallery> getPhotogallery(Long category, Paging paging){
		return photogalleryDao.get(category, paging);
	}
	
	public PictureEntity getPicture(Long picId){
		return pictureDao.getById(picId);
	}
	
	public PictureEntity savePicture(PictureEntity pictureEntity){
		return pictureDao.save(pictureEntity);
	}
	
	public List<PictureEntity> getAllPictures(Paging paging){
		return pictureDao.getAll(paging);
	}
}
