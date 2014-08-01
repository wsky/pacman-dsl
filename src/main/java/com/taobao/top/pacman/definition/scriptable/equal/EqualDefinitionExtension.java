package com.taobao.top.pacman.definition.scriptable.equal;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.EqualDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;
import com.taobao.top.pacman.definition.scriptable.ScriptableUtil;

public class EqualDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "Equal";
	}
	
	public String getAdapterName() {
		return EqualDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return EqualDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return ScriptableUtil.canGetDisplayNameFromCreateArgs(args) ?
				new EqualDefinition((String) args[0]) :
				new EqualDefinition();
	}
}
