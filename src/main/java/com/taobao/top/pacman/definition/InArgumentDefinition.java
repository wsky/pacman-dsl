package com.taobao.top.pacman.definition;

import com.taobao.top.pacman.InArgument;

public class InArgumentDefinition {
	private Object constValue;
	private VariableReferenceDefinition variable;
	private ActivityWithResultDefinition activityWithResult;
	
	public InArgumentDefinition() {
	}
	
	public InArgumentDefinition(Object constValue) {
		this.constValue = constValue;
	}
	
	public InArgumentDefinition(VariableReferenceDefinition variable) {
		this.variable = variable;
	}
	
	public InArgumentDefinition(ActivityWithResultDefinition activityWithResult) {
		this.activityWithResult = activityWithResult;
	}
	
	public InArgument toArgument(ActivityDefinition parent, DefinitionValidator validator) {
		if (this.variable != null)
			return new InArgument(this.variable.toVariable(parent, validator));
		
		if (this.activityWithResult != null)
			return new InArgument(this.activityWithResult.internalToActivityWithResult(parent, validator));
		
		return new InArgument(this.constValue);
	}
}
