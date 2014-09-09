package pacman.extensions;

import java.util.Map;
import java.util.Map.Entry;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class JavaScriptInvoker implements ScriptInvoker {
	@Override
	public Object invoke(String source, Map<String, Object> arguments) {
		if (source == null)
			return null;
		
		source = source.trim();
		
		if (source.equals(""))
			return "";
		
		try {
			Context ctx = Context.enter();
			Scriptable scope = ctx.initStandardObjects();
			
			if (arguments != null)
				for (Entry<String, Object> arg : arguments.entrySet())
					ScriptableObject.putProperty(scope, arg.getKey(), arg.getValue());
			
			Object result = ctx.evaluateString(scope,
					// FIXME if dynamic eval() too slow, should make it inlined(precompiled for reused)
					source.trim().startsWith("function") ? "(" + source + ")()" : source,
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
