package pacman.definition.scriptable.newMap;

import pacman.definition.ActivityDefinition;
import pacman.definition.NewMapDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.DefinitionExtension;

public class NewMapDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "NewMap";
	}
	
	public String getAdapterName() {
		return NewMapDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return NewMapDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return args == null ? new NewMapDefinition() : new NewMapDefinition((String) args[0]);
	}
	
}
