package pacman.definition.scriptable.existsIn;

import pacman.definition.ExistsInDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.ScriptableUtil;

public class ExistsInDefinitionAdapter extends DefinitionAdapter<ExistsInDefinition> {
	private static final long serialVersionUID = -1L;
	
	public ExistsInDefinitionAdapter() {
		this(null, null, null);
	}
	
	public ExistsInDefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
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
	
	public ExistsInDefinitionAdapter jsFunction_List(Object value) {
		this.setValue("List", value);
		return this;
	}
	
	public ExistsInDefinitionAdapter jsFunction_Map(Object value) {
		this.setValue("Map", value);
		return this;
	}
	
	public ExistsInDefinitionAdapter jsFunction_Item(Object value) {
		this.setValue("Item", value);
		return this;
	}
	
	public ExistsInDefinitionAdapter jsFunction_Key(Object value) {
		this.setValue("Key", value);
		return this;
	}
	
	public ExistsInDefinitionAdapter jsFunction_Result(Object value) {
		this.setValue("Result", value);
		return this;
	}
}
