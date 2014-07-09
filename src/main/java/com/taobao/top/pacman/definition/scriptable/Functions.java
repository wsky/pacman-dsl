package com.taobao.top.pacman.definition.scriptable;

import java.util.ArrayList;
import java.util.List;

import com.taobao.top.pacman.definition.InlinedFunctionDefinition;
import com.taobao.top.pacman.definition.VariableReferenceDefinition;
import com.taobao.top.pacman.definition.functions.Script;
import com.taobao.top.pacman.definition.functions.SplitDefinition;

public class Functions extends FunctionExtension {
	@Override
	public List<String> getMethodNames() {
		List<String> list = new ArrayList<String>();
		list.add("Var");
		list.add("Script");
		list.add("Split");
		return list;
	}
	
	public static VariableReferenceDefinition Var(String name) {
		return new VariableReferenceDefinition(name);
	}
	
	public static InlinedFunctionDefinition Script(String source, Object variables) {
		return new Script(source, unwrapArray(variables, VariableReferenceDefinition.class));
	}
	
	public static InlinedFunctionDefinition Split(Object variable, String separator) {
		return new SplitDefinition(unwrap(variable, VariableReferenceDefinition.class), separator);
	}
}
