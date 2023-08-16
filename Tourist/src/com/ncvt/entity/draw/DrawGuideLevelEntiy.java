package com.ncvt.entity.draw;

public class DrawGuideLevelEntiy {
	private int level;
	private int count;
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "DrawGuideLevelEntiy [level" + level + ", count=" + count + "]";
	}
	
}
