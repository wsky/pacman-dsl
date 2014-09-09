package pacman;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import pacman.WorkflowInvoker;

public class WorkflowInvokerTest {
	@Test
	public void invoke_test() throws Exception {
		Map<String, Object> inputs = new HashMap<String, Object>();
		inputs.put("arg1", false);
		inputs.put("arg2", "hi");
		inputs.put("arg3", "hello");

		Map<String, Object> outputs = new WorkflowInvoker().invoke("" +
				"Workflow." +
				"	In('arg1')." +
				"	In('arg2')." +
				"	In('arg3')." +
				"	Out('result')." +
				"	Var('temp')." +
				"	Sequence()." +
				"		Assign().Value(Var('arg1')).To(Var('temp')).End()." +
				"		WriteLine().Text(Var('temp')).End()." +
				"		If()." +
				"			Condition(Var('temp'))." +
				"		Then()." +
				"			Assign().Value(Var('arg2')).To(Var('result')).End()." +
				"		End()." +
				"		Else()." +
				"			Assign().Value(Var('arg3')).To(Var('result')).End().End()." +
				"		End()." +
				"		WriteLine().Text(Var('result')).End()." +
				"	End()." +
				"End();",
				inputs);

		assertEquals("hello", outputs.get("result"));
	}
}
