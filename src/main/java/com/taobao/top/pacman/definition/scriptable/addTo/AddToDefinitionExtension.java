package com.taobao.top.pacman.definition.scriptable.addTo;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.AddToDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;
import com.taobao.top.pacman.definition.scriptable.ScriptableUtil;

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
		return ScriptableUtil.canGetDisplayNameFromCreateArgs(args) ?
				new AddToDefinition((String) args[0]) :
				new AddToDefinition();
	}
	
}
