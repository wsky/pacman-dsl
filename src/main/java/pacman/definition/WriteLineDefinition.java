package pacman.definition;

import pacman.Activity;
import pacman.statements.WriteLine;

public class WriteLineDefinition extends ActivityDefinition {
	private InArgumentDefinition text;
	
	public WriteLineDefinition() {
		this("WriteLine");
	}
	
	public WriteLineDefinition(String displayName) {
		this(displayName, null);
	}
	
	public WriteLineDefinition(String displayName, ActivityDefinition parent) {
		super(displayName, parent);
	}
	
	public WriteLineDefinition Text(Object input) {
		return this.Text(DefinitionUtilities.parseInArgument(input));
	}
	
	public WriteLineDefinition Text(InArgumentDefinition text) {
		this.text = text;
		return this;
	}
	
	public WriteLineDefinition Text(VariableReferenceDefinition variable) {
		return this.Text(new InArgumentDefinition(variable));
	}
	
	@Override
	protected Activity internalToActivity(DefinitionValidator validator) {
		if (this.text == null)
			validator.addError("Text not set");
		if (validator.hasError())
			return null;
		
		WriteLine writeLine = new WriteLine();
		writeLine.Text = this.text.toArgument(this.getParent(), validator);
		return writeLine;
	}
}
