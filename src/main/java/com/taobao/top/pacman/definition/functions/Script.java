package com.taobao.top.pacman.definition.functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taobao.top.pacman.ActivityContext;
import com.taobao.top.pacman.Asserter;
import com.taobao.top.pacman.Function;
import com.taobao.top.pacman.InlinedFunctionValue;
import com.taobao.top.pacman.Variable;
import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.DefinitionValidator;
import com.taobao.top.pacman.definition.InlinedFunctionDefinition;
import com.taobao.top.pacman.definition.VariableReferenceDefinition;
import com.taobao.top.pacman.extensions.ScriptInvoker;

public class Script extends InlinedFunctionDefinition {
	private String source;
	private VariableReferenceDefinition[] variables;
	
	public Script(String source, VariableReferenceDefinition... variables) {
		this.source = source;
		this.variables = variables;
	}
	
	@Override
	public InlinedFunctionValue toFunction(ActivityDefinition parent, DefinitionValidator validator) {
		if (this.source == null)
			validator.addError("source not set");
		
		List<Variable> variables = null;
		if (this.variables != null) {
			variables = new ArrayList<Variable>();
			for (VariableReferenceDefinition definition : this.variables)
				variables.add(definition.toVariable(parent, validator));
		}
		
		return new InlinedFunctionValue(new ScriptFunction(this.source, variables));
	}
	
	class ScriptFunction implements Function<ActivityContext, Object> {
		private String source;
		private List<Variable> variables;
		
		public ScriptFunction(String source, List<Variable> variables) {
			this.source = source;
			this.variables = variables;
		}
		
		@Override
		public Object execute(ActivityContext context) {
			return Asserter.
					getOrThrowIfExtensionMissing(context, ScriptInvoker.class).
					invoke(this.source, this.getArguments(context));
		}
		
		private Map<String, Object> getArguments(ActivityContext context) {
			if (this.variables == null)
				return null;
			
			Map<String, Object> arguments = new HashMap<String, Object>();
			for (Variable variable : this.variables)
				arguments.put(variable.getName(), variable.get(context));
			return arguments;
		}
	}
}
