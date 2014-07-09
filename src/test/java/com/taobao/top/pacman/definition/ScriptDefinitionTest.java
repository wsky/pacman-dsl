package com.taobao.top.pacman.definition;

import java.util.Map;

import org.junit.Test;

import com.taobao.top.pacman.ScriptInvoker;
import com.taobao.top.pacman.WorkflowExtensionManager;
import com.taobao.top.pacman.testsuite.DefinitionTestBase;

public class ScriptDefinitionTest extends DefinitionTestBase {
	@Test
	public void script_test() {
		this.testMetadata(new ScriptDefinition("Script").
				Source("var1+var2").
				Var("var1").
				Var("var2").
				Result(new OutArgumentDefinition()));
	}
	
	@Test
	public void var_test() {
		this.testMetadata(new SequenceDefinition().
				Var("var1").
				Activity(
						new ScriptDefinition("Script").
								Source("var1+var2").
								Put(new VariableReferenceDefinition("var1")).
								Var("var2")
				));
	}
	
	@Test
	public void invoke_test() throws Exception {
		WorkflowExtensionManager extensionManager = new WorkflowExtensionManager();
		extensionManager.addExtension(ScriptInvoker.class, new ScriptInvoker() {
			@Override
			public Object invoke(String source, Map<String, Object> arguments) {
				return arguments.get("var");
			}
		});
		this.invoke(new SequenceDefinition().
				Var("var").
				Activity(
						new ScriptDefinition("Script").
								Source("var").
								Put(new VariableReferenceDefinition("var")).
								Var("var")
				), null, extensionManager);
	}
}
