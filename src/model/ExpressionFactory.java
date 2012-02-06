package model;

import java.util.ArrayList;


public class ExpressionFactory {
	Expression myExpression;
	
	public ExpressionFactory(Expression exp)
	{
		myExpression = exp;
	}
	public boolean isThisKindofThing(String cmd)
	 {
		 return myExpression.isThisKindofThing(cmd);
	 }
	
	public Expression createExp(String cmd, ArrayList<Expression> ops){
		return myExpression.createExp(cmd, ops);
	}
}
