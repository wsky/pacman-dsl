package pacman.definition.scriptable.While;

import pacman.definition.ActivityDefinition;
import pacman.definition.ReferenceActivityDefinition;
import pacman.definition.WhileDefinition;
import pacman.definition.scriptable.DefinitionAdapter;

public class WhileDefinitionAdapter extends DefinitionAdapter<WhileDefinition> {
	private static final long serialVersionUID = -6601271696933772792L;

	public WhileDefinitionAdapter() {
		this(null, null, null);
	}

	public WhileDefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
		super(definition, parent, extensionName);
	}

	@Override
	public DefinitionAdapter<?> jsFunction_End() {
		return this.end();
	}

	@Override
	public void jsFunction_addActivity(Object activity) {
		ActivityDefinition body = this.unwrap(activity, ReferenceActivityDefinition.class);
		if (body != null)
			this.getDefinition().Body(body);
	}

	public WhileDefinitionAdapter jsFunction_Condition(Object value) {
		this.setValue("Condition", value);
		return this;
	}
}
