package com.taobao.top.pacman.definition.scriptable.script;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.ScriptDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;

public class ScriptDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "Scripting";
	}
	
	public String getAdapterName() {
		return ScriptDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return ScriptDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return args == null ? new ScriptDefinition() : new ScriptDefinition((String) args[0]);
	}
	
}
