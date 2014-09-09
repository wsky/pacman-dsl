package pacman.definition.scriptable.tryCatch;

import pacman.definition.ActivityDefinition;
import pacman.definition.TryCatchDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.DefinitionExtension;

public class TryCatchDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "TryCatch";
	}

	public String getAdapterName() {
		return TryCatchDefinitionAdapter.class.getSimpleName();
	}

	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return TryCatchDefinitionAdapter.class;
	}

	public ActivityDefinition create(Object... args) {
		return new TryCatchDefinition();
	}

}
