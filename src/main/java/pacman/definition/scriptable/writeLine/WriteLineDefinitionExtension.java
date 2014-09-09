package pacman.definition.scriptable.writeLine;

import pacman.definition.ActivityDefinition;
import pacman.definition.WriteLineDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.DefinitionExtension;

public class WriteLineDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "WriteLine";
	}

	public String getAdapterName() {
		return WriteLineDefinitionAdapter.class.getSimpleName();
	}

	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return WriteLineDefinitionAdapter.class;
	}

	public ActivityDefinition create(Object... args) {
		return new WriteLineDefinition();
	}

}
