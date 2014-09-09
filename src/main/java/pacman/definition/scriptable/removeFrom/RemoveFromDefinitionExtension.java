package pacman.definition.scriptable.removeFrom;

import pacman.definition.ActivityDefinition;
import pacman.definition.RemoveFromDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.DefinitionExtension;

public class RemoveFromDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "RemoveFrom";
	}
	
	public String getAdapterName() {
		return RemoveFromDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return RemoveFromDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return args == null ? new RemoveFromDefinition() : new RemoveFromDefinition((String) args[0]);
	}
	
}
