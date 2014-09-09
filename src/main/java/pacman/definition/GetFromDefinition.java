package pacman.definition;

import pacman.ActivityWithResult;
import pacman.Asserter;
import pacman.statements.GetFromMap;

public class GetFromDefinition extends ActivityWithResultDefinition {
	private InArgumentDefinition to;
	private InArgumentDefinition index;
	private InArgumentDefinition key;
	private boolean isMap;
	
	public GetFromDefinition() {
		this("GetFrom");
	}
	
	public GetFromDefinition(String displayName) {
		super(displayName);
	}
	
	public GetFromDefinition List(Object input) {
		this.to = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	public GetFromDefinition Map(Object input) {
		this.isMap = true;
		this.to = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	public GetFromDefinition Index(Object input) {
		this.index = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	public GetFromDefinition Key(Object input) {
		this.key = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	@Override
	public ActivityWithResult internalToActivityWithResult(ActivityDefinition parent, DefinitionValidator validator) {
		if (this.to == null)
			validator.addError("Map or List not set");
		if (this.isMap && this.key == null)
			validator.addError("Key not set");
		if (!this.isMap && this.index == null)
			validator.addError("Index not set");
		if (validator.hasError())
			return null;
		
		if (this.isMap) {
			GetFromMap map = new GetFromMap();
			map.Map = this.to.toArgument(parent, validator);
			map.Key = this.key.toArgument(parent, validator);
			return map;
		}
		
		throw Asserter.shouldNotReachHere();
		// RemoveFromCollection collection = new RemoveFromCollection();
		// collection.Collection = this.to.toArgument(this.getParent(), validator);
		// collection.Item = this.index.toArgument(this.getParent(), validator);
		// return collection;
	}
}
