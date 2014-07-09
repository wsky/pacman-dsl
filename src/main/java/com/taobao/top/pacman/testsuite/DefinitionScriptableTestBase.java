package com.taobao.top.pacman.testsuite;

import java.util.Map;

import org.junit.Test;

import com.taobao.top.pacman.definition.Converter;

public abstract class DefinitionScriptableTestBase extends DefinitionTestBase {
	private Converter converter = new Converter();
	
	protected void testMetadata(String definition) {
		this.testMetadata(this.converter.convert(definition));
	}
	
	protected Map<String, Object> invoke(
			String definition,
			Map<String, Object> inputs) throws Exception {
		return this.invoke(this.converter.convert(definition), inputs);
	}
	
	@Test
	public void invoke_test() throws Exception {
		this.assertOutputs(this.invoke(
				this.createDefinition(),
				this.createInputs()));
	}
	
	protected abstract String createDefinition();
	
	protected abstract Map<String, Object> createInputs();
	
	protected abstract void assertOutputs(Map<String, Object> outputs);
}
