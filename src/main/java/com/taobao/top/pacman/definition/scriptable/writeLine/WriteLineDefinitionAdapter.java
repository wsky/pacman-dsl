package com.taobao.top.pacman.definition.scriptable.writeLine;

import com.taobao.top.pacman.definition.WriteLineDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.ScriptableUtil;

public class WriteLineDefinitionAdapter extends DefinitionAdapter<WriteLineDefinition> {
	private static final long serialVersionUID = -336015898855208272L;

	public WriteLineDefinitionAdapter() {
		this(null, null, null);
	}

	public WriteLineDefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
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

	public WriteLineDefinitionAdapter jsFunction_Text(Object value) {
		this.setValue("Text", value);
		return this;
	}
}
