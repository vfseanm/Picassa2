package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;


public class Xexp implements Expression{
	private Expression myOperand1;
    private Expression myOperand2;
    
    public Xexp (String cmd, ArrayList<Expression> ops)
    {
        if  (ops.size()!=2)
            throw new ParserException("Incorrect number of operands");
    	myOperand1 = ops.get(0);
    	myOperand2 = ops.get(1);
    }

	public RGBColor evaluate(float imageX, float imageY, HashMap<String, Expression> myMap, double myCurrentTime) {
		return operate(myOperand1.evaluate(imageX, imageY, myMap, myCurrentTime), myOperand2.evaluate(imageX, imageY, myMap, myCurrentTime));
	}
	
	private static RGBColor operate(RGBColor left, RGBColor right)
    {
		return new RGBColor(Math.pow(left.getRed(), right.getRed()), 
                Math.pow(left.getGreen(), right.getGreen()),
                Math.pow(left.getBlue(), right.getBlue()));
    }
	
private Xexp(){};
    
    public static ExpressionFactory getFactory()
    {
    	return new ExpressionFactory(new Xexp());
    }
    
    public boolean isThisKindofThing(String cmd){
        if (cmd.contentEquals("^") || cmd.contentEquals("exp") )
            return true;
        return false;
         
       }
    public Expression createExp(String cmd, ArrayList<Expression> ops){
            return new Xexp(cmd, ops);
	
}
}
