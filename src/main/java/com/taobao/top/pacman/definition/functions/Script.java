package com.taobao.top.pacman.definition.functions;

import java.util.ArrayList;
import java.util.List;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import com.taobao.top.pacman.ActivityContext;
import com.taobao.top.pacman.Function;
import com.taobao.top.pacman.InlinedFunctionValue;
import com.taobao.top.pacman.Variable;
import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.DefinitionValidator;
import com.taobao.top.pacman.definition.InlinedFunctionDefinition;
import com.taobao.top.pacman.definition.VariableReferenceDefinition;

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

		final List<Variable> variables = new ArrayList<Variable>();
		if (this.variables != null) {
			for (VariableReferenceDefinition definition : this.variables)
				variables.add(definition.toVariable(parent, validator));
		}

		final Script thisPtr = this;

		return new InlinedFunctionValue(new Function<ActivityContext, Object>() {
			public Object execute(ActivityContext context) {
				try {
					Context ctx = Context.enter();
					Scriptable scope = ctx.initStandardObjects();

					for (Variable variable : variables)
						ScriptableObject.putProperty(scope, variable.getName(), variable.get(context));

					Object result = ctx.evaluateString(scope,
							// NOTE if dynamic eval() too slow, should make it inlined(precompiled for reused)
							"(" + thisPtr.source + ")()", "Script", 1, null);

					if (result == null)
						return result;
					if (result instanceof NativeJavaObject)
						return ((NativeJavaObject) result).unwrap();
					return result;
				} finally {
					Context.exit();
				}
			}
		});
	}
}
