package com.taobao.top.pacman.definition.scriptable.getFrom;

import com.taobao.top.pacman.definition.GetFromDefinition;
import com.taobao.top.pacman.definition.scriptable.DefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.ScriptableUtil;

public class GetFromDefinitionAdapter extends DefinitionAdapter<GetFromDefinition> {
	private static final long serialVersionUID = -1L;
	
	public GetFromDefinitionAdapter() {
		this(null, null, null);
	}
	
	public GetFromDefinitionAdapter(Object definition, DefinitionAdapter<?> parent, String extensionName) {
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
	
	public GetFromDefinitionAdapter jsFunction_List(Object value) {
		this.setValue("List", value);
		return this;
	}
	
	public GetFromDefinitionAdapter jsFunction_Map(Object value) {
		this.setValue("Map", value);
		return this;
	}
	
	public GetFromDefinitionAdapter jsFunction_Index(Object value) {
		this.setValue("Index", value);
		return this;
	}
	
	public GetFromDefinitionAdapter jsFunction_Key(Object value) {
		this.setValue("Key", value);
		return this;
	}
	
	public GetFromDefinitionAdapter jsFunction_Result(Object value) {
		this.setValue("Result", value);
		return this;
	}
}
