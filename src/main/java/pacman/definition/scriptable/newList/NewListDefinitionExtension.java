package pacman.definition.scriptable.newList;

import pacman.definition.ActivityDefinition;
import pacman.definition.NewListDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.DefinitionExtension;

public class NewListDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "NewList";
	}
	
	public String getAdapterName() {
		return NewListDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return NewListDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return args == null ? new NewListDefinition() : new NewListDefinition((String) args[0]);
	}
	
}
