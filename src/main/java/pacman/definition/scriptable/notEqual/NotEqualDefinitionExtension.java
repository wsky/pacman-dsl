package pacman.definition.scriptable.notEqual;

import pacman.definition.ActivityDefinition;
import pacman.definition.NotEqualDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.DefinitionExtension;

public class NotEqualDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "NotEqual";
	}
	
	public String getAdapterName() {
		return NotEqualDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return NotEqualDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return args == null ? new NotEqualDefinition() : new NotEqualDefinition((String) args[0]);
	}
}
