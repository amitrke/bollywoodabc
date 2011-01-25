package com.babc.server.model;
import com.babc.server.model.PictureEntity;
public class Test1 {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Class clazz = PictureEntity.class;
		PictureEntity model = (PictureEntity)clazz.newInstance();
		System.out.println("hello");
	}

}
