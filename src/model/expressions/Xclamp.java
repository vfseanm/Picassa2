package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;


public class Xclamp implements Expression{
    private Expression myOperand1;
    
    public Xclamp (String cmd, ArrayList<Expression> ops)
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
        double red;
        double green;
        double blue;
        
                if (left.getRed()>=0)
                    red = 1;
                else
                    red = -1;
                if (left.getGreen()>=0)
                    green = 1;
                else
                    green = -1;
                if (left.getBlue()>=0)
                    blue = 1;
                else
                    blue = -1;
                return new RGBColor(red, green, blue);
    }
    
private Xclamp(){};
    
    public static ExpressionFactory getFactory()
    {
        return new ExpressionFactory(new Xclamp());
    }
    
    public boolean isThisKindofThing(String cmd){
        return (cmd.contentEquals("clamp"));
         
       }
    public Expression createExp(String cmd, ArrayList<Expression> ops){
            return new Xclamp(cmd, ops);
    
}
}
