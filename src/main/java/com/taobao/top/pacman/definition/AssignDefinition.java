package com.taobao.top.pacman.definition;

import com.taobao.top.pacman.Activity;
import com.taobao.top.pacman.statements.Assign;

public class AssignDefinition extends ActivityDefinition {
	private InArgumentDefinition value;
	private OutArgumentDefinition to;
	
	public AssignDefinition() {
		this("Assign");
	}
	
	public AssignDefinition(String displayName) {
		super(displayName);
	}
	
	public AssignDefinition Value(Object value) {
		this.value = DefinitionUtilities.parseInArgument(value);
		return this;
	}
	
	public AssignDefinition To(Object to) {
		this.to = DefinitionUtilities.parseOutArgument(to);
		return this;
	}
	
	@Override
	protected Activity internalToActivity(DefinitionValidator validator) {
		if (this.value == null)
			validator.addError("Value not set");
		if (this.to == null)
			validator.addError("To not set");
		
		if (validator.hasError())
			return null;
		
		Assign assign = new Assign();
		assign.Value = this.value.toArgument(this.getParent(), validator);
		assign.To = this.to.toArgument(this.getParent(), validator);
		return assign;
	}
}
