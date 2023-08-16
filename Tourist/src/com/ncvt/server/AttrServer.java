package com.ncvt.server;

import java.util.List;

import com.ncvt.dao.AttrDao;
import com.ncvt.entity.AttrEntity;
import com.ncvt.utils.Page;

/**
 * 景区业务逻辑层
 * 
 * @author Administrator
 *
 */
public class AttrServer {

	AttrDao attrDao = new AttrDao();

	/**
	 * 添加景区
	 * @param AttrEntity
	 * @return int
	 */
	public int addAttr(AttrEntity attr) {
		return attrDao.addAttr(attr);
	}
	
	/**
	 * 删除景区
	 * @param int
	 * @return int
	 */
	public int delAttr(int id) {
		// TODO Auto-generated method stub
		return attrDao.delAttr(id);
	}
	
	/**
	 * 更新景区
	 * 
	 * @param AttrEntity
	 * @return int
	 */
	public int updateAttr(AttrEntity attr) {
		// TODO Auto-generated method stub
		return attrDao.updateAttr(attr);
	}

	/**
	 * 根据景区ID查询景区
	 * @param int
	 * @return AttrEntity
	 */
	public AttrEntity queryAttr(int id) {
		// TODO Auto-generated method stub
		return attrDao.queryAttr(id);
	}

	/**
	 * 查询景区总数
	 * @param String find_str, int order
	 * @return int
	 */
	public int queryAttrCount(String find_str, int order) {
		// TODO Auto-generated method stub
		return attrDao.queryAttrCount(find_str, order);
	}
	
	/**
	 * 查询景区(分页)
	 * @param Page, String, int
	 * @return Page
	 */
	public Page queryAttrList(Page page, String find_str, int order) {
		// TODO Auto-generated method stub
		return attrDao.queryAttrList(page, find_str, order);
	}
	
	/**
	 * 查询景区
	 * @param
	 * @return List<AttrEntity>
	 */
	public List<AttrEntity> queryAttr() {
		// TODO Auto-generated method stub
		return attrDao.queryAttr();
	}



}
