package com.ncvt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.c3p0.utils.C3p0Utils;
import com.ncvt.entity.GuideEntity;
import com.ncvt.utils.Page;

/**
 * 导游表的数据库操作
 * 
 * @author Administrator
 *
 */
public class GuideDao {

	/**
	 * 新增导游
	 * 
	 * @param GuideEntity
	 * @return int
	 */
	public int addGuide(GuideEntity guide) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		int res = 0;
		try {
			String sql = "insert into tourist_guide(id, name, gender, guide_id, lang, level, institutions, phone) values(?,?,?,?,?,?,?,?)";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, guide.getId());
			prepareStatement.setString(2, guide.getName() + "");
			prepareStatement.setInt(3, guide.getGender());
			prepareStatement.setString(4, guide.getGuide_id());
			prepareStatement.setString(5, guide.getLang());
			prepareStatement.setInt(6, guide.getLevel());
			prepareStatement.setString(7, guide.getInstitutions());
			prepareStatement.setString(8, guide.getPhone());
			res = prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, null);
		}
		return res;
	}

	/**
	 * 删除导游
	 * 
	 * @param int
	 * @return int
	 */
	public int delGuide(int id) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		int res = 0;
		try {
			String sql = "delete from tourist_guide where id=?";
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
	 * 更新导游
	 * 
	 * @param GuideEntity
	 * @return int
	 */
	public int updateGuide(GuideEntity guide) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		int res = 0;
		try {
			String sql = "update tourist_guide set name=?, gender=?, guide_id=?, lang=?, level=?, institutions=?, phone=? where id=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, guide.getName() + "");
			prepareStatement.setInt(2, guide.getGender());
			prepareStatement.setString(3, guide.getGuide_id());
			prepareStatement.setString(4, guide.getLang());
			prepareStatement.setInt(5, guide.getLevel());
			prepareStatement.setString(6, guide.getInstitutions());
			prepareStatement.setString(7, guide.getPhone());
			prepareStatement.setInt(8, guide.getId());
			res = prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, null);
		}
		return res;
	}

	/**
	 * 根据导游ID查询导游
	 * 
	 * @param id
	 * @return GuideEntity
	 */
	public GuideEntity queryGuide(int id) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		GuideEntity guide = new GuideEntity();
		try {
			String sql = "select id, name, gender, guide_id, lang, level, institutions, phone from tourist_guide where id=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			res = prepareStatement.executeQuery();
			if (res.next()) {
				guide.setId(res.getInt(1));
				guide.setName(res.getString(2));
				guide.setGender(res.getInt(3));
				guide.setGuide_id(res.getString(4));
				guide.setLang(res.getString(5));
				guide.setLevel(res.getInt(6));
				guide.setInstitutions(res.getString(7));
				guide.setPhone(res.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, res);
		}
		return guide;
	}

	/**
	 * 查询导游总行数
	 * 
	 * @param String, int
	 * @return int
	 */
	public int queryGuideCount(String find_str, int level) {
		String sql = "select count(1) from tourist_guide";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		int totalRow = 0;
		try {
			if ("default".equals(find_str) && level == 0) {
				sql = "select count(1) from tourist_guide";
				prepareStatement = connection.prepareStatement(sql);
			} else if ("default".equals(find_str) && level != 0) {
				sql = "select count(1) from tourist_guide where level=?";
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setInt(1, level);
			} else if (!"default".equals(find_str) && level == 0) {
				sql = "select count(1) from tourist_guide where name like \"%\"?\"%\"";
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setString(1, find_str);
			} else if (!"default".equals(find_str) && level != 0) {
				sql = "select count(1) from tourist_guide where name like \"%\"?\"%\" and level=?";
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setString(1, find_str);
				prepareStatement.setInt(2, level);
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
	public Page queryGuideList(Page page, String find_str, int level_f) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet resultSet = null;
		List<GuideEntity> rows = new ArrayList<GuideEntity>();
		String sql = null;
		try {
			if ("default".equals(find_str) && level_f == 0) {
				sql = "select id, name, gender, guide_id, lang, level, institutions, phone from tourist_guide limit ?,?";
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setInt(1, page.getLimitIndex());
				prepareStatement.setInt(2, page.getPageSize());
			} else if ("default".equals(find_str) && level_f != 0) {
				sql = "select id, name, gender, guide_id, lang, level, institutions, phone from tourist_guide where level=? limit ?,?";
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setInt(1, level_f);
				prepareStatement.setInt(2, page.getLimitIndex());
				prepareStatement.setInt(3, page.getPageSize());
			} else if (!"default".equals(find_str) && level_f == 0) {
				sql = "select id, name, gender, guide_id, lang, level, institutions, phone from tourist_guide where name like \"%\"?\"%\" limit ?,?";
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setString(1, find_str);
				prepareStatement.setInt(2, page.getLimitIndex());
				prepareStatement.setInt(3, page.getPageSize());
			} else if (!"default".equals(find_str) && level_f != 0) {
				sql = "select id, name, gender, guide_id, lang, level, institutions, phone from tourist_guide where name like \"%\"?\"%\" and level=? limit ?,?";
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setString(1, find_str);
				prepareStatement.setInt(2, level_f);
				prepareStatement.setInt(3, page.getLimitIndex());
				prepareStatement.setInt(4, page.getPageSize());
			}
			resultSet = prepareStatement.executeQuery();
			GuideEntity guide = new GuideEntity();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int gender = resultSet.getInt("gender");
				String guide_id = resultSet.getString("guide_id");
				String lang = resultSet.getString("lang");
				int level = resultSet.getInt("level");
				String institutions = resultSet.getString("institutions");
				String phone = resultSet.getString("phone");
				guide = new GuideEntity();
				guide.setId(id);
				guide.setName(name);
				guide.setGender(gender);
				guide.setGuide_id(guide_id);
				guide.setLang(lang);
				guide.setLevel(level);
				guide.setInstitutions(institutions);
				guide.setPhone(phone);
				rows.add(guide);
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
	 * 查询全部导游
	 * 
	 * @param 
	 * @return List<GuideEntity>
	 */
	public List<GuideEntity> queryGuide() {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		connection = C3p0Utils.getConnection();
		ResultSet res = null;
		GuideEntity guideRes = null;
		List<GuideEntity> rows = new ArrayList<GuideEntity>();
		try {
			String sql = "select id, name, gender, guide_id, lang, level, institutions, phone from tourist_guide";
			prepareStatement = connection.prepareStatement(sql);
			res = prepareStatement.executeQuery();
			while (res.next()) {
				guideRes = new GuideEntity();
				guideRes.setId(res.getInt(1));
				guideRes.setName(res.getString(2));
				guideRes.setGender(res.getInt(3));
				guideRes.setGuide_id(res.getString(4));
				guideRes.setLang(res.getString(5));
				guideRes.setLevel(res.getInt(6));
				guideRes.setInstitutions(res.getString(7));
				guideRes.setPhone(res.getString(8));
				rows.add(guideRes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.release(connection, prepareStatement, res);
		}
		return rows;
	}

}
