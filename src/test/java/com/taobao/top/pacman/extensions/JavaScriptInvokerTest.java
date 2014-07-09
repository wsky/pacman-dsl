package com.taobao.top.pacman.extensions;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class JavaScriptInvokerTest {
	@Test
	public void invoke_test() {
		Map<String, Object> arguments = new HashMap<String, Object>();
		arguments.put("arg1", "hi");
		arguments.put("arg2", 123);
		assertEquals("hi123", new JavaScriptInvoker().invoke("function(){ return arg1 + arg2 }", arguments));
		assertEquals("hi123", new JavaScriptInvoker().invoke("function f(){ return arg1 + arg2 }", arguments));
		assertEquals("hi123", new JavaScriptInvoker().invoke("arg1 + arg2", arguments));
	}
}
