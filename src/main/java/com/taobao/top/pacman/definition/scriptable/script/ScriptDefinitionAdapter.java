package com.taobao.top.pacman.definition.scriptable.script;

import com.taobao.top.pacman.definition.ScriptDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.ScriptableUtil;

public class ScriptDefinitionAdapter extends DefinitionAdapter<ScriptDefinition> {
	private static final long serialVersionUID = 7845685086108309655L;
	
	public ScriptDefinitionAdapter() {
		this(null, null, null);
	}
	
	public ScriptDefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
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
	
	public ScriptDefinitionAdapter jsFunction_Source(String value) {
		this.setValue("Source", value);
		return this;
	}
	
	public ScriptDefinitionAdapter jsFunction_Put(Object value) {
		this.setValue("Put", value);
		return this;
	}
	
	public ScriptDefinitionAdapter jsFunction_Result(Object value) {
		this.setValue("Result", value);
		return this;
	}
}
