package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;

public class Xnumber implements Expression {
    private RGBColor myValue;

    public Xnumber(RGBColor value) {
        myValue = value;
    }

    public RGBColor evaluate(float imageX, float imageY, HashMap<String, Expression> myMap, double myCurrentTime) {
        return myValue;
    }

    private Xnumber() {
    };

    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new Xnumber());
    }

    public boolean isThisKindofThing(String bet) {
        return false;

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        throw new ParserException("Unexpected expression type ");

    };

}
