package com.ncvt.server;

import java.util.List;

import com.ncvt.dao.CityDao;
import com.ncvt.entity.CityEntity;

/**
 * 城市业务逻辑层
 * @author Administrator
 *
 */
public class CityServer {
	
	CityDao cityDao = new CityDao();

	/**
	 * 查询城市
	 * @param int
	 * @return List<CityEntity>
	 */
	public List<CityEntity> queryCity(int order) {
		// TODO Auto-generated method stub
		return cityDao.queryCity(order);
	}
	
	/**
	 * 根据城市ID查询城市
	 * 
	 * @param int
	 * @return CityEntity
	 */
	public CityEntity queryCityId(int id) {
		// TODO Auto-generated method stub
		return cityDao.queryCityId(id);
	}
	
	/**
	 * 更新城市
	 * @param CityEntity
	 * @return int
	 */
	public int updateCity(CityEntity city) {
		// TODO Auto-generated method stub
		return cityDao.updateCity(city);
	}
	
	
}
