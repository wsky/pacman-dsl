package pacman.definition.scriptable.tryCatch;

import pacman.definition.ActivityDefinition;
import pacman.definition.ReferenceActivityDefinition;
import pacman.definition.TryCatchDefinition;
import pacman.definition.scriptable.DefinitionAdapter;

public class TryCatchDefinitionAdapter extends DefinitionAdapter<TryCatchDefinition> {
	private static final long serialVersionUID = 8032549539655047848L;

	public TryCatchDefinitionAdapter() {
		this(null, null, null);
	}

	public TryCatchDefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
		super(definition, parent, extensionName);
	}

	@Override
	public DefinitionAdapter<?> jsFunction_End() {
		return this.end();
	}

	@Override
	public void jsFunction_addActivity(Object activity) {
		ActivityDefinition act = this.unwrap(activity, ReferenceActivityDefinition.class);
		if (act == null)
			return;

		if (act.getDisplayName().equals("Try"))
			this.getDefinition().Try(act);
		else if (act.getDisplayName().equals("Catch"))
			// TODO support more exception catch?
			this.getDefinition().Catch(Exception.class, act);
		else if (act.getDisplayName().equals("Finally"))
			this.getDefinition().Finally(act);
	}

}
