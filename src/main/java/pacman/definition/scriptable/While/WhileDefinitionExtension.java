package pacman.definition.scriptable.While;

import pacman.definition.ActivityDefinition;
import pacman.definition.WhileDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.DefinitionExtension;

public class WhileDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "While";
	}

	public String getAdapterName() {
		return WhileDefinitionAdapter.class.getSimpleName();
	}

	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return WhileDefinitionAdapter.class;
	}

	public ActivityDefinition create(Object... args) {
		return new WhileDefinition();
	}
}
