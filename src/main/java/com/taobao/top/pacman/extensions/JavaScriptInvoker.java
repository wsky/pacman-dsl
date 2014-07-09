package com.taobao.top.pacman.extensions;

import java.util.Map;
import java.util.Map.Entry;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class JavaScriptInvoker implements ScriptInvoker {
	@Override
	public Object invoke(String source, Map<String, Object> arguments) {
		try {
			Context ctx = Context.enter();
			Scriptable scope = ctx.initStandardObjects();
			
			for (Entry<String, Object> arg : arguments.entrySet())
				ScriptableObject.putProperty(scope, arg.getKey(), arg.getValue());
			
			Object result = ctx.evaluateString(scope,
					// NOTE if dynamic eval() too slow, should make it inlined(precompiled for reused)
					source.startsWith("function") ? "(" + source + ")()" : source,
					"Script", 1, null);
			
			if (result == null)
				return result;
			if (result instanceof NativeJavaObject)
				return ((NativeJavaObject) result).unwrap();
			return result;
		} finally {
			Context.exit();
		}
	}
	
}
