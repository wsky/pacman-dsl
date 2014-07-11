package com.taobao.top.pacman.definition.scriptable.notEqual;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.NotEqualDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;

public class NotEqualDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "NotEqual";
	}
	
	public String getAdapterName() {
		return NotEqualDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return NotEqualDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return args == null ? new NotEqualDefinition() : new NotEqualDefinition((String) args[0]);
	}
}
