package com.taobao.top.pacman.definition.scriptable.existsIn;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.ExistsInDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;
import com.taobao.top.pacman.definition.scriptable.ScriptableUtil;

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
		return ScriptableUtil.canGetDisplayNameFromCreateArgs(args) ?
				new ExistsInDefinition((String) args[0]) :
				new ExistsInDefinition();
	}
	
}
