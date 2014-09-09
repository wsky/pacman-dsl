package pacman.definition.scriptable.existsIn;

import pacman.definition.ActivityDefinition;
import pacman.definition.ExistsInDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.DefinitionExtension;
import pacman.definition.scriptable.ScriptableUtil;

public class ExistsInDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "ExistsIn";
	}
	
	public String getAdapterName() {
		return ExistsInDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return ExistsInDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return ScriptableUtil.canGetDisplayNameFromCreateArgs(args) ?
				new ExistsInDefinition((String) args[0]) :
				new ExistsInDefinition();
	}
	
}
