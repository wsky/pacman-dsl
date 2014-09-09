package pacman.definition.scriptable.script;

import pacman.definition.ActivityDefinition;
import pacman.definition.ScriptDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.DefinitionExtension;

public class ScriptDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "Scripting";
	}
	
	public String getAdapterName() {
		return ScriptDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return ScriptDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return args == null ? new ScriptDefinition() : new ScriptDefinition((String) args[0]);
	}
	
}
