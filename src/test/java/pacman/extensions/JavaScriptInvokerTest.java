package pacman.extensions;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import pacman.extensions.JavaScriptInvoker;

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
	
	@Test
	public void empty_source_test() {
		assertEquals(null, new JavaScriptInvoker().invoke(null, null));
		assertEquals("", new JavaScriptInvoker().invoke("", null));
	}
	
	@Test
	public void compiled_test() {
		JavaScriptInvoker invoker = new JavaScriptInvoker();
		
		Map<String, Object> arguments = new HashMap<String, Object>();
		arguments.put("arg1", "hi");
		arguments.put("arg2", 123);
		
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++)
			invoker.invoke("function(){ return arg1 + arg2 }", arguments);
		System.out.println(System.currentTimeMillis() - begin);
	}
}
