package com.ncvt.server;

import java.util.List;

import com.ncvt.dao.GuideDao;
import com.ncvt.entity.GuideEntity;
import com.ncvt.utils.Page;

/**
 * 用户业务逻辑层
 * @author Administrator
 *
 */
public class GuideServer {
	
	GuideDao guideDao = new GuideDao();

	/**
	 * 插入导游信息
	 * @param GuideEntity 
	 * @return int
	 */
	public int addGuide(GuideEntity guide) {
		return guideDao.addGuide(guide);
	}

	/**
	 * 删除导游
	 * @param int
	 * @return int
	 */
	public int delGuide(int id) {
		// TODO Auto-generated method stub
		return guideDao.delGuide(id);
	}
	
	/**
	 * 根据导游ID查询导游
	 * @param int
	 * @return GuideEntity
	 */
	public GuideEntity queryGuide(int id) {
		// TODO Auto-generated method stub
		return guideDao.queryGuide(id);
	}

	/**
	 * 更新导游
	 * @param GuideEntity
	 * @return int
	 */
	public int updateGuide(GuideEntity guide) {
		// TODO Auto-generated method stub
		return guideDao.updateGuide(guide);
	}
	
	/**
	 * 查询导游总行数
	 * 
	 * @param String, int
	 * @return int
	 */
	public int queryGuideCount(String find_str, int level) {
		// TODO Auto-generated method stub
		return guideDao.queryGuideCount(find_str,level);
	}

	/**
	 * 根据分页对象查询对应的页面数据
	 * 
	 * @param Page, String, int
	 * @return Page
	 */
	public Page queryGuideList(Page page,String find_str,int level) {
		// TODO Auto-generated method stub
		return guideDao.queryGuideList(page,find_str, level);
	}

	/**
	 * 查询全部导游
	 * 
	 * @param 
	 * @return List<GuideEntity>
	 */
	public List<GuideEntity> queryGuide() {
		// TODO Auto-generated method stub
		return guideDao.queryGuide();
	}

	
	
}
