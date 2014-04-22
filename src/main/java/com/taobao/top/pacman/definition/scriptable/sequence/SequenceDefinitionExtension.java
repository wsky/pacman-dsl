package com.taobao.top.pacman.definition.scriptable.sequence;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.SequenceDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;

public class SequenceDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "Sequence";
	}

	public String getAdapterName() {
		return SequenceDefinitionAdapter.class.getSimpleName();
	}

	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return SequenceDefinitionAdapter.class;
	}

	public ActivityDefinition create(Object... args) {
		return new SequenceDefinition();
	}

}
