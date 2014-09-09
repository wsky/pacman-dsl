package pacman.definition;

import java.util.List;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.FunctionObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import pacman.definition.scriptable.DefinitionExtension;
import pacman.definition.scriptable.FunctionExtension;
import pacman.definition.scriptable.Functions;
import pacman.definition.scriptable.WorkflowDefinitionAdapter;
import pacman.definition.scriptable.If.ElseDefinitionExtension;
import pacman.definition.scriptable.If.IfDefinitionExtension;
import pacman.definition.scriptable.If.ThenDefinitionExtension;
import pacman.definition.scriptable.While.WhileBodyDefinitionExtension;
import pacman.definition.scriptable.While.WhileDefinitionExtension;
import pacman.definition.scriptable.addTo.AddToDefinitionExtension;
import pacman.definition.scriptable.assign.AssignDefinitionExtension;
import pacman.definition.scriptable.equal.EqualDefinitionExtension;
import pacman.definition.scriptable.existsIn.ExistsInDefinitionExtension;
import pacman.definition.scriptable.getFrom.GetFromDefinitionExtension;
import pacman.definition.scriptable.newList.NewListDefinitionExtension;
import pacman.definition.scriptable.newMap.NewMapDefinitionExtension;
import pacman.definition.scriptable.notEqual.NotEqualDefinitionExtension;
import pacman.definition.scriptable.removeFrom.RemoveFromDefinitionExtension;
import pacman.definition.scriptable.script.ScriptDefinitionExtension;
import pacman.definition.scriptable.sequence.SequenceDefinitionExtension;
import pacman.definition.scriptable.tryCatch.CatchDefinitionExtension;
import pacman.definition.scriptable.tryCatch.FinallyDefinitionExtension;
import pacman.definition.scriptable.tryCatch.TryCatchDefinitionExtension;
import pacman.definition.scriptable.tryCatch.TryDefinitionExtension;
import pacman.definition.scriptable.writeLine.WriteLineDefinitionExtension;

public class Converter {
	private final static String SOURCE_NAME = "DSL";
	
	private ScriptableObject publicScope;
	private String root = "Workflow";
	
	public Converter() {
		try {
			Context ctx = Context.enter();
			this.publicScope = ctx.initStandardObjects();
			this.prepareScope(this.publicScope, ctx);
		} finally {
			Context.exit();
		}
	}
	
	public void setRootObjectName(String value) {
		this.root = value;
	}
	
	public WorkflowDefinition convert(String definition) {
		try {
			Context ctx = Context.enter();
			// more about scope
			// https://developer.mozilla.org/en-US/docs/Rhino/Scopes_and_Contexts
			Scriptable scope = ctx.newObject(this.publicScope);
			
			String source = String.format(
					"var %s = new %s();\n%s",
					this.root,
					WorkflowDefinitionAdapter.class.getSimpleName(),
					definition);
			ctx.evaluateString(scope, source, SOURCE_NAME, 1, null);
			
			return ((WorkflowDefinitionAdapter) ScriptableObject.getProperty(scope, this.root)).getDefinition();
		} finally {
			Context.exit();
		}
	}
	
	public void addDefinitionExtension(DefinitionExtension extension) {
		this.addDefinitionExtension(this.publicScope, extension);
	}
	
	public void addFunctionExtension(FunctionExtension extension) {
		this.addFunctionExtension(this.publicScope, extension);
	}
	
	private void addDefinitionExtension(Scriptable scope, DefinitionExtension extension) {
		try {
			Context ctx = Context.enter();
			ScriptableObject.defineClass(scope, extension.getAdapterType());
			ScriptableObject.putProperty(scope, extension.getName(), extension);
			ctx.evaluateString(scope,
					String.format("Object.prototype.%s = function(a1, a2, a3, a4) { " +
							"	var act = %s.create(a1, a2, a3, a4); " +
							"	if(this.addActivity != undefined) {" +
							"		this.addActivity(act); " +
							"		return new %s(act, this, '%s');" +
							"	} else" +
							"		return new %s(act, null, '%s');" +
							"}",
							extension.getName(),
							extension.getName(),
							extension.getAdapterName(),
							extension.getName(),
							extension.getAdapterName(),
							extension.getName()),
					extension.getName(), 1, null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			Context.exit();
		}
	}
	
	private void addFunctionExtension(Scriptable scope, FunctionExtension extension) {
		List<FunctionObject> functions = extension.getFunctions(scope);
		if (functions != null)
			for (FunctionObject func : functions)
				ScriptableObject.putProperty(scope, func.getFunctionName(), func);
	}
	
	private void prepareScope(Scriptable scope, Context ctx) {
		try {
			ScriptableObject.defineClass(scope, WorkflowDefinitionAdapter.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		// default extensions
		
		this.addFunctionExtension(new Functions());
		
		this.addDefinitionExtension(new SequenceDefinitionExtension());
		this.addDefinitionExtension(new WriteLineDefinitionExtension());
		this.addDefinitionExtension(new AssignDefinitionExtension());
		
		// while
		this.addDefinitionExtension(new WhileDefinitionExtension());
		this.addDefinitionExtension(new WhileBodyDefinitionExtension());
		
		// if
		this.addDefinitionExtension(new IfDefinitionExtension());
		this.addDefinitionExtension(new ThenDefinitionExtension());
		this.addDefinitionExtension(new ElseDefinitionExtension());
		
		// try-catch
		this.addDefinitionExtension(new TryCatchDefinitionExtension());
		this.addDefinitionExtension(new TryDefinitionExtension());
		this.addDefinitionExtension(new CatchDefinitionExtension());
		this.addDefinitionExtension(new FinallyDefinitionExtension());
		
		// Script
		this.addDefinitionExtension(new ScriptDefinitionExtension());
		
		// Map/List
		this.addDefinitionExtension(new NewListDefinitionExtension());
		this.addDefinitionExtension(new NewMapDefinitionExtension());
		this.addDefinitionExtension(new AddToDefinitionExtension());
		this.addDefinitionExtension(new RemoveFromDefinitionExtension());
		this.addDefinitionExtension(new ExistsInDefinitionExtension());
		this.addDefinitionExtension(new GetFromDefinitionExtension());
		
		// Equal/Not
		this.addDefinitionExtension(new EqualDefinitionExtension());
		this.addDefinitionExtension(new NotEqualDefinitionExtension());
	}
}
