package pacman.definition.scriptable.addTo;

import pacman.definition.ActivityDefinition;
import pacman.definition.AddToDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.DefinitionExtension;
import pacman.definition.scriptable.ScriptableUtil;

public class AddToDefinitionExtension implements DefinitionExtension {
	public String getName() {
		return "AddTo";
	}
	
	public String getAdapterName() {
		return AddToDefinitionAdapter.class.getSimpleName();
	}
	
	public Class<? extends DefinitionAdapter<?>> getAdapterType() {
		return AddToDefinitionAdapter.class;
	}
	
	public ActivityDefinition create(Object... args) {
		return ScriptableUtil.canGetDisplayNameFromCreateArgs(args) ?
				new AddToDefinition((String) args[0]) :
				new AddToDefinition();
	}
	
}
