package com.taobao.top.pacman.definition.scriptable.existsIn;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.ExistsInDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;

public class ExistsInDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "ExistsIn";
	}
	
	public String getAdapterName() {
		return ExistsInDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return ExistsInDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return args == null ? new ExistsInDefinition() : new ExistsInDefinition((String) args[0]);
	}
	
}
