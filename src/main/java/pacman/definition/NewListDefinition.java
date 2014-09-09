package pacman.definition;

import java.util.ArrayList;

import pacman.ActivityWithResult;
import pacman.expressions.New;

public class NewListDefinition extends ActivityWithResultDefinition {
	public NewListDefinition() {
		this("NewList");
	}
	
	public NewListDefinition(String displayName) {
		super(displayName);
	}
	
	@Override
	public ActivityWithResult internalToActivityWithResult(ActivityDefinition parent, DefinitionValidator validator) {
		return new New(ArrayList.class);
	}
}
