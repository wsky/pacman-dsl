package com.taobao.top.pacman.definition;

import com.taobao.top.pacman.ActivityWithResult;

public abstract class ActivityWithResultDefinition {
	public abstract ActivityWithResult toActivity(ActivityDefinition parent, DefinitionValidator validator);
}
