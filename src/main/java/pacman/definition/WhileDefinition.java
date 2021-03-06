package pacman.definition;

import pacman.Activity;
import pacman.statements.While;

public class WhileDefinition extends ActivityDefinition {
	protected ActivityWithResultDefinition condition;
	protected ActivityDefinition body;
	
	public WhileDefinition() {
		this("While");
	}
	
	public WhileDefinition(String displayName) {
		super(displayName);
	}
	
	public WhileDefinition Condition(ActivityWithResultDefinition condition) {
		this.condition = condition;
		return this;
	}
	
	public WhileDefinition Condition(Object input) {
		return this.Condition(DefinitionUtilities.parseActivityWithResult(input));
	}
	
	public WhileDefinition Body(ActivityDefinition activity) {
		this.body = activity;
		this.addActivity(activity);
		return this;
	}
	
	@Override
	protected Activity internalToActivity(DefinitionValidator validator) {
		if (this.condition == null)
			validator.addError("Condition not set");
		if (validator.hasError())
			return null;
		
		While _while = new While();
		_while.setDisplayName(this.getDisplayName());
		_while.Condition = this.condition.internalToActivityWithResult(this.getParent(), validator);
		if (this.body != null)
			_while.Body = this.body.toActivity(validator);
		return _while;
	}
	
	// fluent
	
	public ActivityDefinition Body() {
		this.Body(new ReferenceActivityDefinition("Body"));
		return this.body;
	}
}
