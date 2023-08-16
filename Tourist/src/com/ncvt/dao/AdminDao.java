package com.ncvt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.c3p0.utils.C3p0Utils;
import com.ncvt.entity.AdminEntity;

/**
 * 管理员表的数据库操作
 * 
 * @author Administrator
 *
 */
public class AdminDao {

	/**
	 * 新增管理员
	 * 
	 * @param AdminEntity
	 * @return int
	 */
	public int addAdmin(AdminEntity admin) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		int res = 0;
		try {
			String sql = "insert into tourist_admin(username, password) values(?,?)";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, admin.getUsername() + "");
			prepareStatement.setString(2, admin.getPassword());
			res = prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, null);
		}
		return res;
	}

	/**
	 * 删除管理员
	 * 
	 * @param int
	 * @return int
	 */
	public int delAdmin(int id) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		int res = 0;
		try {
			String sql = "delete from tourist.tourist_admin where id=?";
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
	 * 更新管理员
	 * 
	 * @param AdminEntity
	 * @return int
	 */
	public int updateAdmin(AdminEntity admin) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		int res = 0;
		try {
			String sql = "update tourist_admin set username=?,password=? where id=?";
			prepareStatement = connection.prepareStatement(sql);
			System.out.println(admin.getUsername());
			prepareStatement.setString(1, admin.getUsername());
			prepareStatement.setString(2, admin.getPassword());
			prepareStatement.setInt(3, admin.getId());
			res = prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, null);
		}
		return res;
	}

	/**
	 * 根据管理员ID查询管理员
	 * 
	 * @param int
	 * @return AdminEntity
	 */
	public AdminEntity queryAdminWithId(int id) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		AdminEntity adminRes = null;
		try {
			String sql = "select id,username,password from tourist_admin where id=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			res = prepareStatement.executeQuery();
			if (res.next()) {
				adminRes = new AdminEntity();
				adminRes.setId(res.getInt(1));
				adminRes.setUsername(res.getString(2));
				adminRes.setPassword(res.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, res);
		}
		return adminRes;
	}

	/**
	 * 根据姓名查询管理员信息
	 * 
	 * @param String
	 * @return AdminEntity
	 */
	public AdminEntity queryAdminWithName(String username) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		AdminEntity adminRes = null;
		try {
			String sql = "select id,username,password from tourist_admin where username=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, username);
			res = prepareStatement.executeQuery();
			if (res.next()) {
				adminRes = new AdminEntity();
				adminRes.setId(res.getInt(1));
				adminRes.setUsername(res.getString(2));
				adminRes.setPassword(res.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, res);
		}
		return adminRes;
	}

	/**
	 * 查询全部管理员
	 * 
	 * @param
	 * @return List<AdminEntity>
	 */
	public List<AdminEntity> queryAllAdmin() {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		AdminEntity adminRes = null;
		List<AdminEntity> rows = new ArrayList<AdminEntity>();
		try {
			String sql = "select id,username,password from tourist_admin";
			prepareStatement = connection.prepareStatement(sql);
			res = prepareStatement.executeQuery();
			while (res.next()) {
				adminRes = new AdminEntity();
				adminRes.setId(res.getInt("id"));
				adminRes.setUsername(res.getString("username"));
				adminRes.setPassword(res.getString("password"));
				rows.add(adminRes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, res);
		}
		return rows;
	}

}
