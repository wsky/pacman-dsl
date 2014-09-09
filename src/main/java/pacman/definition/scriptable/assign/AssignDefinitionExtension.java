package pacman.definition.scriptable.assign;

import pacman.definition.ActivityDefinition;
import pacman.definition.AssignDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.DefinitionExtension;

public class AssignDefinitionExtension implements DefinitionExtension{
	public String getName() {
		return "Assign";
	}

	public String getAdapterName() {
		return AssignDefinitionAdapter.class.getSimpleName();
	}

	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return AssignDefinitionAdapter.class;
	}

	public ActivityDefinition create(Object... args) {
		return new AssignDefinition();
	}
}
