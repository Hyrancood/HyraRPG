package com.hyrancood.hyrarpg.capability;

import java.util.concurrent.Callable;

public class PointsFactory implements Callable<IPoints> {

	@Override
	public IPoints call() throws Exception {
		return new Points();
	}
}
