package com.taobao.top.pacman.definition;

import com.taobao.top.pacman.ActivityContext;
import com.taobao.top.pacman.ActivityWithResult;
import com.taobao.top.pacman.Function;
import com.taobao.top.pacman.InlinedFunctionValue;

public abstract class InlinedFunctionDefinition extends ActivityWithResultDefinition {
	@Override
	public ActivityWithResult toActivity(ActivityDefinition parent, DefinitionValidator validator) {
		return new InlinedFunctionValue(this.getFunction(parent, validator));
	}
	
	public abstract Function<ActivityContext, Object> getFunction(ActivityDefinition parent, DefinitionValidator validator);
}
