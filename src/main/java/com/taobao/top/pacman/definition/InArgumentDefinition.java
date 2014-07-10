package com.taobao.top.pacman.definition;

import com.taobao.top.pacman.InArgument;
import com.taobao.top.pacman.Variable;

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
	
	public InArgumentDefinition(ActivityWithResultDefinition expression) {
		this.activityWithResult = expression;
	}
	
	public InArgument toArgument(ActivityDefinition parent, DefinitionValidator validator) {
		if (this.variable != null) {
			Variable variable = this.variable.toVariable(parent, validator);
			return variable != null ? new InArgument(variable) : null;
		}
		
		if (this.activityWithResult != null)
			return new InArgument(this.activityWithResult.internalToActivityWithResult(parent, validator));
		
		return new InArgument(this.constValue);
	}
}
