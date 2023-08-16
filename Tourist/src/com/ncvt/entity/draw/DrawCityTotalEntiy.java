package com.ncvt.entity.draw;

public class DrawCityTotalEntiy {
	private int id;
	private float Total_revenue;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public float getTotal_revenue() {
		return Total_revenue;
	}

	public void setTotal_revenue(float Total_revenue) {
		this.Total_revenue = Total_revenue;
	}
	@Override
	public String toString() {
		return "DrawCityTotalEntiy [id=" + id + ", Total_revenue=" + Total_revenue + "]";
	}
	
}
