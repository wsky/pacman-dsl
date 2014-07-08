package com.taobao.top.pacman;

import java.util.Map;

public interface ScriptInvoker {
	public Object invoke(String source, Map<String, Object> arguments);
}
