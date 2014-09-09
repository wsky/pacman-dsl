package pacman.definition.scriptable.equal;

import pacman.definition.ActivityDefinition;
import pacman.definition.EqualDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.DefinitionExtension;
import pacman.definition.scriptable.ScriptableUtil;

public class EqualDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "Equal";
	}
	
	public String getAdapterName() {
		return EqualDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return EqualDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return ScriptableUtil.canGetDisplayNameFromCreateArgs(args) ?
				new EqualDefinition((String) args[0]) :
				new EqualDefinition();
	}
}
