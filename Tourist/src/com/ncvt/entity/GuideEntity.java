package com.ncvt.entity;

/**
 * 数据库表：tourist_attractions
 * @author Administrator
 * CREATE TABLE `tourist_guide` (
	`id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(10) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`gender` SMALLINT(5) NOT NULL,
	`guide_id` VARCHAR(20) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`lang` VARCHAR(20) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`level` SMALLINT(5) NOT NULL,
	`institutions` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`phone` VARCHAR(11) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=19349
;
 *
 */
public class GuideEntity {
	private int id;
	private String name;
	private int gender;
	private String guide_id;
	private String lang;
	private int level;
	private String institutions;
	private String phone;
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
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getGuide_id() {
		return guide_id;
	}
	public void setGuide_id(String guide_id) {
		this.guide_id = guide_id;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getInstitutions() {
		return institutions;
	}
	public void setInstitutions(String institutions) {
		this.institutions = institutions;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "GuideEntiy [id=" + id + ", name=" + name + ", gender=" + gender + ", "+ "guide_id=" + guide_id + ", lang=" + lang + ", level=" + level + ", institutions=" + institutions + ", phone=" + phone+"]";
	}
	
}
