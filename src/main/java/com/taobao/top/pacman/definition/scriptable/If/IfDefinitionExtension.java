package com.taobao.top.pacman.definition.scriptable.If;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.IfDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;

public class IfDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "If";
	}

	public String getAdapterName() {
		return IfDefinitionAdapter.class.getSimpleName();
	}

	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return IfDefinitionAdapter.class;
	}

	public ActivityDefinition create(Object... args) {
		return new IfDefinition();
	}
}
