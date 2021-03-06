package pacman.statements;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import pacman.Activity;
import pacman.InArgument;
import pacman.OutArgument;
import pacman.Variable;
import pacman.WorkflowExtensionManager;
import pacman.extensions.ScriptInvoker;
import pacman.statements.Script;
import pacman.testsuite.StatementTestBase;

public class ScriptTest extends StatementTestBase {
	@Override
	protected Activity createActivity() {
		Script script = new Script();
		script.Source = new InArgument();
		script.getVariables().add(new Variable("var1", "hi"));
		script.getVariables().add(new Variable("var2", 123));
		script.setResult(new OutArgument());
		return script;
	}
	
	@Override
	protected Map<String, Object> createInputs() {
		Map<String, Object> inputs = new HashMap<String, Object>();
		inputs.put("Source", "return var1 + var2");
		return inputs;
	}
	
	@Override
	protected WorkflowExtensionManager createExtensionManager() {
		WorkflowExtensionManager extensionManager = new WorkflowExtensionManager();
		extensionManager.addExtension(ScriptInvoker.class, new ScriptInvoker() {
			@Override
			public Object invoke(String source, Map<String, Object> arguments) {
				return arguments.get("var1") + "" + arguments.get("var2");
			}
		});
		return extensionManager;
	}
	
	@Override
	protected void assertOutputs(Map<String, Object> outputs) {
		assertEquals("hi123", outputs.get("Result"));
	}
}
