package com.ncvt.server;

import com.ncvt.dao.DrawDao;
import java.util.HashMap;

/**
 * 绘图数据查询
 * 
 * @author Administrator
 *
 */
public class DrawServer {

	DrawDao drawDao = new DrawDao();

	/**
	 * 查询各个城市景区数量
	 * 
	 * @param
	 * @return HashMap
	 */
	public HashMap<Integer, Integer> queryAttrCount() {
		// TODO Auto-generated method stub
		return drawDao.queryAttrCount();
	}

	/**
	 * 查询各个性别导游数量
	 * 
	 * @param
	 * @return HashMap
	 */
	public HashMap<Integer, Integer> queryGuideGender() {
		// TODO Auto-generated method stub
		return drawDao.queryGuideGender();
	}

	/**
	 * 查询各个等级导游数量
	 * 
	 * @param
	 * @return HashMap
	 */
	public HashMap<Integer, Integer> queryGuideLevel() {
		// TODO Auto-generated method stub
		return drawDao.queryGuideLevel();
	}

	/**
	 * 查询各个城市收入
	 * 
	 * @param
	 * @return HashMap
	 */
	public HashMap<Integer, Float> queryCityTotal() {
		// TODO Auto-generated method stub
		return drawDao.queryCityTotal();
	}

}
