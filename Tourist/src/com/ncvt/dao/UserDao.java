package com.ncvt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.c3p0.utils.C3p0Utils;
import com.ncvt.entity.UserEntity;

/**
 * 用户表的数据库操作
 * 
 * @author Administrator
 *
 */
public class UserDao {

	/**
	 * 新增用户
	 * 
	 * @param UserEntity
	 * @return int
	 */
	public int addUser(UserEntity user) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		int res = 0;
		try {
			String sql = "insert into tourist_user(username, password) values(?,?)";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, user.getUsername() + "");
			prepareStatement.setString(2, user.getPassword());
			res = prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, null);
		}
		return res;
	}

	/**
	 * 删除用户
	 * 
	 * @param int
	 * @return int
	 */
	public int delUser(int id) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		int res = 0;
		try {
			String sql = "delete from tourist.tourist_user where id=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			res = prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, null);
		}
		return res;
	}

	/**
	 * 更新用户
	 * 
	 * @param UserEntity
	 * @return int
	 */
	public int updateUser(UserEntity user) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		int res = 0;
		try {
			String sql = "update tourist_user set username=?,password=? where id=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, user.getUsername());
			prepareStatement.setString(2, user.getPassword());
			prepareStatement.setInt(3, user.getId());
			res = prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, null);
		}
		return res;
	}

	/**
	 * 根据用户ID查询用户信息
	 * 
	 * @param int
	 * @return UserEntity
	 */
	public UserEntity queryUserWithId(int id) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		UserEntity userRes = null;
		try {
			String sql = "select id,username,password from tourist_user where id=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			res = prepareStatement.executeQuery();
			if (res.next()) {
				userRes = new UserEntity();
				userRes.setId(res.getInt(1));
				userRes.setUsername(res.getString(2));
				userRes.setPassword(res.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, res);
		}
		return userRes;
	}

	/**
	 * 根据姓名查询用户信息
	 * 
	 * @param String
	 * @return UserEntity
	 */
	public UserEntity queryUserWithName(String username) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		UserEntity userRes = null;
		try {
			String sql = "select id,username,password from tourist_user where username=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, username);
			res = prepareStatement.executeQuery();
			if (res.next()) {
				userRes = new UserEntity();
				userRes.setId(res.getInt(1));
				userRes.setUsername(res.getString(2));
				userRes.setPassword(res.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, res);
		}
		return userRes;
	}

	/**
	 * 查询全部用户信息
	 * 
	 * @param
	 * @return List<UserEntity>
	 */
	public List<UserEntity> queryAllUser() {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		UserEntity userRes = null;
		List<UserEntity> rows = new ArrayList<UserEntity>();
		try {
			String sql = "select id,username,password from tourist_user";
			prepareStatement = connection.prepareStatement(sql);
			res = prepareStatement.executeQuery();
			while (res.next()) {
				userRes = new UserEntity();
				userRes.setId(res.getInt("id"));
				userRes.setUsername(res.getString("username"));
				userRes.setPassword(res.getString("password"));
				rows.add(userRes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, res);
		}
		return rows;
	}

}
