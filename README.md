pacman-dsl
==========

Extensible DSL for [pacman](https://github.com/wsky/pacman) workflow.

```js
var wf = Workflow.
    In('arg1').
    In('arg2').
    Out('result').
    Var('temp').
    Sequence().
        Assign().From(Var('arg1')).To(Var('temp').End().
        If().Condition(Var('temp').
            Then().
                Assign().Value(Var('arg1').To(Var('Result')).End().
            End().
            Else().
                Assign().Value(Var('arg2').To(Var('Result')).End().
            End().
        End().
        WriteLine().Text('end').End().
    End().
End();
```

## DSL Extension

If you implement a new activity and definition, you can easily add it to DSL.

```js
Layout.
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