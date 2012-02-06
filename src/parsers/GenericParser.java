package parsers;

import model.Expression;

public interface GenericParser {

	public abstract boolean isThisThing(Parsehandler handler);
	public abstract Expression parseThis(Parsehandler handler);
	
	
}
