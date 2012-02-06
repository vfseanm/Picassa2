package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;



public class Xcolor implements Expression{
	private Expression myOperand1;
    private Expression myOperand2;
    private Expression myOperand3;
    
    public Xcolor (String cmd, ArrayList<Expression> ops)
    {
        if  (ops.size()!=3)
            throw new ParserException("Incorrect number of operands");
    	myOperand1 = ops.get(0);
    	myOperand2 = ops.get(1);
    	myOperand3 = ops.get(2);
    }

	public RGBColor evaluate(float imageX, float imageY, HashMap<String, Expression> myMap, double myCurrentTime) {
		return operate(myOperand1.evaluate(imageX, imageY, myMap, myCurrentTime), myOperand2.evaluate(imageX, imageY, myMap, myCurrentTime), myOperand3.evaluate(imageX, imageY, myMap, myCurrentTime));
	}

	private static RGBColor operate(RGBColor r, RGBColor g, RGBColor b)
    {
		return new RGBColor(r.getRed(), 
                g.getGreen(),
                b.getBlue());
    }
	
private Xcolor(){};
    
    public static ExpressionFactory getFactory()
    {
    	return new ExpressionFactory(new Xcolor());
    }
    
    public boolean isThisKindofThing(String cmd){
        return (cmd.contentEquals("color"));
         
       }
    public Expression createExp(String cmd, ArrayList<Expression> ops){
            return new Xcolor(cmd, ops);
	
}
}
