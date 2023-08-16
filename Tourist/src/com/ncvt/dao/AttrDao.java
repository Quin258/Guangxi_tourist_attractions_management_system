package com.ncvt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.c3p0.utils.C3p0Utils;
import com.ncvt.entity.AttrEntity;
import com.ncvt.utils.Page;

/**
 * 景区表的数据库操作
 * 
 * @author Administrator
 *
 */
public class AttrDao {

	/**
	 * 新增景区
	 * 
	 * @param AttrEntity
	 * @return int
	 */
	public int addAttr(AttrEntity attr) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		int res = 0;
		try {
			String sql = "insert into tourist_attractions(name,level,create_time,city_id) values(?,?,?,?)";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, attr.getName() + "");
			prepareStatement.setInt(2, attr.getLevel());
			prepareStatement.setString(3, attr.getCreate_time());
			prepareStatement.setInt(4, attr.getCity_id());
			res = prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, null);
		}
		return res;
	}

	/**
	 * 删除景区
	 * 
	 * @param int
	 * @return int
	 */
	public int delAttr(int id) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		int res = 0;
		try {
			String sql = "delete from tourist.tourist_attractions where id=?";
			prepareStatement = connection.prepareStatement(sql);
			System.out.println(id);
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
	 * 更新景区
	 * 
	 * @param AttrEntity
	 * @return int
	 */
	public int updateAttr(AttrEntity attr) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		int res = 0;
		try {
			String sql = "update tourist_attractions set name=?,level=?,create_time=?,city_id=? where id=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, attr.getName());
			prepareStatement.setInt(2, attr.getLevel());
			prepareStatement.setString(3, attr.getCreate_time());
			prepareStatement.setInt(4, attr.getCity_id());
			prepareStatement.setInt(5, attr.getId());
			res = prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, null);
		}
		return res;
	}

	/**
	 * 根据景区ID查询景区
	 * 
	 * @param int
	 * @return AttrEntity
	 */
	public AttrEntity queryAttr(int id) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		AttrEntity attr = new AttrEntity();
		try {
			String sql = "select id,name,level,create_time,city_id from tourist_attractions where id=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			res = prepareStatement.executeQuery();
			if (res.next()) {
				attr.setId(res.getInt(1));
				attr.setName(res.getString(2));
				attr.setLevel(res.getInt(3));
				attr.setCreate_time(res.getString(4));
				attr.setCity_id(res.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, res);
		}
		return attr;
	}

	/**
	 * 根据城市ID查询景区
	 * 
	 * @param String
	 * @return AttrEntity
	 */
	public AttrEntity queryAttrCity(String city) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		AttrEntity attr = new AttrEntity();
		try {
			String sql = "select id,name,level,create_time,city_id from tourist_attractions where city='?' ";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, city);
			res = prepareStatement.executeQuery();
			if (res.next()) {
				attr.setId(res.getInt(1));
				attr.setName(res.getString(2));
				attr.setLevel(res.getInt(3));
				attr.setCreate_time(res.getString(4));
				attr.setCity_id(res.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, res);
		}
		return attr;
	}

	/**
	 * 查询景区总行数
	 * 
	 * @param String, int
	 * @return int
	 */
	public int queryAttrCount(String find_str, int order) {
		String sql = null;
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		int totalRow = 0;
		try {
			if ("default".equals(find_str) && order == 0) {
				sql = "select count(1) from tourist_attractions";
				prepareStatement = connection.prepareStatement(sql);
			} else if ("default".equals(find_str) && order != 0) {
				sql = "select count(1) from tourist_attractions where city_id=?";
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setInt(1, order);
			} else if (!"default".equals(find_str) && order == 0) {
				sql = "select count(1) from tourist_attractions where name like \"%\"?\"%\"";
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setString(1, find_str);
			} else if (!"default".equals(find_str) && order != 0) {
				sql = "select count(1) from tourist_attractions where name like \"%\"?\"%\" and city_id=?";
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setString(1, find_str);
				prepareStatement.setInt(2, order);
			}
			res = prepareStatement.executeQuery();
			if (res.next()) {
				totalRow = res.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, res);
		}
		return totalRow;
	}

	/**
	 * 根据分页对象查询对应的页面数据
	 * 
	 * @param Page, String, int
	 * @return Page
	 */
	public Page queryAttrList(Page page, String find_str, int order) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet resultSet = null;
		String sql = null;
		List<AttrEntity> rows = new ArrayList<AttrEntity>();
		try {
			if ("default".equals(find_str) && order == 0) {
				sql = "select id,name,level,create_time,city_id from tourist_attractions limit ?,?";
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setInt(1, page.getLimitIndex());
				prepareStatement.setInt(2, page.getPageSize());
			} else if ("default".equals(find_str) && order != 0) {
				sql = "select id,name,level,create_time,city_id from tourist_attractions where city_id=? limit ?,?";
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setInt(1, order);
				prepareStatement.setInt(2, page.getLimitIndex());
				prepareStatement.setInt(3, page.getPageSize());
			} else if (!"default".equals(find_str) && order == 0) {
				sql = "select id,name,level,create_time,city_id from tourist_attractions where name like \"%\"?\"%\" limit ?,?";
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setString(1, find_str);
				prepareStatement.setInt(2, page.getLimitIndex());
				prepareStatement.setInt(3, page.getPageSize());
			} else if (!"default".equals(find_str) && order != 0) {
				sql = "select id,name,level,create_time,city_id from tourist_attractions where name like \"%\"?\"%\" and city_id=? limit ?,?";
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setString(1, find_str);
				prepareStatement.setInt(2, order);
				prepareStatement.setInt(3, page.getLimitIndex());
				prepareStatement.setInt(4, page.getPageSize());
			}
			resultSet = prepareStatement.executeQuery();
			AttrEntity attr = new AttrEntity();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int level = resultSet.getInt("level");
				String create_time = resultSet.getString("create_time");
				int city_id = resultSet.getInt("city_id");
				attr = new AttrEntity();
				attr.setId(id);
				attr.setName(name);
				attr.setLevel(level);
				attr.setCreate_time(create_time);
				attr.setCity_id(city_id);
				rows.add(attr);
			}
			page.setRows(rows);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, resultSet);
		}
		return page;
	}

	/**
	 * 查询全部景区
	 * 
	 * @param
	 * @return AttrEntity
	 */
	public List<AttrEntity> queryAttr() {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		AttrEntity attrRes = null;
		List<AttrEntity> rows = new ArrayList<AttrEntity>();
		try {
			String sql = "select id,name,level,create_time,city_id from tourist_attractions";
			prepareStatement = connection.prepareStatement(sql);
			res = prepareStatement.executeQuery();
			while (res.next()) {
				attrRes = new AttrEntity();
				attrRes.setId(res.getInt(1));
				attrRes.setName(res.getString(2));
				attrRes.setLevel(res.getInt(3));
				attrRes.setCreate_time(res.getString(4));
				attrRes.setCity_id(res.getInt(5));
				rows.add(attrRes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, res);
		}
		return rows;
	}

}
