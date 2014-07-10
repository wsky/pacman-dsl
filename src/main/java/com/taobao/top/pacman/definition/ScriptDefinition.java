package com.taobao.top.pacman.definition;

import java.util.ArrayList;
import java.util.List;

import com.taobao.top.pacman.ActivityWithResult;
import com.taobao.top.pacman.Variable;
import com.taobao.top.pacman.VariableValue;
import com.taobao.top.pacman.statements.Script;

public class ScriptDefinition extends ActivityWithResultDefinition {
	private InArgumentDefinition source;
	private List<VariableReferenceDefinition> variables;
	
	public ScriptDefinition() {
		this("Script");
	}
	
	public ScriptDefinition(String displayName) {
		super(displayName);
	}
	
	public ScriptDefinition(String displayName, ActivityDefinition parent) {
		super(displayName, parent);
	}
	
	public ScriptDefinition Source(String constValue) {
		this.source = new InArgumentDefinition(constValue);
		return this;
	}
	
	public ScriptDefinition Var(String name) {
		this.addVariable(name);
		return this;
	}
	
	public ScriptDefinition Put(VariableReferenceDefinition variable) {
		if (this.variables == null)
			this.variables = new ArrayList<VariableReferenceDefinition>();
		this.variables.add(variable);
		return this;
	}
	
	@Override
	public ActivityWithResult internalToActivityWithResult(ActivityDefinition parent, DefinitionValidator validator) {
		if (this.source == null)
			validator.addError("Source not set");
		if (validator.hasError())
			return null;
		
		Script script = new Script();
		script.Source = this.source.toArgument(parent, validator);
		
		// variable refer to parent's
		if (this.variables != null)
			for (VariableReferenceDefinition v : this.variables)
				script.getVariables().add(
						new Variable(v.getName(),
								new VariableValue(
										v.toVariable(parent, validator))));
		
		// variables of itself
		if (super.variables != null) {
			for (VariableDefinition variable : super.variables) {
				Variable var = variable.toVariable();
				this.addVariable(variable.getName(), var);
				script.getVariables().add(var);
			}
		}
		
		return script;
	}
}
