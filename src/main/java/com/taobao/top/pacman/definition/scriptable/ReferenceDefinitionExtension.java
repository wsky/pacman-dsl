package com.taobao.top.pacman.definition.scriptable;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.ReferenceActivityDefinition;

public abstract class ReferenceDefinitionExtension implements DefinitionExtension {
	public String getAdapterName() {
		return ReferenceDefinitionAdapter.class.getSimpleName();
	}

	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return ReferenceDefinitionAdapter.class;
	}

	public ActivityDefinition create(Object... args) {
		return new ReferenceActivityDefinition(this.getName());
	}
}