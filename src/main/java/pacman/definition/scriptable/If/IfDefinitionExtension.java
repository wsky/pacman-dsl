package pacman.definition.scriptable.If;

import pacman.definition.ActivityDefinition;
import pacman.definition.IfDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.DefinitionExtension;

public class IfDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "If";
	}

	public String getAdapterName() {
		return IfDefinitionAdapter.class.getSimpleName();
	}

	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return IfDefinitionAdapter.class;
	}

	public ActivityDefinition create(Object... args) {
		return new IfDefinition();
	}
}
