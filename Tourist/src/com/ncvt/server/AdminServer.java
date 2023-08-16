package com.ncvt.server;

import java.util.List;

import com.ncvt.dao.AdminDao;
import com.ncvt.entity.AdminEntity;

/**
 * 管理员业务逻辑层
 * 
 * @author Administrator
 *
 */
public class AdminServer {

	AdminDao adminDao = new AdminDao();

	/**
	 * 添加管理员
	 * @param AdminEntity
	 * @return int
	 */
	public int addAdmin(AdminEntity admin) {
		return adminDao.addAdmin(admin);
	}
	
	/**
	 * 删除管理员
	 * @param int
	 * @return int
	 */
	public int delAdmin(int id) {
		// TODO Auto-generated method stub
		return adminDao.delAdmin(id);
	}

	/**
	 * 更新管理员
	 * 
	 * @param AdminEntity
	 * @return int 
	 */
	public int updateAdmin(AdminEntity admin) {
		// TODO Auto-generated method stub
		return adminDao.updateAdmin(admin);
	}
	
	/**
	 * 根据管理员ID查询管理员信息
	 * @param int
	 * @return AdminEntiy
	 */
	public AdminEntity queryAdminWithId(int id) {
		// TODO Auto-generated method stub
		return adminDao.queryAdminWithId(id);
	}
	
	/**
	 * 根据管理员名称查询管理员
	 * @param String
	 * @return AdminEntiy
	 */
	public AdminEntity queryAdminWithName(String username) {
		// TODO Auto-generated method stub
		return adminDao.queryAdminWithName(username);
	}

	/**
	 * 查询全部管理员信息
	 * @param 
	 * @return List<AdminEntity>
	 */
	public List<AdminEntity> queryAllAdmin() {
		// TODO Auto-generated method stub
		return adminDao.queryAllAdmin();
	}
}
