package com.ncvt.entity;

/**
 * 数据库表：tourist_city
 * @author Administrator
 * CREATE TABLE `tourist_city` (
	`id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(4) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`Total_revenue` DECIMAL(10,2) NULL DEFAULT NULL,
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=15
;
 *
 */
public class CityEntity {
	private int id;
	private String name;
	private float Total_revenue;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getTotal_revenue() {
		return Total_revenue;
	}
	public void setTotal_revenue(float Total_revenue) {
		this.Total_revenue = Total_revenue;
	}
	@Override
	public String toString() {
		return "CityEntiy [id=" + id + ", name=" + name + ", Total_revenue=" + Total_revenue + "]";
	}
	
	
}
