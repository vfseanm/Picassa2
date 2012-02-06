package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;

public class XletVariable implements Expression {
    private String variableName;

    public XletVariable(String variable) {
        variableName = variable;
        if (variableName.contentEquals("x") || variableName.contentEquals("y"))
            throw new ParserException("You have called an undefined variable");
    }

    public RGBColor evaluate(float imageX, float imageY,
            HashMap<String, Expression> myMap, double myCurrentTime) {
        RGBColor answer;
        if (myMap == null) {
            throw new ParserException("You have called an undefined variable");
        }
        answer = myMap.get(variableName).evaluate(imageX, imageY, myMap, myCurrentTime);
        if (answer == null) {
            throw new ParserException("You have called an undefined variable");
        }

        return answer;
    }

    public String toString() {
        return variableName;
    }

    private XletVariable() {
    };

    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new XletVariable());
    }

    public boolean isThisKindofThing(String cmd) {
        System.out.println("ERROR");
        return false;

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        throw new ParserException(
                "create XletVariable should not have been called ");

    };
}
