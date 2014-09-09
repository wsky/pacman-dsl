package pacman.definition.functions;

import pacman.ActivityContext;
import pacman.Function;
import pacman.Variable;
import pacman.definition.ActivityDefinition;
import pacman.definition.DefinitionValidator;
import pacman.definition.InlinedFunctionDefinition;
import pacman.definition.VariableReferenceDefinition;

public class SplitDefinition extends InlinedFunctionDefinition {
	private VariableReferenceDefinition variable;
	private String separator;
	
	public SplitDefinition(VariableReferenceDefinition variable, String separator) {
		this.variable = variable;
		this.separator = separator;
	}
	
	@Override
	public Function<ActivityContext, Object> getFunction(ActivityDefinition parent, DefinitionValidator validator) {
		if (this.separator == null)
			validator.addError("separator not set");
		
		final Variable variable = this.variable.toVariable(parent, validator);
		
		return new Function<ActivityContext, Object>() {
			@Override
			public Object execute(ActivityContext context) {
				Object value = variable.get(context);
				return value != null ? value.toString().split(separator) : "";
			}
		};
	}
}
