package com.ncvt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.c3p0.utils.C3p0Utils;
import com.ncvt.entity.draw.DrawAttrCountEntiy;
import com.ncvt.entity.draw.DrawCityTotalEntiy;
import com.ncvt.entity.draw.DrawGuideGenderEntiy;
import com.ncvt.entity.draw.DrawGuideLevelEntiy;

/**
 * 可视化数据查询
 * 
 * @author Administrator
 *
 */
public class DrawDao {

	/**
	 * 查询各个城市景区数量
	 * 
	 * @param
	 * @return HashMap<Integer, Integer>
	 */
	public HashMap<Integer,Integer> queryAttrCount() {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		List<DrawAttrCountEntiy> rows = new ArrayList<DrawAttrCountEntiy>();
		String sql = "SELECT city_id,COUNT(1) as count FROM tourist.tourist_attractions GROUP BY city_id";
		try {
			prepareStatement = connection.prepareStatement(sql);
			res = prepareStatement.executeQuery();
			while (res.next()) {
				DrawAttrCountEntiy drawAttrCount = new DrawAttrCountEntiy();
				int id = res.getInt("city_id");
				int count = res.getInt("count");
				drawAttrCount.setId(id);
				drawAttrCount.setCount(count);
				rows.add(drawAttrCount);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, null);
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < rows.size(); i = i + 1) {
			map.put(rows.get(i).getId(), rows.get(i).getCount());
		}
		return map;
	}

	/**
	 * 查询各个性别导游数量
	 * 
	 * @param
	 * @return HashMap<Integer, Integer>
	 */
	public HashMap<Integer,Integer> queryGuideGender() {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		List<DrawGuideGenderEntiy> rows = new ArrayList<DrawGuideGenderEntiy>();
		String sql = "SELECT gender,COUNT(1) AS count FROM tourist.tourist_guide GROUP BY gender";
		try {
			prepareStatement = connection.prepareStatement(sql);
			res = prepareStatement.executeQuery();
			while (res.next()) {
				DrawGuideGenderEntiy drawGuideGender = new DrawGuideGenderEntiy();
				int gender = res.getInt("gender");
				int count = res.getInt("count");
				drawGuideGender.setGender(gender);
				drawGuideGender.setCount(count);
				rows.add(drawGuideGender);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, null);
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < rows.size(); i = i + 1) {
			map.put(rows.get(i).getGender(), rows.get(i).getCount());
		}
		return map;
	}

	/**
	 * 查询各个等级导游数量
	 * 
	 * @param
	 * @return HashMap<Integer, Integer>
	 */
	public HashMap<Integer, Integer> queryGuideLevel() {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		List<DrawGuideLevelEntiy> rows = new ArrayList<DrawGuideLevelEntiy>();
		String sql = "SELECT level,COUNT(1) AS count FROM tourist.tourist_guide GROUP BY LEVEL ORDER BY level";
		try {
			prepareStatement = connection.prepareStatement(sql);
			res = prepareStatement.executeQuery();
			while (res.next()) {
				DrawGuideLevelEntiy drawGuideLevel = new DrawGuideLevelEntiy();
				int level = res.getInt("level");
				int count = res.getInt("count");
				drawGuideLevel.setLevel(level);
				drawGuideLevel.setCount(count);
				rows.add(drawGuideLevel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, null);
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < rows.size(); i = i + 1) {
			map.put(rows.get(i).getLevel(), rows.get(i).getCount());
		}
		return map;
	}

	/**
	 * 查询各个城市收入
	 * 
	 * @param
	 * @return HashMap<Integer, Float>
	 */
	public HashMap<Integer, Float> queryCityTotal() {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		List<DrawCityTotalEntiy> rows = new ArrayList<DrawCityTotalEntiy>();
		String sql = "SELECT id, total_revenue FROM tourist_city";
		try {
			prepareStatement = connection.prepareStatement(sql);
			res = prepareStatement.executeQuery();
			while (res.next()) {
				DrawCityTotalEntiy drawCityTotal = new DrawCityTotalEntiy();
				int id = res.getInt("id");
				float Total_revenue = res.getFloat("Total_revenue");
				drawCityTotal.setId(id);
				drawCityTotal.setTotal_revenue(Total_revenue);
				rows.add(drawCityTotal);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, null);
		}
		HashMap<Integer, Float> map = new HashMap<Integer, Float>();
		for (int i = 0; i < rows.size(); i = i + 1) {
			map.put(rows.get(i).getId(), rows.get(i).getTotal_revenue());
		}
		return map;
	}

}
