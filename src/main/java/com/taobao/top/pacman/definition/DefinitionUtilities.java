package com.taobao.top.pacman.definition;

import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;

public class DefinitionUtilities {
	public static void traversal(ActivityDefinition definition, Callback callback) {
		callback.execute(definition);
		for (ActivityDefinition child : definition.activities)
			traversal(child, callback);
	}
	
	public static interface Callback {
		public void execute(ActivityDefinition definition);
	}
	
	public static InArgumentDefinition parseInArgument(Object input) {
		if (input instanceof ActivityWithResultDefinition)
			return new InArgumentDefinition((ActivityWithResultDefinition) input);
		if (input instanceof DefinitionAdapter<?>)
			return new InArgumentDefinition((ActivityWithResultDefinition) ((DefinitionAdapter<?>) input).getDefinition());
		if (input instanceof VariableReferenceDefinition)
			return new InArgumentDefinition((VariableReferenceDefinition) input);
		
		return new InArgumentDefinition(input);
	}
}
