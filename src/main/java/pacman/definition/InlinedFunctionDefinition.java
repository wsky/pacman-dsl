package pacman.definition;

import pacman.ActivityContext;
import pacman.ActivityWithResult;
import pacman.Function;
import pacman.InlinedFunctionValue;

public abstract class InlinedFunctionDefinition extends ActivityWithResultDefinition {
	public InlinedFunctionDefinition() {
		this("InlinedFunctionValue");
	}
	
	public InlinedFunctionDefinition(String displayName) {
		super(displayName);
	}
	
	public InlinedFunctionDefinition(String displayName, ActivityDefinition parent) {
		super(displayName, parent);
	}
	
	@Override
	public ActivityWithResult internalToActivityWithResult(ActivityDefinition parent, DefinitionValidator validator) {
		return new InlinedFunctionValue(this.getFunction(parent, validator));
	}
	
	public abstract Function<ActivityContext, Object> getFunction(ActivityDefinition parent, DefinitionValidator validator);
}
