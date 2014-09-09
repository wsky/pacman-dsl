package pacman.definition;

import pacman.ActivityWithResult;
import pacman.statements.NotEqual;

public class NotEqualDefinition extends ActivityWithResultDefinition {
	private InArgumentDefinition left;
	private InArgumentDefinition right;
	
	public NotEqualDefinition() {
		this("NotEqual");
	}
	
	public NotEqualDefinition(String displayName) {
		super(displayName);
	}
	
	public NotEqualDefinition Left(Object input) {
		this.left = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	public NotEqualDefinition Right(Object input) {
		this.right = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	@Override
	public ActivityWithResult internalToActivityWithResult(ActivityDefinition parent, DefinitionValidator validator) {
		if (this.left == null)
			validator.addError("Left not set");
		if (this.left == null)
			validator.addError("Right not set");
		if (validator.hasError())
			return null;
		
		NotEqual equal = new NotEqual();
		equal.Left = this.left.toArgument(parent, validator);
		equal.Right = this.right.toArgument(parent, validator);
		return equal;
	}
	
}
