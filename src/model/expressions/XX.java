package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;

public class XX implements Expression {

    public XX() {
    }

    public RGBColor evaluate(float imageX, float imageY, HashMap<String, Expression> myMap, double myCurrentTime) {
        if(myMap!=null && myMap.containsKey("x"))
            throw new ParserException("You can not use 'x' as a let variable");
        RGBColor color = new RGBColor(imageX);
        return color;
    }

    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new XX());
    }

    public String toString()
    {
        return "x";
    }
    
    public boolean isThisKindofThing(String cmd) {
        return false;

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        throw new ParserException("Unexpected expression type ");

    }
}
