pacman-dsl
==========

Extensible DSL for [pacman](https://github.com/wsky/pacman) workflow.

```js
var wf = Workflow.
    In('arg1').
    In('arg2').
    In('arg3').
    Out('result').
    Var('temp').
    Sequence().
        Assign().From(Var('arg1')).To(Var('temp')).End().
        If().Condition(Var('temp')).
            Then().
                Assign().Value(Var('arg1')).To(Var('Result')).End().
            End().
            Else().
                Assign().Value(Var('arg2')).To(Var('Result')).End().
            End().
        End().
        WriteLine().Text(Var('result')).End().
    End().
End();
```

## How to

```xml
<dependency>
    <groupId>com.taobao.top</groupId>
    <artifactId>pacman-dsl</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

```java
Map<String, Object> inputs = new HashMap<String, Object>();
inputs.put("arg1", false);
inputs.put("arg2", "hi");
inputs.put("arg3", "hello");

// invoke definition text
Map<String, Object> outputs = new WorkflowInvoker().invoke(wf, inouts);
assertEquals("hello", outputs.get("result"));

// or
Activity workflow = new DefaultLayoutInvoker().convert(wf);
Map<String, Object> outputs = new WorkflowInvoker().invoke(workflow, inputs);
assertEquals("hello", outputs.get("result"));
```

## DSL Extension

If you implement a new activity and definition, you can easily add it to DSL.

```js
Workflow.
	In('arg').
	Out('result').
	Sequence().
		NewAct().End().
		Assign().
			Value(NewFunc(Var('arg'))).
			To(Var('result')).
		End().
	End().
End();
```

## License

- pacman, Apache License Version 2.0

	https://github.com/wsky/pacman

- Rhino, Mozilla Public License Version 2.0

	https://github.com/mozilla/rhino
	
	https://github.com/mozilla/rhino/blob/master/LICENSE.txt