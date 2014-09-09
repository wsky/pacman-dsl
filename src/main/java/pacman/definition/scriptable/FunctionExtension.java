package pacman.definition.scriptable;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.mozilla.javascript.FunctionObject;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Scriptable;

public abstract class FunctionExtension {
	public List<FunctionObject> getFunctions(Scriptable scope) {
		List<String> names = this.getMethodNames();
		List<FunctionObject> functions = new ArrayList<FunctionObject>();

		Method[] methods = this.getClass().getMethods();
		for (Method method : methods)
			if (names.contains(method.getName()))
				functions.add(new FunctionObject(method.getName(), method, scope));

		return functions;
	}

	public abstract List<String> getMethodNames();

	@SuppressWarnings("unchecked")
	protected static <T> T[] unwrapArray(Object value, Class<T> type) {
		if (value == null)
			return null;
		if (value instanceof NativeArray)
			return (T[]) ((NativeArray) value).toArray((T[]) Array.newInstance(type, 0));
		return (T[]) value;
	}

	@SuppressWarnings("unchecked")
	protected static <T> T unwrap(Object value, Class<T> type) {
		if (value == null)
			return null;
		if (value instanceof NativeJavaObject)
			return (T) ((NativeJavaObject) value).unwrap();
		return (T) value;
	}
}
