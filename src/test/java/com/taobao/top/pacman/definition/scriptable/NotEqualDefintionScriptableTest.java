package com.taobao.top.pacman.definition.scriptable;

import static org.junit.Assert.*;

import java.util.Map;

import com.taobao.top.pacman.testsuite.DefinitionScriptableTestBase;

public class NotEqualDefintionScriptableTest extends DefinitionScriptableTestBase {
	@Override
	protected String createDefinition() {
		return "Workflow."
				+ "Out('result1').Out('result2')."
				+ "Sequence()."
				+ "    NotEqual().Left('hi').Right('hi').Result(Var('result1')).End()."
				+ "    Assign().Value(NotEqual().Left('hi1').Right('hi2')).To(Var('result2')).End()."
				+ "End()";
	}
	
	@Override
	protected Map<String, Object> createInputs() {
		return null;
	}
	
	@Override
	protected void assertOutputs(Map<String, Object> outputs) {
		assertFalse((Boolean) outputs.get("result1"));
		assertTrue((Boolean) outputs.get("result2"));
	}
}
