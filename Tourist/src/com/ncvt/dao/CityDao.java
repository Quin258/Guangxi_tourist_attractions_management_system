package com.ncvt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.c3p0.utils.C3p0Utils;
import com.ncvt.entity.CityEntity;

/**
 * 城市表的数据库操作
 * 
 * @author Administrator
 *
 */
public class CityDao {

	/**
	 * 更新城市
	 * 
	 * @param CityEntity
	 * @return int
	 */
	public int updateCity(CityEntity city) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		int res = 0;
		try {
			String sql = "update tourist_city set Total_revenue=? where id=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setFloat(1, city.getTotal_revenue());
			prepareStatement.setInt(2, city.getId());
			res = prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, null);
		}
		return res;
	}

	/**
	 * 根据城市ID查询城市
	 * 
	 * @param int
	 * @return CityEntity
	 */
	public CityEntity queryCityId(int id) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		CityEntity city = new CityEntity();
		try {
			String sql = "select id,name,Total_revenue from tourist_city where id=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			res = prepareStatement.executeQuery();
			if (res.next()) {
				city.setId(res.getInt(1));
				city.setName(res.getString(2));
				city.setTotal_revenue(res.getFloat(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, res);
		}
		return city;
	}

	/**
	 * 根据分页对象查询对应的页面数据
	 * 
	 * @param int
	 * @return List<CityEntiy>
	 */
	public List<CityEntity> queryCity(int order) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet resultSet = null;
		List<CityEntity> rows = new ArrayList<CityEntity>();
		String sql = null;
		try {
			if (order == 0) {
				sql = "select id,name,Total_revenue from tourist_city";
			} else if (order == 1) {
				sql = "select id,name,Total_revenue from tourist_city order by Total_revenue asc";
			} else if (order == 2) {
				sql = "select id,name,Total_revenue from tourist_city order by Total_revenue desc";
			}
			prepareStatement = connection.prepareStatement(sql);
			resultSet = prepareStatement.executeQuery();
			CityEntity city = new CityEntity();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				float Total_revenue = resultSet.getFloat("Total_revenue");
				city = new CityEntity();
				city.setId(id);
				city.setName(name);
				city.setTotal_revenue(Total_revenue);
				rows.add(city);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, resultSet);
		}
		return rows;
	}

}
