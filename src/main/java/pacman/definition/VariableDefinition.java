package pacman.definition;

import pacman.Variable;

public class VariableDefinition {
	private String name;

	public VariableDefinition(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Variable toVariable() {
		return new Variable(this.name);
	}
}
