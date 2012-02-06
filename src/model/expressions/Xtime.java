package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;

public class Xtime implements Expression {

    public Xtime() {
    }

    public RGBColor evaluate(float imageX, float imageY, HashMap<String, Expression> myMap, double myCurrentTime) {
        if(myMap!=null && myMap.containsKey("t"))
            throw new ParserException("You can not use 't' as a let variable");
        RGBColor color = new RGBColor(myCurrentTime);
        return color;
    }

    public String toString()
    {
        return "t";
    }
    
    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new Xtime());
    }

    public boolean isThisKindofThing(String cmd) {
        return false;

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        throw new ParserException("Unexpected expression type ");

    }
}
