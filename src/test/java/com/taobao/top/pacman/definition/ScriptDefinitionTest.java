package com.taobao.top.pacman.definition;

import org.junit.Test;

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
								Var(new VariableReferenceDefinition("var1")).
								Var("var2")
				));
	}
}
