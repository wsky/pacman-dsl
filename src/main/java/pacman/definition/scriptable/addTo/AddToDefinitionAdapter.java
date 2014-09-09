package pacman.definition.scriptable.addTo;

import pacman.definition.AddToDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.ScriptableUtil;

public class AddToDefinitionAdapter extends DefinitionAdapter<AddToDefinition> {
	private static final long serialVersionUID = -1L;
	
	public AddToDefinitionAdapter() {
		this(null, null, null);
	}
	
	public AddToDefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
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
	
	public AddToDefinitionAdapter jsFunction_List(Object value) {
		this.setValue("List", value);
		return this;
	}
	
	public AddToDefinitionAdapter jsFunction_Map(Object value) {
		this.setValue("Map", value);
		return this;
	}
	
	public AddToDefinitionAdapter jsFunction_Item(Object value) {
		this.setValue("Item", value);
		return this;
	}
	
	public AddToDefinitionAdapter jsFunction_Key(Object value) {
		this.setValue("Key", value);
		return this;
	}
	
	public AddToDefinitionAdapter jsFunction_Value(Object value) {
		this.setValue("Value", value);
		return this;
	}
}
