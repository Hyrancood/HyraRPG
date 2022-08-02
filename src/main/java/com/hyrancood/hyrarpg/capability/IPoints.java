package com.hyrancood.hyrarpg.capability;

public interface IPoints {
	public int getPoints(int id);

	public void setPoints(int id, int amount);

	public void addPoints(int id, int amount);

	public void addPoint(int id);

	public int[] getPointsArray();
	public void setPointsArray(int[] array);
}
