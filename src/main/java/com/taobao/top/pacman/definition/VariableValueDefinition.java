package com.taobao.top.pacman.definition;

import com.taobao.top.pacman.ActivityWithResult;
import com.taobao.top.pacman.VariableValue;

public class VariableValueDefinition extends ActivityWithResultDefinition {
	private VariableReferenceDefinition variable;
	
	public VariableValueDefinition() {
		this("VariableValue");
	}
	
	public VariableValueDefinition(String displayName) {
		super(displayName);
	}
	
	public VariableValueDefinition(String displayName, ActivityDefinition parent) {
		super(displayName, parent);
	}
	
	public VariableValueDefinition Variable(VariableReferenceDefinition variable) {
		this.variable = variable;
		return this;
	}
	
	@Override
	public ActivityWithResult internalToActivityWithResult(ActivityDefinition parent, DefinitionValidator validator) {
		return new VariableValue(this.variable.toVariable(parent, validator));
	}
}
