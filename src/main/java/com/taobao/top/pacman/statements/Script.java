package com.taobao.top.pacman.statements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taobao.top.pacman.ActivityContext;
import com.taobao.top.pacman.ActivityMetadata;
import com.taobao.top.pacman.Asserter;
import com.taobao.top.pacman.InArgument;
import com.taobao.top.pacman.NativeActivity;
import com.taobao.top.pacman.NativeActivityContext;
import com.taobao.top.pacman.OutArgument;
import com.taobao.top.pacman.RuntimeArgument;
import com.taobao.top.pacman.Variable;
import com.taobao.top.pacman.RuntimeArgument.ArgumentDirection;
import com.taobao.top.pacman.extensions.ScriptInvoker;

public class Script extends NativeActivity {
	private List<Variable> variables;
	
	public InArgument Source;
	public OutArgument Result;
	
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
		
		if (this.Result != null)
			metadata.bindAndAddArgument(this.Result, new RuntimeArgument("Result", Object.class, ArgumentDirection.Out));
	}
	
	@Override
	protected void execute(NativeActivityContext context) throws Exception {
		Object result = Asserter.
				getOrThrowIfExtensionMissing(context, ScriptInvoker.class).
				invoke((String) this.Source.get(context), this.getArguments(context));
		
		if (this.Result != null)
			this.Result.set(context, result);
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
