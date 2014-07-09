package com.taobao.top.pacman.definition.scriptable;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import com.taobao.top.pacman.WorkflowExtensionManager;
import com.taobao.top.pacman.extensions.JavaScriptInvoker;
import com.taobao.top.pacman.extensions.ScriptInvoker;
import com.taobao.top.pacman.testsuite.DefinitionScriptableTestBase;

public class ScriptDefinitionScriptableTest extends DefinitionScriptableTestBase {
	@Override
	protected String createDefinition() {
		return "function func(){ return arg; }"
				+ "Workflow."
				+ "In('arg')."
				+ "Out('result')."
				+ "Script()."
				+ "    Source(func)."
				+ "    Put(Var('arg'))."
				+ "    Result(Var('result'))";
	}
	
	@Override
	protected Map<String, Object> createInputs() {
		Map<String, Object> inputs = new HashMap<String, Object>();
		inputs.put("arg", "hi");
		return inputs;
	}
	
	@Override
	protected void assertOutputs(Map<String, Object> outputs) {
		assertEquals("hi", outputs.get("result"));
	}
	
	@Override
	protected WorkflowExtensionManager createExtensionManager() {
		WorkflowExtensionManager extensionManager = new WorkflowExtensionManager();
		extensionManager.addExtension(ScriptInvoker.class, new JavaScriptInvoker());
		return extensionManager;
	}
}
