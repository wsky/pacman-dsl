package pacman.definition.scriptable;

import pacman.definition.ActivityDefinition;

public interface DefinitionExtension {
	public String getName();

	public String getAdapterName();

	public Class<? extends DefinitionAdapter<?>> getAdapterType();

	public ActivityDefinition create(Object... args);
}