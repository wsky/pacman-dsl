package pacman.definition;

import pacman.Activity;
import pacman.InArgument;
import pacman.statements.AddToCollection;
import pacman.statements.AddToMap;

public class AddToDefinition extends ActivityDefinition {
	private InArgumentDefinition to;
	private InArgumentDefinition item;
	private InArgumentDefinition key;
	private InArgumentDefinition value;
	private boolean isMap;
	
	public AddToDefinition() {
		this("AddTo");
	}
	
	public AddToDefinition(String displayName) {
		super(displayName);
	}
	
	public AddToDefinition List(Object input) {
		this.to = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	public AddToDefinition Map(Object input) {
		this.isMap = true;
		this.to = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	public AddToDefinition Item(Object input) {
		this.item = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	public AddToDefinition Key(Object input) {
		this.key = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	public AddToDefinition Value(Object input) {
		this.value = DefinitionUtilities.parseInArgument(input);
		return this;
	}
	
	@Override
	protected Activity internalToActivity(DefinitionValidator validator) {
		if (this.to == null)
			validator.addError("Map or List not set");
		if (this.isMap && this.key == null)
			validator.addError("Key not set");
		if (!this.isMap && this.item == null)
			validator.addError("Item not set");
		if (validator.hasError())
			return null;
		
		if (this.isMap) {
			AddToMap map = new AddToMap();
			map.Map = this.to.toArgument(this.getParent(), validator);
			map.Key = this.key.toArgument(this.getParent(), validator);
			map.Value = this.value != null ? this.value.toArgument(this.getParent(), validator) : new InArgument();
			return map;
		}
		
		AddToCollection collection = new AddToCollection();
		collection.Collection = this.to.toArgument(this.getParent(), validator);
		collection.Item = this.item.toArgument(this.getParent(), validator);
		return collection;
	}
}
