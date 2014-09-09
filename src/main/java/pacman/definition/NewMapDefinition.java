package pacman.definition;

import java.util.HashMap;

import pacman.ActivityWithResult;
import pacman.expressions.New;

public class NewMapDefinition extends ActivityWithResultDefinition {
	public NewMapDefinition() {
		this("NewMap");
	}
	
	public NewMapDefinition(String displayName) {
		super(displayName);
	}
	
	@Override
	public ActivityWithResult internalToActivityWithResult(ActivityDefinition parent, DefinitionValidator validator) {
		return new New(HashMap.class);
	}
	
}
