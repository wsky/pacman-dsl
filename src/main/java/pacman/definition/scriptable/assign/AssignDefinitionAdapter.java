package pacman.definition.scriptable.assign;

import pacman.definition.AssignDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.ScriptableUtil;

public class AssignDefinitionAdapter extends DefinitionAdapter<AssignDefinition> {
	private static final long serialVersionUID = 8350944160836998026L;
	
	public AssignDefinitionAdapter() {
		this(null, null, null);
	}
	
	public AssignDefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
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
	
	public AssignDefinitionAdapter jsFunction_Value(Object value) {
		if (value == null)
			this.getDefinition().Value((Object) null);
		else
			this.setValue("Value", value);
		return this;
	}
	
	public AssignDefinitionAdapter jsFunction_To(Object value) {
		this.setValue("To", value);
		return this;
	}
}
