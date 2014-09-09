package pacman.definition.scriptable.removeFrom;

import pacman.definition.RemoveFromDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.ScriptableUtil;

public class RemoveFromDefinitionAdapter extends DefinitionAdapter<RemoveFromDefinition> {
	private static final long serialVersionUID = -1L;
	
	public RemoveFromDefinitionAdapter() {
		this(null, null, null);
	}
	
	public RemoveFromDefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
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
	
	public RemoveFromDefinitionAdapter jsFunction_List(Object value) {
		this.setValue("List", value);
		return this;
	}
	
	public RemoveFromDefinitionAdapter jsFunction_Map(Object value) {
		this.setValue("Map", value);
		return this;
	}
	
	public RemoveFromDefinitionAdapter jsFunction_Item(Object value) {
		this.setValue("Item", value);
		return this;
	}
	
	public RemoveFromDefinitionAdapter jsFunction_Key(Object value) {
		this.setValue("Key", value);
		return this;
	}
	
	public RemoveFromDefinitionAdapter jsFunction_Result(Object value) {
		this.setValue("Result", value);
		return this;
	}
}
