package com.taobao.top.pacman.definition.scriptable.addTo;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.AddToDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;

public class AddToDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "AddTo";
	}
	
	public String getAdapterName() {
		return AddToDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return AddToDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return args == null ? new AddToDefinition() : new AddToDefinition((String) args[0]);
	}
	
}
