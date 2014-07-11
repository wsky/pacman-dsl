package com.taobao.top.pacman.definition;

import com.taobao.top.pacman.ActivityWithResult;
import com.taobao.top.pacman.statements.Equal;

public class EqualDefinition extends ActivityWithResultDefinition {
	private InArgumentDefinition left;
	private InArgumentDefinition right;
	
	public EqualDefinition() {
		this("Equal");
	}
	
	public EqualDefinition(String displayName) {
		super(displayName);
	}
	
	public EqualDefinition Left(Object input) {
		this.left = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	public EqualDefinition Right(Object input) {
		this.right = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	@Override
	public ActivityWithResult internalToActivityWithResult(ActivityDefinition parent, DefinitionValidator validator) {
		if (this.left == null)
			validator.addError("Left not set");
		if (this.left == null)
			validator.addError("Right not set");
		if (validator.hasError())
			return null;
		
		Equal equal = new Equal();
		equal.Left = this.left.toArgument(parent, validator);
		equal.Right = this.right.toArgument(parent, validator);
		return equal;
	}
	
}
