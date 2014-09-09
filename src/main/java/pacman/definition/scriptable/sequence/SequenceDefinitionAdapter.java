package pacman.definition.scriptable.sequence;

import pacman.definition.SequenceDefinition;
import pacman.definition.scriptable.DefinitionAdapter;

public class SequenceDefinitionAdapter extends DefinitionAdapter<SequenceDefinition> {
	private static final long serialVersionUID = -6647255998084890393L;

	public SequenceDefinitionAdapter() {
		this(null, null, null);
	}

	public SequenceDefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
		super(definition, parent, extensionName);
	}

	@Override
	public DefinitionAdapter<?> jsFunction_End() {
		return this.end();
	}

	@Override
	public void jsFunction_addActivity(Object activity) {
		this.addActivity(activity);
	}
}
