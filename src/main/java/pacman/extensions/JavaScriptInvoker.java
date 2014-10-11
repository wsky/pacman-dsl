package pacman.extensions;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class JavaScriptInvoker implements ScriptInvoker {
	private Map<String, Script> compiled = new HashMap<String, Script>();
	
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
			
			Script script = this.compiled.get(source);
			if (script == null) {
				synchronized (this.compiled) {
					if ((script = this.compiled.get(source)) == null) {
						String source2 = source.startsWith("function") ? "(" + source + ")()" : source;
						this.compiled.put(source,
								script = ctx.compileString(source2, "", 1, null));
					}
				}
			}
			
			Object result = script.exec(ctx, scope);
			
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
