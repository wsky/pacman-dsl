package com.taobao.top.pacman.definition.scriptable;

import static org.junit.Assert.*;

import java.util.Map;

import com.taobao.top.pacman.testsuite.DefinitionScriptableTestBase;

public class EqualDefintionScriptableTest extends DefinitionScriptableTestBase {
	@Override
	protected String createDefinition() {
		return "Workflow."
				+ "Out('result1').Out('result2')."
				+ "Sequence()."
				+ "    Equal().Left('hi').Right('hi').Result(Var('result1')).End()."
				+ "    Assign().Value(Equal().Left('hi1').Right('hi2')).To(Var('result2')).End()."
				+ "End()";
	}
	
	@Override
	protected Map<String, Object> createInputs() {
		return null;
	}
	
	@Override
	protected void assertOutputs(Map<String, Object> outputs) {
		assertTrue((Boolean) outputs.get("result1"));
		assertFalse((Boolean) outputs.get("result2"));
	}
}
