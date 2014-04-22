package com.taobao.top.pacman.definition.scriptable.If;

import com.taobao.top.pacman.definition.ActivityDefinition;
import com.taobao.top.pacman.definition.IfDefinition;
import com.taobao.top.pacman.definition.ReferenceActivityDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;

public class IfDefinitionAdapter extends DefinitionAdapter<IfDefinition> {
	private static final long serialVersionUID = -626279900011726176L;

	public IfDefinitionAdapter() {
		this(null, null, null);
	}

	public IfDefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
		super(definition, parent, extensionName);
	}

	@Override
	public void jsFunction_addActivity(Object activity) {
		ActivityDefinition thenOrElse = this.unwrap(activity, ReferenceActivityDefinition.class);
		if (thenOrElse == null)
			return;
		if (thenOrElse.getDisplayName().equals("Then"))
			this.getDefinition().Then(thenOrElse);
		else if (thenOrElse.getDisplayName().equals("Else"))
			this.getDefinition().Else(thenOrElse);
	}

	@Override
	public DefinitionAdapter<?> jsFunction_End() {
		return this.end();
	}

	public IfDefinitionAdapter jsFunction_Condition(Object value) {
		this.setValue("Condition", value);
		return this;
	}

}
