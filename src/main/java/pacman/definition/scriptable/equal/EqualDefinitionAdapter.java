package pacman.definition.scriptable.equal;

import pacman.definition.EqualDefinition;
import pacman.definition.scriptable.DefinitionAdapter;
import pacman.definition.scriptable.ScriptableUtil;

public class EqualDefinitionAdapter extends DefinitionAdapter<EqualDefinition> {
	private static final long serialVersionUID = -1L;
	
	public EqualDefinitionAdapter() {
		this(null, null, null);
	}
	
	public EqualDefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
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
	
	public EqualDefinitionAdapter jsFunction_Left(Object value) {
		this.setValue("Left", value);
		return this;
	}
	
	public EqualDefinitionAdapter jsFunction_Right(Object value) {
		this.setValue("Right", value);
		return this;
	}
	
	public EqualDefinitionAdapter jsFunction_Result(Object value) {
		this.setValue("Result", value);
		return this;
	}
}
