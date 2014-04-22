package com.taobao.top.pacman.definition;

import java.util.List;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.FunctionObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import com.taobao.top.pacman.definition.scriptable.DefinitionExtension;
import com.taobao.top.pacman.definition.scriptable.FunctionExtension;
import com.taobao.top.pacman.definition.scriptable.Functions;
import com.taobao.top.pacman.definition.scriptable.WorkflowDefinitionAdapter;
import com.taobao.top.pacman.definition.scriptable.If.ElseDefinitionExtension;
import com.taobao.top.pacman.definition.scriptable.If.IfDefinitionExtension;
import com.taobao.top.pacman.definition.scriptable.If.ThenDefinitionExtension;
import com.taobao.top.pacman.definition.scriptable.While.WhileBodyDefinitionExtension;
import com.taobao.top.pacman.definition.scriptable.While.WhileDefinitionExtension;
import com.taobao.top.pacman.definition.scriptable.assign.AssignDefinitionExtension;
import com.taobao.top.pacman.definition.scriptable.sequence.SequenceDefinitionExtension;
import com.taobao.top.pacman.definition.scriptable.tryCatch.CatchDefinitionExtension;
import com.taobao.top.pacman.definition.scriptable.tryCatch.FinallyDefinitionExtension;
import com.taobao.top.pacman.definition.scriptable.tryCatch.TryCatchDefinitionExtension;
import com.taobao.top.pacman.definition.scriptable.tryCatch.TryDefinitionExtension;
import com.taobao.top.pacman.definition.scriptable.writeLine.WriteLineDefinitionExtension;

public class Converter {
	private final static String SOURCE_NAME = "DSL";

	private ScriptableObject publicScope;
	private String root = "Layout";

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
							"	this.addActivity(act); " +
							"	return new %s(act, this, '%s'); " +
							"}",
							extension.getName(),
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
	}
}
