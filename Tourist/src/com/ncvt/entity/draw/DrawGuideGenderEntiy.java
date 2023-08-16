package com.ncvt.entity.draw;

public class DrawGuideGenderEntiy {
	private int gender;
	private int count;
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "DrawGuideGenderEntiy [Gender" + gender + ", count=" + count + "]";
	}
	
}
