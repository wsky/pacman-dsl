package pacman.definition;

import pacman.Activity;
import pacman.Variable;
import pacman.statements.Sequence;

public class SequenceDefinition extends ActivityDefinition {
	public SequenceDefinition() {
		this("Sequence");
	}

	public SequenceDefinition(String displayName) {
		super(displayName);
	}

	public SequenceDefinition Var(String name) {
		this.addVariable(name);
		return this;
	}

	public SequenceDefinition Activity(ActivityDefinition activity) {
		this.addActivity(activity);
		return this;
	}

	@Override
	protected Activity internalToActivity(DefinitionValidator validator) {
		Sequence sequence = new Sequence();
		for (VariableDefinition variable : this.variables) {
			Variable var = variable.toVariable();
			this.addVariable(variable.getName(), var);
			sequence.getVariables().add(var);
		}
		for (ActivityDefinition activity : this.activities)
			sequence.getActivities().add(activity.toActivity(validator));
		return sequence;
	}
}
