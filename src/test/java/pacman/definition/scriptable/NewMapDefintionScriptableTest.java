package pacman.definition.scriptable;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import pacman.testsuite.DefinitionScriptableTestBase;

public class NewMapDefintionScriptableTest extends DefinitionScriptableTestBase {
	@Override
	protected String createDefinition() {
		return "Workflow."
				+ "Out('result1').Out('result2')."
				+ "Sequence()."
				+ "    NewMap().Result(Var('result1')).End()."
				+ "    Assign().Value(NewMap()).To(Var('result2')).End()."
				+ "End()";
	}
	
	@Override
	protected Map<String, Object> createInputs() {
		return null;
	}
	
	@Override
	protected void assertOutputs(Map<String, Object> outputs) {
		assertEquals(HashMap.class, outputs.get("result1").getClass());
		assertEquals(HashMap.class, outputs.get("result2").getClass());
	}
}
