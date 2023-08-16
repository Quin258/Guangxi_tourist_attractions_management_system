package com.ncvt.entity;

/**
 * 数据库表：tourist_attractions
 * @author Administrator
 * CREATE TABLE `tourist_attractions` (
	`id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`level` SMALLINT(5) NOT NULL,
	`create_time` DATE NULL DEFAULT NULL,
	`city_id` BIGINT(19) NULL DEFAULT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `tourist_attractions_city_id_9dba5f47_fk_tourist_city_id` (`city_id`) USING BTREE,
	CONSTRAINT `tourist_attractions_city_id_9dba5f47_fk_tourist_city_id` FOREIGN KEY (`city_id`) REFERENCES `tourist_city` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=662
;
 *
 */
public class AttrEntity {
	private int id;
	private String name;
	private int level;
	private String create_time;
	private int city_id;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	@Override
	public String toString() {
		return "AttrEntiy [id=" + id + ", name=" + name + ", level=" + level + ", create_time=" + create_time + ", city_id=" + city_id + "]";
	}
	
}
