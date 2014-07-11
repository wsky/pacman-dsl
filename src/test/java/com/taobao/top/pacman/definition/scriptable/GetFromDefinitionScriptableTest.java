package com.taobao.top.pacman.definition.scriptable;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import com.taobao.top.pacman.testsuite.DefinitionScriptableTestBase;

public class GetFromDefinitionScriptableTest extends DefinitionScriptableTestBase {
	@Override
	protected String createDefinition() {
		return "Workflow."
				+ "In('map')."
				+ "Out('result1').Out('result2')."
				+ "Sequence()."
				+ "    GetFrom().Map(Var('map')).Key('key').Result(Var('result1')).End()."
				+ "    Assign().Value(GetFrom().Map(Var('map')).Key('key')).To(Var('result2')).End()."
				+ "End()";
	}
	
	@Override
	protected Map<String, Object> createInputs() {
		Map<String, Object> inputs = new HashMap<String, Object>();
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("key", "value");
		inputs.put("map", map);
		
		return inputs;
	}
	
	@Override
	protected void assertOutputs(Map<String, Object> outputs) {
		assertEquals("value", outputs.get("result1"));
		assertEquals("value", outputs.get("result2"));
	}
}
