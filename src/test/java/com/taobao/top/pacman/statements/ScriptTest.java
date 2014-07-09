package com.taobao.top.pacman.statements;

import java.util.HashMap;
import java.util.Map;

import com.taobao.top.pacman.Activity;
import com.taobao.top.pacman.InArgument;
import com.taobao.top.pacman.OutArgument;
import com.taobao.top.pacman.ScriptInvoker;
import com.taobao.top.pacman.Variable;
import com.taobao.top.pacman.WorkflowExtensionManager;

public class ScriptTest extends StatementTestBase {
	@Override
	protected Activity createActivity() {
		Script script = new Script();
		script.Source = new InArgument();
		script.getVariables().add(new Variable("var1", "hi"));
		script.getVariables().add(new Variable("var2", 123));
		script.Result = new OutArgument();
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
}
