package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;


public class Xatan implements Expression{
    private Expression myOperand1;
    
    public Xatan (String cmd, ArrayList<Expression> ops)
    {
        if  (ops.size()!=1)
            throw new ParserException("Incorrect number of operands");
        myOperand1 = ops.get(0);;
    }

    public RGBColor evaluate(float imageX, float imageY, HashMap<String, Expression> myMap, double myCurrentTime) {
        return operate(myOperand1.evaluate(imageX, imageY, myMap, myCurrentTime));
    }
    
    private static RGBColor operate(RGBColor left)
    {
        return new RGBColor(Math.atan(left.getRed()), 
                Math.atan(left.getGreen()),
                Math.atan(left.getBlue()));
    }
    
private Xatan(){};
    
    public static ExpressionFactory getFactory()
    {
        return new ExpressionFactory(new Xatan());
    }
    
    public boolean isThisKindofThing(String cmd){
        return (cmd.contentEquals("atan"));
         
       }
    public Expression createExp(String cmd, ArrayList<Expression> ops){
            return new Xatan(cmd, ops);
    
}
}
