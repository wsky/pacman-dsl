package pacman.definition.scriptable;

import pacman.definition.WorkflowDefinition;

public class WorkflowDefinitionAdapter extends DefinitionAdapter<WorkflowDefinition> {
	private static final long serialVersionUID = -2144541527609354413L;

	public WorkflowDefinitionAdapter() {
		super(new WorkflowDefinition(), null, null);
	}

	@Override
	public void jsFunction_addActivity(Object activity) {
		this.addActivity(activity);
	}

	@Override
	public DefinitionAdapter<?> jsFunction_End() {
		return this.end();
	}

	public WorkflowDefinitionAdapter jsFunction_In(String name, Object defaultValue) {
		this.getDefinition().In(name, defaultValue);
		return this;
	}

	public WorkflowDefinitionAdapter jsFunction_Out(String name) {
		this.getDefinition().Out(name);
		return this;
	}

	public WorkflowDefinitionAdapter jsFunction_Var(String name) {
		this.getDefinition().Var(name);
		return this;
	}
}
