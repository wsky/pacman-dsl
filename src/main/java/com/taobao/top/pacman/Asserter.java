package com.taobao.top.pacman;

public class Asserter {
	public static <T> T getOrThrowIfExtensionMissing(ActivityContext context, Class<T> type) throws Exception {
		T e = context.getExtension(type);
		if (e == null)
			throw new Exception(String.format("extension \"%s\" can not be null", type.getName()));
		return e;
	}
}
