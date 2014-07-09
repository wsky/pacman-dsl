package com.taobao.top.pacman.statements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taobao.top.pacman.ActivityContext;
import com.taobao.top.pacman.ActivityMetadata;
import com.taobao.top.pacman.Asserter;
import com.taobao.top.pacman.CodeActivityContext;
import com.taobao.top.pacman.CodeActivityWithResult;
import com.taobao.top.pacman.InArgument;
import com.taobao.top.pacman.RuntimeArgument;
import com.taobao.top.pacman.Variable;
import com.taobao.top.pacman.RuntimeArgument.ArgumentDirection;
import com.taobao.top.pacman.extensions.ScriptInvoker;

public class Script extends CodeActivityWithResult {
	private List<Variable> variables;
	
	public InArgument Source;
	
	public List<Variable> getVariables() {
		if (this.variables == null)
			this.variables = new ArrayList<Variable>();
		return this.variables;
	}
	
	@Override
	protected void cacheMetadata(ActivityMetadata metadata) {
		metadata.bindAndAddArgument(this.Source, new RuntimeArgument("Source", Object.class, ArgumentDirection.In));
		
		if (this.variables != null)
			for (Variable variable : this.getVariables())
				metadata.addRuntimeVariable(variable);
	}
	
	@Override
	protected Object execute(CodeActivityContext context) {
		return Asserter.
				getOrThrowIfExtensionMissing(context, ScriptInvoker.class).
				invoke((String) this.Source.get(context), this.getArguments(context));
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
