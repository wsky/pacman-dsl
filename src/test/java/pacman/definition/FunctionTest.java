package pacman.definition;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import pacman.definition.VariableReferenceDefinition;
import pacman.definition.WorkflowDefinition;
import pacman.definition.functions.Script;
import pacman.testsuite.DefinitionTestBase;

public class FunctionTest extends DefinitionTestBase {
	@Test
	public void script_test() throws Exception {
		Map<String, Object> inputs = new HashMap<String, Object>();
		inputs.put("arg1", true);
		inputs.put("arg2", "prefix");
		Map<String, Object> outputs = this.invoke(
				new WorkflowDefinition().
						In("arg1").
						In("arg2").
						Out("result").
						Assign().
						Value(new Script("" +
								"function(){" +
								"	if(arg1) { " +
								"		return arg2 + 'hello'; " +
								"	} else { " +
								"		return arg2 + 'world';" +
								"	}" +
								"}",
								new VariableReferenceDefinition("arg1"),
								new VariableReferenceDefinition("arg2"))).
						To(new VariableReferenceDefinition("result")).
						End(), inputs);
		assertEquals("prefixhello", outputs.get("result"));
	}
}
