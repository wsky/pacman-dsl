package com.taobao.top.pacman.definition.scriptable.getFrom;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.GetFromDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;

public class GetFromDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "GetFrom";
	}
	
	public String getAdapterName() {
		return GetFromDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return GetFromDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return args == null ? new GetFromDefinition() : new GetFromDefinition((String) args[0]);
	}
}
