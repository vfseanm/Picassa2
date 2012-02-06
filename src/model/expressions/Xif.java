package model.expressions;

import java.util.ArrayList;
import java.util.HashMap;

import model.ExpressionFactory;
import model.ParserException;
import model.RGBColor;
import model.Expression;

public class Xif implements Expression {
    private Expression operand1;
    private Expression operand2;
    private Expression operand3;

    public Xif(String cmd, ArrayList<Expression> ops) {
        if  (ops.size()!=3)
            throw new ParserException("Incorrect number of operands");
        
        operand1 = ops.get(0);
        operand2 = ops.get(1);
        operand3 = ops.get(2);
    }

    public RGBColor evaluate(float imageX, float imageY,
            HashMap<String, Expression> myMap, double myCurrentTime) {
        RGBColor first = operand1.evaluate(imageX, imageY, myMap, myCurrentTime);
        double value = average(first);
        if(value>0)
        {
            return operand2.evaluate(imageX, imageY, myMap, myCurrentTime);
        }
        else
            return operand3.evaluate(imageX, imageY, myMap, myCurrentTime);

    }

    private static double average(RGBColor expression) {
        double red = expression.getRed();
        double green = expression.getGreen();
        double blue = expression.getBlue();
        
        double value = ((red+green+blue)/3);
        return value;
    }

    private Xif() {
    };

    public static ExpressionFactory getFactory() {
        return new ExpressionFactory(new Xif());
    }

    public boolean isThisKindofThing(String cmd) {
        return cmd.contentEquals("if");

    }

    public Expression createExp(String cmd, ArrayList<Expression> ops) {
        return new Xif(cmd, ops);

    }
}
