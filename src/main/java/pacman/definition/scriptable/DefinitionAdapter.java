package pacman.definition.scriptable;

import java.lang.reflect.Method;

import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.ScriptableObject;

import pacman.definition.ActivityDefinition;

public abstract class DefinitionAdapter<T extends ActivityDefinition> extends ScriptableObject {
	private static final long serialVersionUID = -3605598589081963739L;
	private Class<T> definitionType;
	private T definition;
	private DefinitionAdapter<?> parent;
	private String extensionName;

	@SuppressWarnings("unchecked")
	public DefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
		if (definition != null) {
			this.definition = definition instanceof NativeJavaObject ?
					(T) ((NativeJavaObject) definition).unwrap() :
					(T) definition;
			this.definitionType = (Class<T>) this.definition.getClass();
		}
		this.parent = parent;
		this.extensionName = extensionName;
	}

	public T getDefinition() {
		return this.definition;
	}

	public String getExtensionName() {
		return this.extensionName;
	}

	@Override
	public String getClassName() {
		return this.getClass().getSimpleName();
	}

	public abstract DefinitionAdapter<?> jsFunction_End();

	protected DefinitionAdapter<?> end() {
		return this.parent;
	}

	public abstract void jsFunction_addActivity(Object activity);

	protected void addActivity(Object activity) {
		this.getDefinition().Activity(this.unwrap(activity, ActivityDefinition.class));
	}

	@SuppressWarnings("unchecked")
	protected <TJava> TJava unwrap(Object object, Class<TJava> type) {
		return (TJava) this.unwrap(object);
	}

	protected Object unwrap(Object object) {
		return object != null &&
				object instanceof NativeJavaObject ?
				((NativeJavaObject) object).unwrap() :
				object;
	}

	protected void setIn(String name, Object value) {
		this.setValue("In", name, value);
	}

	protected void setVar(String name, Object value) {
		this.setValue("Var", name, value);
	}

	protected void setValue(String to, Object value) {
		this.setValue(to, null, value);
	}

	private void setValue(String to, String key, Object value) {
		Object arg = this.unwrap(value);
		// FIXME make more understandable
		Method method = this.findMethod(to,
				arg != null ? arg.getClass() : null,
				key == null ? 0 : 1);

		if (method == null)
			throw new RuntimeException(
					new NoSuchMethodException(
							String.format("%s.%s(%s%s)",
									this.getDefinition().getClass().getName(),
									to,
									key == null ? "" : key.getClass().getName(),
									arg.getClass().getName())));

		if (key == null)
			this.invokeMethod(method, arg);
		else
			this.invokeMethod(method, key, arg);
	}

	private void invokeMethod(Method method, Object... args) {
		try {
			method.invoke(this.getDefinition(), args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Method findMethod(String name, Class<?> parameterType, int index) {
		Method[] methods = this.definitionType.getMethods();
		Method potentialMethod = null;
		for (Method method : methods) {
			if (!method.getName().equals(name))
				continue;

			if (parameterType == null &&
					method.getParameterTypes().length == 0)
				return method;

			if (index > method.getParameterTypes().length - 1)
				continue;

			// HACK only supprt method(string, value) or method(value)
			Class<?> type = method.getParameterTypes()[index];

			// exact method
			if (type.equals(parameterType))
				return method;

			if (!type.isAssignableFrom(parameterType))
				continue;
			if (!type.equals(Object.class))
				potentialMethod = method;
			else if (potentialMethod == null)
				potentialMethod = method;
		}
		return potentialMethod;
	}
}
