package com.cs311.pa1;

public class Result implements IResult {
	int size;
	long time;
	public Result(int newSize, long newTime) {
		this.size = newSize;
		this.time = newTime;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public long getTime() {
		return time;
	}
}