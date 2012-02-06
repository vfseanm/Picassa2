package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;


public class Xceil implements Expression{
    private Expression myOperand1;
    
    public Xceil (String cmd, ArrayList<Expression> ops)
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
        return new RGBColor(Math.ceil(left.getRed()), 
                Math.ceil(left.getGreen()),
                Math.ceil(left.getBlue()));
    }
    
private Xceil(){};
    
    public static ExpressionFactory getFactory()
    {
        return new ExpressionFactory(new Xceil());
    }
    
    public boolean isThisKindofThing(String cmd){
        return (cmd.contentEquals("ceil"));
         
       }
    public Expression createExp(String cmd, ArrayList<Expression> ops){
            return new Xceil(cmd, ops);
    
}
}
