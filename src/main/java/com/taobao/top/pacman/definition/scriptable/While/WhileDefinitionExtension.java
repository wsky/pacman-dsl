package com.taobao.top.pacman.definition.scriptable.While;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.WhileDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;

public class WhileDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "While";
	}

	public String getAdapterName() {
		return WhileDefinitionAdapter.class.getSimpleName();
	}

	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return WhileDefinitionAdapter.class;
	}

	public ActivityDefinition create(Object... args) {
		return new WhileDefinition();
	}
}
