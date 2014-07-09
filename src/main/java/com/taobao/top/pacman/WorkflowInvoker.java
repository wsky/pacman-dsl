package com.taobao.top.pacman;

import java.util.Map;

import com.taobao.top.pacman.definition.Converter;
import com.taobao.top.pacman.definition.DefinitionValidator;
import com.taobao.top.pacman.definition.WorkflowDefinition;
import com.taobao.top.pacman.extensions.JavaScriptInvoker;
import com.taobao.top.pacman.extensions.ScriptInvoker;

// simple impl
public class WorkflowInvoker {
	private Converter converter;
	private WorkflowExtensionManager extensionManager;
	
	public WorkflowInvoker() {
		this.setConvert(new Converter());
		this.setExtensionManager(new WorkflowExtensionManager());
		this.extensionManager.addExtension(ScriptInvoker.class, new JavaScriptInvoker());
	}
	
	public void setConvert(Converter converter) {
		this.converter = converter;
	}
	
	public void setExtensionManager(WorkflowExtensionManager extensionManager) {
		this.extensionManager = extensionManager;
	}
	
	public Map<String, Object> invoke(String definition, Map<String, Object> inputs) throws Exception {
		return this.invoke(this.convert(definition), inputs);
	}
	
	public Map<String, Object> invoke(Activity workflow, Map<String, Object> inputs) throws Exception {
		Map<String, Object> outputs = WorkflowInstance.invoke(workflow, inputs, this.extensionManager);
		
		Object error = outputs.get(WorkflowDefinition.EXCEPTION);
		if (error instanceof Exception)
			throw (Exception) error;
		
		return outputs;
	}
	
	public Activity convert(String definition) throws Exception {
		return this.convert(definition, new DefinitionValidator());
	}
	
	public Activity convert(String definition, DefinitionValidator validator) throws Exception {
		Activity workflow = this.converter.
				convert(definition).
				toActivity(validator);
		
		if (validator.hasAnyError())
			throw new Exception(validator.getErrors().toString());
		
		return workflow;
	}
}
