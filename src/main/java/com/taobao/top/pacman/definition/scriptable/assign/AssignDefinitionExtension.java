package com.taobao.top.pacman.definition.scriptable.assign;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.AssignDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;

public class AssignDefinitionExtension implements DefinitionExtension{
	public String getName() {
		return "Assign";
	}

	public String getAdapterName() {
		return AssignDefinitionAdapter.class.getSimpleName();
	}

	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return AssignDefinitionAdapter.class;
	}

	public ActivityDefinition create(Object... args) {
		return new AssignDefinition();
	}
}
