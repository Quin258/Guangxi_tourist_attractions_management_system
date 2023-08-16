package com.ncvt.entity;

/**
 * 数据库表：tourist_admin
 * @author Administrator
 * CREATE TABLE `tourist_admin` (
	`id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(32) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`password` VARCHAR(64) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=5;
 *
 */
public class AdminEntity {
	private int id;
	private String username;
	private String password;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "AdminEntiy [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
}
