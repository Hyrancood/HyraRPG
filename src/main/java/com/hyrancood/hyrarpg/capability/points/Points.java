package com.hyrancood.hyrarpg.capability.points;

public class Points implements IPoints {
	private int points[];
	
	public Points() {
		this.points = new int[4];
	}

	public int getPoints(int id) {
		return this.points[id];
	}

	@Override
	public void setPoints(int id, int amount) {
		this.points[id] = amount;
	}

	@Override
	public void addPoints(int id, int amount) {
		this.points[id] += amount;
	}

	@Override
	public void addPoint(int id) {
		this.points[id]++;
	}

	@Override
	public int[] getPointsArray() {
		return this.points;
	}

	@Override
	public void setPointsArray(int[] array) {
		this.points = array;
	}
}
