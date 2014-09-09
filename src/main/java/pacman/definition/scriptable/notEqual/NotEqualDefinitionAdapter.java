package pacman.definition.scriptable.notEqual;

import pacman.definition.NotEqualDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.ScriptableUtil;

public class NotEqualDefinitionAdapter extends DefinitionAdapter<NotEqualDefinition> {
	private static final long serialVersionUID = -1L;
	
	public NotEqualDefinitionAdapter() {
		this(null, null, null);
	}
	
	public NotEqualDefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
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
	
	public NotEqualDefinitionAdapter jsFunction_Left(Object value) {
		this.setValue("Left", value);
		return this;
	}
	
	public NotEqualDefinitionAdapter jsFunction_Right(Object value) {
		this.setValue("Right", value);
		return this;
	}
	
	public NotEqualDefinitionAdapter jsFunction_Result(Object value) {
		this.setValue("Result", value);
		return this;
	}
}
