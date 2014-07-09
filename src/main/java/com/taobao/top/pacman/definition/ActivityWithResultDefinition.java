package com.taobao.top.pacman.definition;

import com.taobao.top.pacman.Activity;
import com.taobao.top.pacman.ActivityWithResult;

public abstract class ActivityWithResultDefinition extends ActivityDefinition {
	public ActivityWithResultDefinition(String displayName) {
		super(displayName);
	}
	
	public ActivityWithResultDefinition(String displayName, ActivityDefinition parent) {
		super(displayName, parent);
	}
	
	@Override
	protected Activity internalToActivity(DefinitionValidator validator) {
		return this.internalToActivityWithResult(this.getParent(), validator);
	}
	
	public abstract ActivityWithResult internalToActivityWithResult(ActivityDefinition parent, DefinitionValidator validator);
}
