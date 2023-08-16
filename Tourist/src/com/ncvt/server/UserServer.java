package com.ncvt.server;

import java.util.List;

import com.ncvt.dao.UserDao;
import com.ncvt.entity.UserEntity;

/**
 * 用户业务逻辑层
 * 
 * @author Administrator
 *
 */
public class UserServer {

	UserDao userDao = new UserDao();

	/**
	 * 添加用户
	 * @param UserEntity
	 * @return int
	 */
	public int addUser(UserEntity user) {
		return userDao.addUser(user);
	}
	
	/**
	 * 删除用户
	 * @param int
	 * @return int
	 */
	public int delUser(int id) {
		// TODO Auto-generated method stub
		return userDao.delUser(id);
	}

	/**
	 * 更新用户
	 * 
	 * @param UserEntity
	 * @return int 
	 */
	public int updateUser(UserEntity user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}
	
	/**
	 * 根据用户ID查询用户
	 * @param int
	 * @return UserEntiy
	 */
	public UserEntity queryUserWithId(int id) {
		// TODO Auto-generated method stub
		return userDao.queryUserWithId(id);
	}
	
	/**
	 * 根据用户名称查询用户
	 * @param String
	 * @return UserEntiy
	 */
	public UserEntity queryUserWithName(String username) {
		// TODO Auto-generated method stub
		return userDao.queryUserWithName(username);
	}

	/**
	 * 查询全部用户
	 * @param 
	 * @return List<UserEntity>
	 */
	public List<UserEntity> queryAllUser() {
		// TODO Auto-generated method stub
		return userDao.queryAllUser();
	}
}
