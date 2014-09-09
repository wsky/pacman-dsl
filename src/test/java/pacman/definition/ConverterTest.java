package pacman.definition;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import pacman.Activity;
import pacman.WorkflowInvoker;
import pacman.definition.Converter;
import pacman.definition.DefinitionValidator;
import pacman.definition.WorkflowDefinition;

public class ConverterTest {
	@Test
	public void convert_test() throws Exception {
		WorkflowDefinition layout = new Converter().convert(
				"function update(){" +
						"	return temp + 'Test';" +
						"}" +
						"" +
						"Workflow." +
						"	In('arg')." +
						"	In('isThen')." +
						"	In('isWhile')." +
						"	Out('result')." +
						"	Var('temp')." +
						"	Sequence()." +
						"		WriteLine().Text('hello').End()." +
						"		WriteLine().Text('world').End()." +
						"		Assign().Value('hi').To(Var('temp')).End()." +
						"		Assign().Value(Script(update, [Var('temp')])).To(Var('result')).End()." +
						"		If().Condition(Var('isThen'))." +
						"			Then()." +
						"				WriteLine().Text('then').End()." +
						"			End()." +
						"			Else()." +
						"				WriteLine().Text('else').End()." +
						"			End()." +
						"		End()." +
						"		While().Condition(Var('isWhile'))." +
						"			Body()." +
						"				Sequence()." +
						"					WriteLine().Text('while').End()." +
						"					Assign().Value(false).To(Var('isWhile')).End()." +
						"				End()." +
						"			End()." +
						"		End()." +
						"		TryCatch()." +
						"			Try()." +
						"				WriteLine().Text('try').End()." +
						"			End()." +
						"			Catch()." +
						"				WriteLine().Text('catch').End()." +
						"			End()." +
						"			Finally()." +
						"				WriteLine().Text('finally').End()." +
						"			End()." +
						"		End()." +
						"	End()." +
						"End()");
		
		DefinitionValidator validator = new DefinitionValidator();
		
		Activity activity = layout.toActivity(validator);
		
		if (validator.hasAnyError()) {
			System.err.println(validator.getErrors());
			fail();
		}
		
		ActivityTestHelper.testMetadata(activity);
		
		Map<String, Object> inputs = new HashMap<String, Object>();
		inputs.put("arg", "test");
		inputs.put("isThen", false);
		inputs.put("isWhile", true);
		Map<String, Object> outputs = new WorkflowInvoker().invoke(
				activity,
				inputs);
		System.out.println(outputs);
		
		if (outputs.get("exception") != null) {
			((Exception) outputs.get("exception")).printStackTrace();
			fail();
		}
		assertEquals("hiTest", outputs.get("result"));
	}
}
