package com.taobao.top.pacman.definition;

import java.util.HashMap;

import com.taobao.top.pacman.ActivityWithResult;
import com.taobao.top.pacman.expressions.New;

public class NewMapDefinition extends ActivityWithResultDefinition {
	public NewMapDefinition() {
		this("NewMap");
	}
	
	public NewMapDefinition(String displayName) {
		super(displayName);
	}
	
	@Override
	public ActivityWithResult internalToActivityWithResult(ActivityDefinition parent, DefinitionValidator validator) {
		return new New(HashMap.class);
	}
	
}
