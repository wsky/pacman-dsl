package pacman.definition.scriptable;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pacman.testsuite.DefinitionScriptableTestBase;

public class ExistsInDefinitionScriptableTest extends DefinitionScriptableTestBase {
	@Override
	protected String createDefinition() {
		return "Workflow."
				+ "In('map').In('list')."
				+ "Out('result1').Out('result2').Out('result3')."
				+ "Sequence()."
				+ "    ExistsIn().Map(Var('map')).Key('key').Result(Var('result1')).End()."
				+ "    ExistsIn().List(Var('list')).Item('item').Result(Var('result2')).End()."
				+ "    Assign().Value(ExistsIn().Map(Var('map')).Key('key')).To(Var('result3')).End()."
				+ "End()";
	}
	
	@Override
	protected Map<String, Object> createInputs() {
		Map<String, Object> inputs = new HashMap<String, Object>();
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("key", "value");
		inputs.put("map", map);
		
		List<Object> list = new ArrayList<Object>();
		list.add("item");
		inputs.put("list", list);
		
		return inputs;
	}
	
	@Override
	protected void assertOutputs(Map<String, Object> outputs) {
		assertEquals(true, outputs.get("result1"));
		assertEquals(true, outputs.get("result2"));
		assertEquals(true, outputs.get("result3"));
	}
}
