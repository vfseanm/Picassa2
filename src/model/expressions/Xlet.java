package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;


public class Xlet implements Expression{
    private Expression myOperand1;
    private Expression myOperand2;
    private Expression myOperand3;
    
    public Xlet (String cmd, ArrayList<Expression> ops)
    {
        if  (ops.size()!=3)
            throw new ParserException("Incorrect number of operands");
        myOperand1 = ops.get(0);
        myOperand2 = ops.get(1);
        myOperand3 = ops.get(2);
    }

    public RGBColor evaluate(float imageX, float imageY, HashMap<String, Expression> myMap, double myCurrentTime)
    {
        String variableName = myOperand1.toString();
        if (myMap != null)
            myMap.put(variableName, myOperand2);
        else
        {
            myMap = new HashMap<String, Expression>();
            myMap.put(variableName, myOperand2);
        }
        
        return myOperand3.evaluate(imageX, imageY, myMap, myCurrentTime);
    }

private Xlet(){};
    
    public static ExpressionFactory getFactory()
    {
        return new ExpressionFactory(new Xlet());
    }
    
    public boolean isThisKindofThing(String cmd){
        return (cmd.contentEquals("let"));
         
       }
    public Expression createExp(String cmd, ArrayList<Expression> ops){
            return new Xlet(cmd, ops);
    
}
}
