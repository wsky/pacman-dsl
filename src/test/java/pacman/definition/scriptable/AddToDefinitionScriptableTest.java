package pacman.definition.scriptable;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pacman.testsuite.DefinitionScriptableTestBase;

public class AddToDefinitionScriptableTest extends DefinitionScriptableTestBase {
	@Override
	protected String createDefinition() {
		return "Workflow."
				+ "In('map').In('list')."
				+ "Out('map').Out('list')."
				+ "Sequence()."
				+ "    AddTo().Map(Var('map')).Key('key').Value('value').End()."
				+ "    AddTo().List(Var('list')).Item('item').End()."
				+ "End()";
	}
	
	@Override
	protected Map<String, Object> createInputs() {
		Map<String, Object> inputs = new HashMap<String, Object>();
		inputs.put("map", new HashMap<Object, Object>());
		inputs.put("list", new ArrayList<Object>());
		return inputs;
	}
	
	@Override
	protected void assertOutputs(Map<String, Object> outputs) {
		assertEquals("value", ((Map<?, ?>) outputs.get("map")).get("key"));
		assertEquals("item", ((List<?>) outputs.get("list")).get(0));
	}	
}
