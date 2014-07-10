package com.taobao.top.pacman.definition.scriptable.newMap;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.NewMapDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;

public class NewMapDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "NewMap";
	}
	
	public String getAdapterName() {
		return NewMapDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return NewMapDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return args == null ? new NewMapDefinition() : new NewMapDefinition((String) args[0]);
	}
	
}
