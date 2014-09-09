package pacman.definition;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import pacman.Activity;
import pacman.ActivityContext;
import pacman.Function;
import pacman.WorkflowInstance;
import pacman.definition.ActivityDefinition;
import pacman.definition.AssignDefinition;
import pacman.definition.DefinitionValidator;
import pacman.definition.IfDefinition;
import pacman.definition.InlinedFunctionDefinition;
import pacman.definition.SequenceDefinition;
import pacman.definition.VariableReferenceDefinition;
import pacman.definition.WhileDefinition;
import pacman.definition.WorkflowDefinition;
import pacman.definition.WriteLineDefinition;

public class WorkflowDefinitionTest {
	@Test
	public void create_test() throws Exception {
		DefinitionValidator validator = new DefinitionValidator();
		Activity workflow = new WorkflowDefinition().
				In("arg").
				In("bool").
				Out("result").
				Var("var").
				Activity(new SequenceDefinition().
						Activity(
								new AssignDefinition().
										Value(new VariableReferenceDefinition("arg")).
										To(new VariableReferenceDefinition("var"))
						).
						Activity(
								new AssignDefinition().
										Value(new VariableReferenceDefinition("var")).
										To(new VariableReferenceDefinition("result"))
						).
						Activity(
								new WhileDefinition().
										Condition(new InlinedFunctionDefinition() {
											@Override
											public Function<ActivityContext, Object> getFunction(ActivityDefinition parent, DefinitionValidator validator) {
												return new Function<ActivityContext, Object>() {
													private boolean b = false;
													
													@Override
													public Object execute(ActivityContext context) {
														return b = !b;
													}
												};
											}
										}).
										Body(new WriteLineDefinition().Text("while"))
						).
						Activity(
								new IfDefinition().
										Condition().
										Then(new WriteLineDefinition().Text(new VariableReferenceDefinition("arg"))).
										Else(new WriteLineDefinition().Text("else"))
						).
						Activity(new WriteLineDefinition().Text("end"))
				).toActivity(validator);
		assertFalse(validator.hasAnyError());
		Map<String, Object> inputs = new HashMap<String, Object>();
		inputs.put("arg", "hello");
		Map<String, Object> outputs = WorkflowInstance.invoke(workflow, inputs);
		System.out.println(outputs);
		assertEquals("hello", outputs.get("result"));
	}
	
	@Test
	public void validate_test() throws Exception {
		DefinitionValidator validator = new DefinitionValidator();
		new WorkflowDefinition().
				Activity(
						new SequenceDefinition().
								Activity(new WriteLineDefinition()).
								Activity(new IfDefinition()).
								Activity(new AssignDefinition()).
								Activity(
										new AssignDefinition().
												Value(new VariableReferenceDefinition("var")).
												To(new VariableReferenceDefinition("var")))
				).
				toActivity(validator);
		assertTrue(validator.hasAnyError());
		System.err.println(validator.getErrors());
		for (Entry<ActivityDefinition, List<String>> e : validator.getErrors().entrySet()) {
			System.err.println(e.getKey().getDisplayName());
			System.err.println(Arrays.toString(e.getValue().toArray()));
		}
	}
}
