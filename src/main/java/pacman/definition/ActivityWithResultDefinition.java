package pacman.definition;

import pacman.Activity;
import pacman.ActivityWithResult;

public abstract class ActivityWithResultDefinition extends ActivityDefinition {
	private OutArgumentDefinition result;
	
	public ActivityWithResultDefinition(String displayName) {
		super(displayName);
	}
	
	public ActivityWithResultDefinition(String displayName, ActivityDefinition parent) {
		super(displayName, parent);
	}
	
	public ActivityWithResultDefinition Result(VariableReferenceDefinition variable) {
		return this.Result(new OutArgumentDefinition(variable));
	}
	
	public ActivityWithResultDefinition Result(OutArgumentDefinition result) {
		this.result = result;
		return this;
	}
	
	@Override
	protected Activity internalToActivity(DefinitionValidator validator) {
		ActivityWithResult activityWithResult = this.internalToActivityWithResult(this.getParent(), validator);
		
		if (this.result != null)
			activityWithResult.setResult(this.result.toArgument(this.getParent(), validator));
		
		return activityWithResult;
	}
	
	public abstract ActivityWithResult internalToActivityWithResult(ActivityDefinition parent, DefinitionValidator validator);
}
