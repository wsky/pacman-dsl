package com.taobao.top.pacman.definition.scriptable;

import org.mozilla.javascript.NativeJavaObject;

public class ScriptableUtil {
	public static void throwNotSupportTheExtension(DefinitionAdapter<?> adapter, Object activity) {
		throw new RuntimeException(String.format("%s not support %s",
				adapter.getExtensionName(),
				((DefinitionAdapter<?>)
				((NativeJavaObject) activity).unwrap()).getExtensionName()));
	}
	
	public static boolean canGetDisplayNameFromCreateArgs(Object... args) {
		return args != null && args.length > 0 && !"undefined".equals(args[0]);
	}
}
