package pacman.extensions;

import java.util.Map;

public interface ScriptInvoker {
	public Object invoke(String source, Map<String, Object> arguments);
}
