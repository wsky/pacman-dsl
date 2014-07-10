package com.taobao.top.pacman.definition.scriptable.newList;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.NewListDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;

public class NewListDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "NewList";
	}
	
	public String getAdapterName() {
		return NewListDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return NewListDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return args == null ? new NewListDefinition() : new NewListDefinition((String) args[0]);
	}
	
}
