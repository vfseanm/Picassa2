package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;

public class XY implements Expression {

    public XY() {
    }

    public RGBColor evaluate(float imageX, float imageY, HashMap<String, Expression> myMap, double myCurrentTime) {
        if(myMap!=null && myMap.containsKey("y"))
            throw new ParserException("You can not use 'y' as a let variable");
        RGBColor color = new RGBColor(imageY);
        return color;
    }

    public String toString()
    {
        return "y";
    }
    
    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new XY());
    }

    public boolean isThisKindofThing(String cmd) {
        return false;

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        throw new ParserException("Unexpected expression type ");

    }
}
