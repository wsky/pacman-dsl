package com.taobao.top.pacman.definition.scriptable.writeLine;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.WriteLineDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;

public class WriteLineDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "WriteLine";
	}

	public String getAdapterName() {
		return WriteLineDefinitionAdapter.class.getSimpleName();
	}

	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return WriteLineDefinitionAdapter.class;
	}

	public ActivityDefinition create(Object... args) {
		return new WriteLineDefinition();
	}

}
