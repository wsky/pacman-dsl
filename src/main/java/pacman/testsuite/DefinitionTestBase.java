package pacman.testsuite;

import static org.junit.Assert.*;

import java.util.Map;

import pacman.Activity;
import pacman.ActivityLocationReferenceEnvironment;
import pacman.ActivityUtilities;
import pacman.PacmanException;
import pacman.RenderProcessActivityCallback;
import pacman.WorkflowExtensionManager;
import pacman.WorkflowInstance;
import pacman.definition.ActivityDefinition;
import pacman.definition.DefinitionValidator;
import pacman.extensions.JavaScriptInvoker;
import pacman.extensions.ScriptInvoker;

public abstract class DefinitionTestBase {
	protected void testMetadata(ActivityDefinition definition) {
		this.testMetadata(definition, false);
	}
	
	protected void testMetadata(ActivityDefinition definition, boolean hasAnyError) {
		this.testMetadata(definition, hasAnyError, new DefinitionValidator());
	}
	
	protected void testMetadata(ActivityDefinition definition, boolean hasAnyError, DefinitionValidator validator) {
		Activity activity = definition.toActivity(validator);
		if (validator.hasAnyError())
			System.err.println(validator.getErrors());
		assertEquals(hasAnyError, validator.hasAnyError());
		if (hasAnyError)
			return;
		ActivityLocationReferenceEnvironment hostEnvironment = new ActivityLocationReferenceEnvironment(null);
		try {
			ActivityUtilities.cacheRootMetadata(activity, hostEnvironment, new RenderProcessActivityCallback());
		} catch (Exception e) {
			throw new PacmanException(e);
		}
	}
	
	protected Map<String, Object> invoke(
			ActivityDefinition definition,
			Map<String, Object> inputs) throws Exception {
		return this.invoke(definition, inputs, this.createExtensionManager());
	}
	
	protected Map<String, Object> invoke(
			ActivityDefinition definition,
			Map<String, Object> inputs,
			WorkflowExtensionManager extensionManager) throws Exception {
		DefinitionValidator validator = new DefinitionValidator();
		Activity activity = definition.toActivity(validator);
		if (validator.hasAnyError()) {
			System.err.println(validator.getErrors());
			fail();
		}
		
		Map<String, Object> outputs = WorkflowInstance.invoke(activity, inputs, extensionManager);
		
		if (outputs != null && outputs.get("exception") != null) {
			((Exception) outputs.get("exception")).printStackTrace();
			fail();
		}
		
		System.out.println(outputs);
		return outputs;
	}
	
	protected WorkflowExtensionManager createExtensionManager() {
		WorkflowExtensionManager extensionManager = new WorkflowExtensionManager();
		extensionManager.addExtension(ScriptInvoker.class, new JavaScriptInvoker());
		return extensionManager;
	}
}
