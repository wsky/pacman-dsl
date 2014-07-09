package com.taobao.top.pacman.definition.scriptable;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.taobao.top.pacman.testsuite.DefinitionScriptableTestBase;

public class AssignTest extends DefinitionScriptableTestBase {
	@Override
	protected String createDefinition() {
		return "Workflow.In('arg').Out('result').Assign().Value(Var('arg')).To(Var('result')).End()";
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
	
	@Test
	public void assign_null_test() throws Exception {
		Map<String, Object> outputs = this.invoke(
				"Workflow.Out('result').Assign().Value(null).To(Var('result')).End()", null);
		assertTrue(outputs.containsKey("result"));
		assertNull(outputs.get("result"));
	}
}
