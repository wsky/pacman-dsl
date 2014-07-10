package com.taobao.top.pacman.definition.scriptable.newList;

import com.taobao.top.pacman.definition.NewListDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.ScriptableUtil;

public class NewListDefinitionAdapter extends DefinitionAdapter<NewListDefinition> {
	private static final long serialVersionUID = -1L;
	
	public NewListDefinitionAdapter() {
		this(null, null, null);
	}
	
	public NewListDefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
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

	public NewListDefinitionAdapter jsFunction_Result(Object value) {
		this.setValue("Result", value);
		return this;
	}
}
