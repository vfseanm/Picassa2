package model;


import java.util.ArrayList;
import java.util.HashMap;

public interface Expression {
	
	    public abstract RGBColor evaluate (float imageX, float imageY, HashMap<String, Expression> myMap, double myCurrentTime); // make subclasses of expression. like plusExpression, etc

	    public abstract String toString ();
	    public abstract boolean isThisKindofThing(String bet);
	    public abstract Expression createExp(String cmd, ArrayList<Expression> ops);
	}








	

