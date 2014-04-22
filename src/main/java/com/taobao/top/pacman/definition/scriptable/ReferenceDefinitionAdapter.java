package com.taobao.top.pacman.definition.scriptable;

import com.taobao.top.pacman.definition.ReferenceActivityDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;

public class ReferenceDefinitionAdapter extends DefinitionAdapter<ReferenceActivityDefinition> {
	private static final long serialVersionUID = -9055510076718042976L;

	public ReferenceDefinitionAdapter() {
		this(null, null, null);
	}

	public ReferenceDefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
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
