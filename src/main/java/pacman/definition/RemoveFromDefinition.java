package pacman.definition;

import pacman.ActivityWithResult;
import pacman.statements.RemoveFromCollection;
import pacman.statements.RemoveFromMap;

public class RemoveFromDefinition extends ActivityWithResultDefinition {
	private InArgumentDefinition to;
	private InArgumentDefinition item;
	private InArgumentDefinition key;
	private boolean isMap;
	
	public RemoveFromDefinition() {
		this("RemoveFrom");
	}
	
	public RemoveFromDefinition(String displayName) {
		super(displayName);
	}
	
	public RemoveFromDefinition List(Object input) {
		this.to = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	public RemoveFromDefinition Map(Object input) {
		this.isMap = true;
		this.to = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	public RemoveFromDefinition Item(Object input) {
		this.item = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	public RemoveFromDefinition Key(Object input) {
		this.key = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	@Override
	public ActivityWithResult internalToActivityWithResult(ActivityDefinition parent, DefinitionValidator validator) {
		if (this.to == null)
			validator.addError("Map or List not set");
		if (this.isMap && this.key == null)
			validator.addError("Key not set");
		if (!this.isMap && this.item == null)
			validator.addError("Item not set");
		if (validator.hasError())
			return null;
		
		if (this.isMap) {
			RemoveFromMap map = new RemoveFromMap();
			map.Map = this.to.toArgument(parent, validator);
			map.Key = this.key.toArgument(parent, validator);
			return map;
		}
		
		RemoveFromCollection collection = new RemoveFromCollection();
		collection.Collection = this.to.toArgument(parent, validator);
		collection.Item = this.item.toArgument(parent, validator);
		return collection;
	}
	
}
