package pacman.definition;

import pacman.ActivityWithResult;
import pacman.statements.ExistsInCollection;
import pacman.statements.ExistsInMap;

public class ExistsInDefinition extends ActivityWithResultDefinition {
	private InArgumentDefinition to;
	private InArgumentDefinition item;
	private InArgumentDefinition key;
	private boolean isMap;
	
	public ExistsInDefinition() {
		this("ExistsIn");
	}
	
	public ExistsInDefinition(String displayName) {
		super(displayName);
	}
	
	public ExistsInDefinition List(Object input) {
		this.to = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	public ExistsInDefinition Map(Object input) {
		this.isMap = true;
		this.to = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	public ExistsInDefinition Item(Object input) {
		this.item = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	public ExistsInDefinition Key(Object input) {
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
			ExistsInMap map = new ExistsInMap();
			map.Map = this.to.toArgument(parent, validator);
			map.Key = this.key.toArgument(parent, validator);
			return map;
		}
		
		ExistsInCollection collection = new ExistsInCollection();
		collection.Collection = this.to.toArgument(parent, validator);
		collection.Item = this.item.toArgument(parent, validator);
		return collection;
	}
	
}
