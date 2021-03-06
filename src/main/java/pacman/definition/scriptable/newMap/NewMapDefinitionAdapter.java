package pacman.definition.scriptable.newMap;

import pacman.definition.NewMapDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.ScriptableUtil;

public class NewMapDefinitionAdapter extends DefinitionAdapter<NewMapDefinition> {
	private static final long serialVersionUID = -1L;
	
	public NewMapDefinitionAdapter() {
		this(null, null, null);
	}
	
	public NewMapDefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
		super(definition, parent, extensionName);
	}
	
	@Override
	public DefinitionAdapter<?> jsFunction_End() {
		return this.end();
	}
	
	@Override
	public void jsFunction_addActivity(Object activity) {
		ScriptableUtil.throwNotSupportTheExtension(this, activity);
	}

	public NewMapDefinitionAdapter jsFunction_Result(Object value) {
		this.setValue("Result", value);
		return this;
	}
}
