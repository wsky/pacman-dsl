package com.taobao.top.pacman.definition.scriptable.removeFrom;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.RemoveFromDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;

public class RemoveFromDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "RemoveFrom";
	}
	
	public String getAdapterName() {
		return RemoveFromDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return RemoveFromDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return args == null ? new RemoveFromDefinition() : new RemoveFromDefinition((String) args[0]);
	}
	
}
