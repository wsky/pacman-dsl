package pacman.definition.scriptable.sequence;

import pacman.definition.ActivityDefinition;
import pacman.definition.SequenceDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.DefinitionExtension;

public class SequenceDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "Sequence";
	}

	public String getAdapterName() {
		return SequenceDefinitionAdapter.class.getSimpleName();
	}

	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return SequenceDefinitionAdapter.class;
	}

	public ActivityDefinition create(Object... args) {
		return new SequenceDefinition();
	}

}
