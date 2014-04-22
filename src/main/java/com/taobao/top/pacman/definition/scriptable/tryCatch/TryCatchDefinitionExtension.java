package com.taobao.top.pacman.definition.scriptable.tryCatch;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.TryCatchDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;

public class TryCatchDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "TryCatch";
	}

	public String getAdapterName() {
		return TryCatchDefinitionAdapter.class.getSimpleName();
	}

	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return TryCatchDefinitionAdapter.class;
	}

	public ActivityDefinition create(Object... args) {
		return new TryCatchDefinition();
	}

}
